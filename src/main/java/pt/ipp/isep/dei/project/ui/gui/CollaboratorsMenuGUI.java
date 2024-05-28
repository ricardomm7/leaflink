package pt.ipp.isep.dei.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollaboratorsMenuGUI {
    private final CreateSkillController jc = new CreateSkillController();
    private List<String> allSkills;


    @FXML
    private Label functionNameLabel;

    @FXML
    private ListView<String> listViewSkills;

    @FXML
    private Button removeBtn;

    @FXML
    private TextField skillsSearchTextArea;

    @FXML
    void handleSearchBtn(ActionEvent event) {
        String searchText = skillsSearchTextArea.getText().toLowerCase();
        List<String> filteredSkills = allSkills.stream()
                .filter(skill -> skill.toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<String> observableSkillsList = FXCollections.observableArrayList(filteredSkills);
        listViewSkills.setItems(observableSkillsList);
    }

    @FXML
    void handleAddBtn(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Skill");
        dialog.setHeaderText("Skill Creation");
        dialog.setContentText("Skill designation: ");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String skillDescription = result.get().trim();
            if (!skillDescription.isEmpty()) {
                jc.createSkill(new SkillDto(skillDescription));
                allSkills.add(skillDescription);
                updateSkillsList();
            }
        }
    }

    @FXML
    void handelRemoveBtn(ActionEvent event) {
        int selectedIndex = listViewSkills.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jc.removeSkill(selectedIndex);
            updateSkillsList();
        }
    }

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        // Implementação do botão de tarefas (caso necessário)
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        // Implementação do botão de colaboradores (caso necessário)
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        // Implementação do botão de times (caso necessário)
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        // Implementação do botão de análise (caso necessário)
    }

    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        // Implementação do botão de veículos (caso necessário)
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
        // Implementação do botão de rotas (caso necessário)
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
        // Implementação do botão de espaços (caso necessário)
    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {
        // Implementação do botão de usuários (caso necessário)
    }

    private void updateSkillsList() {
        List<SkillDto> skillsListDto = jc.getSkillsDto();
        allSkills = new ArrayList<>();
        for (SkillDto s : skillsListDto) {
            allSkills.add(s.getDesignation());
        }
        ObservableList<String> observableSkillsList = FXCollections.observableArrayList(allSkills);
        listViewSkills.setItems(observableSkillsList);
    }

    @FXML
    public void initialize() {
        updateSkillsList();
        listViewSkills.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtn.setDisable(newValue == null);
        });
        removeBtn.setDisable(true);
    }
}
