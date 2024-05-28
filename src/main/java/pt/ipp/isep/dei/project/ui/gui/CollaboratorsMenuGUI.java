package pt.ipp.isep.dei.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.project.dto.JobDto;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollaboratorsMenuGUI {
    private final CreateSkillController skillC = new CreateSkillController();
    private final CreateJobController jobC = new CreateJobController();
    private List<String> allSkills;
    private List<String> allJobs;


    @FXML
    private ListView<String> listViewSkills;

    @FXML
    private Button removeBtn;

    @FXML
    private ListView<String> listViewJobs;

    @FXML
    private TextField jobSearchTextArea;

    @FXML
    private Button removeBtnJob;


    @FXML
    void handleJobSearchBtn(ActionEvent event) {
        String searchText = jobSearchTextArea.getText().toLowerCase();
        List<String> filteredJobs = allJobs.stream()
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
                jobC.createJob(new JobDto(jobDescription));
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
                skillC.createSkill(new SkillDto(skillDescription));
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
    void tasksBtnActionHandle(ActionEvent event) {
        // Implementação do botão de tarefas (caso necessário)
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
        allJobs = new ArrayList<>();
        for (JobDto s : jobListDto) {
            allJobs.add(s.getTitle());
        }
        ObservableList<String> observableJobsList = FXCollections.observableArrayList(allJobs);
        listViewJobs.setItems(observableJobsList);
    }

    @FXML
    public void initialize() {
        updateSkillsList();
        updateJobsList();
        listViewSkills.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtn.setDisable(newValue == null);
        });
        removeBtn.setDisable(true);

        listViewJobs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtnJob.setDisable(newValue == null);
        });
        removeBtnJob.setDisable(true);
    }
}
