package pt.ipp.isep.dei.project.ui.gui.collab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.AddAgendaEntryController;
import pt.ipp.isep.dei.project.application.controller.RecordEntryController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.util.List;
import java.util.stream.Collectors;

public class TasksGUI {

    private final AddAgendaEntryController addAgendaEntryController = new AddAgendaEntryController();


    private final RecordEntryController recordEntryController = new RecordEntryController();

    private final UserSession session = ApplicationSession.getInstance().getCurrentSession();
    private List<VehicleDto> vehicleListDto;

    @FXML
    private ListView<String> agendaListView;

    @FXML
    private List<AgendaEntryDto> allAgendaEntry;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label urgencyLabel;

    @FXML
    private Label greenSpaceLabel;
    @FXML
    private Label titleLabelA;

    @FXML
    private Label descriptionLabelA;

    @FXML
    private Label durationLabelA;

    @FXML
    private Label urgencyLabelA;
    @FXML
    private Label greenSpaceLabelA;
    @FXML
    private Label startingDateLabelA;
    @FXML
    private Label progressStatusLabelA;
    @FXML
    private Label vehicleLabelA;
    @FXML
    private Label teamLabelA;

    @FXML
    private VBox AgendaDetailsVBox;

    @FXML
    private Button CompleteAgendaEntryBtn;

    @FXML
    void CompleteAgendaEntryHandle(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry == null) {
            ShowError.showAlert("Complete Agenda Entry", "No entry selected to complete.", null);
        } else {
            updateAgendaEntryList();
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedAgendaEntry, session);
            recordEntryController.recordEntryCompletion(agendaEntryDto);

            updateAgendaEntryList();
        }
    }


    private void updateAgendaEntryList() {
        allAgendaEntry = addAgendaEntryController.getAgendaEntries(session);

        List<String> agendaEntryList = allAgendaEntry.stream()
                .map(entry -> String.format("Starting Date: %s | Title: %s | Green Space: %s | Team: %s | Vehicles: %s",
                        entry.getStartingDate(), entry.getTitle(), entry.getGreenSpace().getName(),
                        entry.getAssignedTeam() != null ? entry.getAssignedTeam().getTeamAsString() : "No team assigned",
                        entry.getAssignedVehicles() != null && !entry.getAssignedVehicles().isEmpty() ?
                                entry.getAssignedVehicles().stream().map(VehicleDto::getVehiclePlate).collect(Collectors.joining(", ")) : "No vehicles assigned"))
                .collect(Collectors.toList());
        ObservableList<String> observableList = FXCollections.observableArrayList(agendaEntryList);
        agendaListView.setItems(observableList);
    }

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/collab/collabMenu_tasks.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        // Inicialização das caixas de detalhes e botões
        AgendaDetailsVBox.setVisible(false);
        CompleteAgendaEntryBtn.setDisable(true);
        updateAgendaEntryList();

        // Listener para seleção de itens na lista de Agenda
        agendaListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showAgendaEntryDetails(newValue);
                AgendaDetailsVBox.setVisible(true);
                CompleteAgendaEntryBtn.setDisable(false);
            } else {
                AgendaDetailsVBox.setVisible(false);
                CompleteAgendaEntryBtn.setDisable(true);
            }
        });

        // Recarregar entradas de agenda ao inicializar
        reloadAgendaEntries();
    }

    private void reloadAgendaEntries() {
        allAgendaEntry = addAgendaEntryController.getAgendaEntries(session);
        updateAgendaEntryList();
    }

    private void showAgendaEntryDetails(String string) {
        AgendaEntryDto entry = getAgendaEntry(string, session);

        if (entry != null) {
            titleLabelA.setText(entry.getTitle());
            descriptionLabelA.setText(entry.getDescription());
            durationLabelA.setText(entry.getDuration() + "h");
            urgencyLabelA.setText(entry.getUrgencyStatus().toString());
            greenSpaceLabelA.setText(entry.getGreenSpace().getName());
            startingDateLabelA.setText(entry.getStartingDate().toString());
            progressStatusLabelA.setText(entry.getProgressStatus().toString());

            if (entry.getAssignedTeam() == null) {
                teamLabelA.setText("No team assigned");
            } else {
                teamLabelA.setText(entry.getAssignedTeam().getTeamAsString());
            }

            if (entry.getAssignedVehicles() == null || entry.getAssignedVehicles().isEmpty()) {
                vehicleLabelA.setText("No vehicles assigned");
            } else {
                String vehicles = entry.getAssignedVehicles().stream()
                        .map(VehicleDto::getVehiclePlate)
                        .collect(Collectors.joining(", "));
                vehicleLabelA.setText(vehicles);
            }

            AgendaDetailsVBox.setVisible(true);
        } else {
            AgendaDetailsVBox.setVisible(false);
        }
    }

    private AgendaEntryDto getAgendaEntry(String string, UserSession GSM) {
        String[] splittedString = string.split(" \\| ");
        String title = splittedString[1].split(": ")[1];
        for (AgendaEntryDto entry : addAgendaEntryController.getAgendaEntries(GSM)) {
            if (entry.getTitle().equalsIgnoreCase(title)) {
                return entry;
            }
        }

        return null;

    }
}
