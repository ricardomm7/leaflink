package pt.ipp.isep.dei.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.project.domain.DocumentType;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.mappers.JobMapper;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollaboratorsMenuGUI {
    private final CreateSkillController skillC = new CreateSkillController();
    private final CreateJobController jobC = new CreateJobController();
    private final RegisterCollaboratorController collabC = new RegisterCollaboratorController();
    private final AssignSkillController assSkillC = new AssignSkillController();
    private List<String> allSkills;
    private List<JobDto> allJobs;
    private List<CollaboratorDto> collaboratorDtoList;
    private List<String> allCollabs;

    @FXML
    private ListView<String> listViewSkills;

    @FXML
    private Label admissLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label skillsLabel;

    @FXML
    private Label addressLabel;
    @FXML
    private Label mobileLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label jobLabel;

    @FXML
    private VBox vbox_selectedCollab;

    @FXML
    private Label mailLabel;

    @FXML
    private Label doctypeLabel;

    @FXML
    private Label docnumberLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField skillsSearchTextArea;

    @FXML
    private Button removeBtn;

    @FXML
    private ListView<String> listViewJobs;

    @FXML
    private TextField jobSearchTextArea;

    @FXML
    private Button removeBtnJob;

    @FXML
    private Button removeBtnCollab;

    @FXML
    private ListView<String> collabsList;

    @FXML
    private TextField collaboratorsTextArea;

    @FXML
    void handleCollabEnterSearchBar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleCollavSearchBtn(new ActionEvent());
        }
    }

    @FXML
    void handleAddSkillsBtn(ActionEvent event) {
        String selectedCollab = collabsList.getSelectionModel().getSelectedItem();
        if (selectedCollab == null) {
            ShowError.showAlert("Error", "No collaborator selected.", null);
        }

        String[] parts = selectedCollab.split(" \\| ");
        String name = parts[0].trim();

        CollaboratorDto selectedCollaborator = collaboratorDtoList.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);

        List<SkillDto> allSkillsDto = skillC.getSkillsDto();

        List<SkillDto> collaboratorSkillsDto = selectedCollaborator.getSkills();

        Dialog<List<SkillDto>> dialog = new Dialog<>();
        dialog.setTitle("Assign Skills");
        dialog.setHeaderText("Select the Skills/Competences you want to associate with the employee:");

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        List<CheckBox> checkBoxList = new ArrayList<>();
        for (SkillDto skillDto : allSkillsDto) {
            CheckBox checkBox = new CheckBox(skillDto.getDesignation());
            checkBoxList.add(checkBox);
            vbox.getChildren().add(checkBox);

            if (collaboratorSkillsDto.contains(skillDto)) {
                checkBox.setSelected(true);
                checkBox.setDisable(true); // Impede que a habilidade já associada seja desmarcada
            }
        }

        dialog.getDialogPane().setContent(vbox);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                List<SkillDto> selectedSkills = new ArrayList<>();
                for (CheckBox checkBox : checkBoxList) {
                    if (checkBox.isSelected() && !collaboratorSkillsDto.contains(allSkillsDto.get(checkBoxList.indexOf(checkBox)))) {
                        SkillDto skillDto = allSkillsDto.get(checkBoxList.indexOf(checkBox));
                        selectedSkills.add(skillDto);
                    }
                }
                return selectedSkills;
            }
            return null;
        });

        Optional<List<SkillDto>> result = dialog.showAndWait();
        result.ifPresent(selectedSkills -> {
            assSkillC.assignSkills(selectedCollaborator, selectedSkills);
            updateCollaboratorsList();
            updateCollabDetails(selectedCollab);
        });
    }


    @FXML
    void handleCollavSearchBtn(ActionEvent event) {
        String searchText = collaboratorsTextArea.getText().toLowerCase();
        List<String> filteredCollabs = allCollabs.stream()
                .filter(collaborator -> collaborator.toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<String> observableCollabs = FXCollections.observableArrayList(filteredCollabs);
        collabsList.setItems(observableCollabs);
    }

    @FXML
    void handleAddBtnCollab(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add new Collaborator");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        DatePicker birthdatePicker = new DatePicker();
        TextField contactMobileField = new TextField();
        TextField taxpayerNumberField = new TextField();
        TextField emailField = new TextField();
        TextField addressField = new TextField();
        TextField zipCodeField = new TextField();
        TextField cityField = new TextField();
        ComboBox<DocumentType> documentTypeComboBox = new ComboBox<>(FXCollections.observableArrayList(DocumentType.values()));
        TextField identificationNumberField = new TextField();
        DatePicker admissionDatePicker = new DatePicker();

        ComboBox<JobDto> jobComboBox = new ComboBox<>(FXCollections.observableArrayList(allJobs));
        jobComboBox.setCellFactory(lv -> new ListCell<JobDto>() {
            @Override
            protected void updateItem(JobDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getTitle());
            }
        });
        jobComboBox.setButtonCell(new ListCell<JobDto>() {
            @Override
            protected void updateItem(JobDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getTitle());
            }
        });

        setupEnterKeyTraversal(nameField, birthdatePicker, contactMobileField, taxpayerNumberField, emailField, addressField, zipCodeField, cityField, documentTypeComboBox, identificationNumberField, admissionDatePicker, jobComboBox);

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("@")) {
                emailField.setStyle("-fx-border-color: red;");
            } else {
                emailField.setStyle(null);
            }
        });

        zipCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("-")) {
                zipCodeField.setStyle("-fx-border-color: red;");
            } else {
                zipCodeField.setStyle(null);
            }
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Birthdate:"), 0, 1);
        grid.add(birthdatePicker, 1, 1);
        grid.add(new Label("Mobile Contact:"), 0, 2);
        grid.add(contactMobileField, 1, 2);
        grid.add(new Label("Taxpayer number:"), 0, 3);
        grid.add(taxpayerNumberField, 1, 3);
        grid.add(new Label("E-Mail:"), 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(new Label("Address:"), 0, 5);
        grid.add(addressField, 1, 5);
        grid.add(new Label("Zip-Code:"), 0, 6);
        grid.add(zipCodeField, 1, 6);
        grid.add(new Label("City:"), 0, 7);
        grid.add(cityField, 1, 7);
        grid.add(new Label("ID Document Type:"), 0, 8);
        grid.add(documentTypeComboBox, 1, 8);
        grid.add(new Label("ID Document Number:"), 0, 9);
        grid.add(identificationNumberField, 1, 9);
        grid.add(new Label("Admission Date:"), 0, 10);
        grid.add(admissionDatePicker, 1, 10);
        grid.add(new Label("Job:"), 0, 11);
        grid.add(jobComboBox, 1, 11);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String name = nameField.getText();
                LocalDate birthdate = birthdatePicker.getValue();
                int contactMobile = Integer.parseInt(contactMobileField.getText());
                int taxpayerNumber = Integer.parseInt(taxpayerNumberField.getText());
                String email = emailField.getText();
                String address = addressField.getText();
                String zipCode = zipCodeField.getText();
                String city = cityField.getText();
                DocumentType documentType = documentTypeComboBox.getValue();
                String identificationNumber = identificationNumberField.getText();
                LocalDate admissionDate = admissionDatePicker.getValue();
                JobDto jobDto = jobComboBox.getValue();

                collabC.createCLB(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, JobMapper.toDomain(jobDto));

                updateCollaboratorsList();
                return addButtonType;
            }
            return null;
        });
        dialog.showAndWait();
    }

    private void setupEnterKeyTraversal(Node... nodes) {
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            Node nextNode = i < nodes.length - 1 ? nodes[i + 1] : nodes[0];
            node.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    nextNode.requestFocus();
                    event.consume();
                }
            });
        }
    }

    @FXML
    void handelRemoveBtnCollab(ActionEvent event) {
        int selectedIndex = collabsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            collabC.removeCollab(selectedIndex);
            updateCollaboratorsList();
        }
    }

    @FXML
    void handleJobSearchBtn(ActionEvent event) {
        String searchText = jobSearchTextArea.getText().toLowerCase();
        List<String> filteredJobs = allJobs.stream()
                .map(JobDto::getTitle)
                .filter(job -> job.toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<String> observableJobsList = FXCollections.observableArrayList(filteredJobs);
        listViewJobs.setItems(observableJobsList);
    }

    @FXML
    void handleJobAddBtn(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Job");
        dialog.setHeaderText("Job Creation");
        dialog.setContentText("Job designation: ");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String jobDescription = result.get().trim();
            if (!jobDescription.isEmpty()) {
                jobC.createJob(jobDescription);
                updateJobsList();
            }
        }
    }

    @FXML
    void handelJobRemoveBtn(ActionEvent event) {
        int selectedIndex = listViewJobs.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobC.removeJob(selectedIndex);
            updateJobsList();
        }
    }

    @FXML
    void handleEnterSearchBarJob(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleJobSearchBtn(new ActionEvent());
        }
    }

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
                skillC.createSkill(skillDescription);
                updateSkillsList();
            }
        }
    }

    @FXML
    void handleEnterSearchBar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleSearchBtn(new ActionEvent());
        }
    }

    @FXML
    void handelRemoveBtn(ActionEvent event) {
        int selectedIndex = listViewSkills.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            skillC.removeSkill(selectedIndex);
            updateSkillsList();
        }
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/adminMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        // Implementação do botão de times (caso necessário)
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/adminMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        // Implementação do botão de tarefas (caso necessário)
    }

    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/adminMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        List<SkillDto> skillsListDto = skillC.getSkillsDto();
        allSkills = new ArrayList<>();
        for (SkillDto s : skillsListDto) {
            allSkills.add(s.getDesignation());
        }
        ObservableList<String> observableSkillsList = FXCollections.observableArrayList(allSkills);
        listViewSkills.setItems(observableSkillsList);
    }

    private void updateJobsList() {
        List<JobDto> jobListDto = jobC.getJobList();
        allJobs = new ArrayList<>(jobListDto);
        List<String> jobTitles = jobListDto.stream()
                .map(JobDto::getTitle)
                .collect(Collectors.toList());
        ObservableList<String> observableJobsList = FXCollections.observableArrayList(jobTitles);
        listViewJobs.setItems(observableJobsList);
    }

    private void updateCollaboratorsList() {
        collaboratorDtoList = collabC.getCollaborators();
        allCollabs = new ArrayList<>();
        for (CollaboratorDto s : collaboratorDtoList) {
            allCollabs.add(s.getName() + " | " + s.getJob().getTitle() + " | Tax no: " + s.getTaxpayerNumber());
        }
        ObservableList<String> collabsObservable = FXCollections.observableArrayList(allCollabs);
        collabsList.setItems(collabsObservable);
    }

    @FXML
    public void initialize() {
        vbox_selectedCollab.setVisible(false);
        updateSkillsList();
        updateJobsList();
        updateCollaboratorsList();

        listViewSkills.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtn.setDisable(newValue == null);
        });
        removeBtn.setDisable(true);

        listViewJobs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtnJob.setDisable(newValue == null);
        });
        removeBtnJob.setDisable(true);

        collabsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtnCollab.setDisable(newValue == null);
        });
        removeBtnCollab.setDisable(true);

        collabsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateCollabDetails(newValue);
                vbox_selectedCollab.setVisible(true);
            }
        });
    }

    private void updateCollabDetails(String selected) {
        // Extrai os detalhes do colaborador selecionado
        String[] parts = selected.split(" \\| ");
        String name = parts[0].trim();  // Atualizado para pegar a parte correta do nome

        CollaboratorDto collab = collaboratorDtoList.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (collab != null) {
            nameLabel.setText(collab.getName());
            birthLabel.setText(collab.getBirthdate().toString());
            mobileLabel.setText(collab.getContactMobile() + "");
            taxLabel.setText(collab.getTaxpayerNumber() + "");
            mailLabel.setText(collab.getEmail());
            addressLabel.setText(collab.getAddress().toString());
            doctypeLabel.setText(collab.getDocumentType().toString());
            docnumberLabel.setText(collab.getIdentificationNumber());
            admissLabel.setText(collab.getAdmissionDate().toString());
            jobLabel.setText(collab.getJob().getTitle());
            String skillsDescription = collab.getSkills().stream()
                    .map(SkillDto::getDesignation)
                    .collect(Collectors.joining("\n"));
            skillsLabel.setText(skillsDescription);
        }
    }
}
