package pt.ipp.isep.dei.project.ui.gui.hrm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamsGUI {

    @FXML
    private ListView<String> listTeamView;

    @FXML
    private TextField teamSearch;

    @FXML
    private Button removeTeamBtn;

    @FXML
    void handleEnterSearchBar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchBtnHandler(new ActionEvent());
        }
    }

    @FXML
    void searchBtnHandler(ActionEvent event) {
        String searchText = teamSearch.getText().toLowerCase();
        List<String> filteredTeams = teamList.stream()
                .filter(team -> team.toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<String> observableTeamList = FXCollections.observableArrayList(filteredTeams);
        listTeamView.setItems(observableTeamList);
    }

    private CreateTeamController createTeamController;
    private ObservableList<String> teamList = FXCollections.observableArrayList();

    public void initialize() {
        createTeamController = new CreateTeamController();
        updateTeamList();
    }

    @FXML
    void newTeamHandler(ActionEvent event) {
        Dialog<List<SkillDto>> skillDialog = createSkillSelectionDialog();
        Optional<List<SkillDto>> result = skillDialog.showAndWait();
        result.ifPresent(selectedSkills -> {
            if (!selectedSkills.isEmpty()) {
                Dialog<Integer[]> sizeDialog = createTeamSizeDialog();
                Optional<Integer[]> sizeResult = sizeDialog.showAndWait();
                sizeResult.ifPresent(sizes -> {
                    int minTeamSize = sizes[0];
                    int maxTeamSize = sizes[1];
                    TeamDto proposedTeam = createTeamController.generateProposal(selectedSkills, minTeamSize, maxTeamSize);
                    if (proposedTeam != null) {
                        boolean confirmed = showTeamProposalDialog(proposedTeam);
                        if (confirmed) {
                            createTeamController.createCustomTeam(selectedSkills, CollaboratorMapper.toDomainList(proposedTeam.getCollaboratorsDtoList()), minTeamSize, maxTeamSize);
                            updateTeamList();
                        }
                    } else {
                        showAlert("No suitable team could be generated.");
                    }
                });
            }
        });
    }

    private Dialog<List<SkillDto>> createSkillSelectionDialog() {
        Dialog<List<SkillDto>> dialog = new Dialog<>();
        dialog.setTitle("Select Skills");

        List<SkillDto> skills = createTeamController.getSkills();
        VBox vBox = new VBox();
        CheckBox[] checkBoxes = new CheckBox[skills.size()];
        for (int i = 0; i < skills.size(); i++) {
            checkBoxes[i] = new CheckBox(skills.get(i).getDesignation());
            vBox.getChildren().add(checkBoxes[i]);
        }

        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                List<SkillDto> selectedSkills = new ArrayList<>();
                for (int i = 0; i < skills.size(); i++) {
                    if (checkBoxes[i].isSelected()) {
                        selectedSkills.add(skills.get(i));
                    }
                }
                return selectedSkills;
            }
            return null;
        });

        return dialog;
    }

    private Dialog<Integer[]> createTeamSizeDialog() {
        Dialog<Integer[]> dialog = new Dialog<>();
        dialog.setTitle("Set Team Size");

        VBox vBox = new VBox();
        TextField minTeamSizeField = new TextField();
        minTeamSizeField.setPromptText("Minimum Team Size");
        TextField maxTeamSizeField = new TextField();
        maxTeamSizeField.setPromptText("Maximum Team Size");
        vBox.getChildren().addAll(new Label("Minimum Team Size:"), minTeamSizeField, new Label("Maximum Team Size:"), maxTeamSizeField);

        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    int minTeamSize = Integer.parseInt(minTeamSizeField.getText());
                    int maxTeamSize = Integer.parseInt(maxTeamSizeField.getText());
                    return new Integer[]{minTeamSize, maxTeamSize};
                } catch (NumberFormatException e) {
                    showAlert("Please enter valid numbers.");
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private boolean showTeamProposalDialog(TeamDto proposedTeam) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Team Proposal");
        alert.setHeaderText("Generated Team Proposal:");
        StringBuilder content = new StringBuilder();
        for (CollaboratorDto collaborator : proposedTeam.getCollaboratorsDtoList()) {
            content.append(collaborator.getName()).append(" - Skills: ");
            for (SkillDto skill : collaborator.getSkills()) {
                content.append(skill.getDesignation()).append(", ");
            }
            content.setLength(content.length() - 2);
            content.append("\n");
        }
        alert.setContentText(content.toString());

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateTeamList() {
        teamList.clear();
        List<TeamDto> teams = createTeamController.getTeams();
        List<TeamDto> uniqueTeams = new ArrayList<>(); // Lista para armazenar equipes únicas

        for (TeamDto team : teams) {
            // Verifica se a equipe já está na lista de equipes únicas
            if (!isTeamAlreadyAdded(uniqueTeams, team)) {
                uniqueTeams.add(team);
            }
        }

        int teamIndex = 1;
        for (TeamDto team : uniqueTeams) {
            // Construa uma string com os detalhes da equipe, por exemplo, nome da equipe
            StringBuilder teamDetails = new StringBuilder("Team " + teamIndex + ": ");
            for (CollaboratorDto collaborator : team.getCollaboratorsDtoList()) {
                teamDetails.append(collaborator.getName()).append(", ");
            }
            // Remove a vírgula extra no final
            teamDetails.setLength(teamDetails.length() - 2);

            teamList.add(teamDetails.toString());
            teamIndex++;
        }
        listTeamView.setItems(teamList); // Atualiza a ListView com a nova lista de equipes
    }

    private boolean isTeamAlreadyAdded(List<TeamDto> teams, TeamDto newTeam) {
        for (TeamDto team : teams) {
            // Verifica se as equipes são iguais
            if (areTeamsEqual(team, newTeam)) {
                return true;
            }
        }
        return false;
    }

    private boolean areTeamsEqual(TeamDto team1, TeamDto team2) {
        List<CollaboratorDto> collaborators1 = team1.getCollaboratorsDtoList();
        List<CollaboratorDto> collaborators2 = team2.getCollaboratorsDtoList();

        // Verifica se as equipes têm o mesmo número de colaboradores
        if (collaborators1.size() != collaborators2.size()) {
            return false;
        }

        // Verifica se todos os colaboradores da equipe 1 estão presentes na equipe 2
        for (CollaboratorDto collaborator1 : collaborators1) {
            boolean collaboratorFound = false;
            for (CollaboratorDto collaborator2 : collaborators2) {
                // Comparando os nomes dos colaboradores para verificar a igualdade
                if (collaborator1.getName().equals(collaborator2.getName())) {
                    collaboratorFound = true;
                    break;
                }
            }
            // Se um colaborador da equipe 1 não for encontrado na equipe 2, as equipes não são iguais
            if (!collaboratorFound) {
                return false;
            }
        }

        // Se todas as verificações passarem, as equipes são consideradas iguais
        return true;
    }

    @FXML
    void handleRemoveTeam(ActionEvent event) {
        // Implement team removal logic here
        int selectedIndex = listTeamView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            createTeamController.removeTeam(selectedIndex);
        }
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/hrm/hrmMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/hrm/hrmMenu_teams.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}