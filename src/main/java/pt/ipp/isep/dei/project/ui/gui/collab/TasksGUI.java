package pt.ipp.isep.dei.project.ui.gui.collab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.AddAgendaEntryController;
import pt.ipp.isep.dei.project.application.controller.ListTaskController;
import pt.ipp.isep.dei.project.application.controller.RecordEntryController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.dto.AgendaEntryDto;
import pt.ipp.isep.dei.project.dto.VehicleDto;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TasksGUI {

    private final AddAgendaEntryController addAgendaEntryController = new AddAgendaEntryController();
    private final RecordEntryController recordEntryController = new RecordEntryController();
    private final ListTaskController listTaskController = new ListTaskController();
    private final UserSession session = ApplicationSession.getInstance().getCurrentSession();
    @FXML
    private ListView<String> agendaListView;
    @FXML
    private List<AgendaEntryDto> allAgendaEntry;
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
    private DatePicker startDateDateP;
    @FXML
    private DatePicker endDateDateP;
    @FXML
    private Button CompleteAgendaEntryBtn;
    @FXML
    private Button searchBtn;

    @FXML
    void SerchAgendaEntryHandle(ActionEvent event){


    }

    @FXML
    void CompleteAgendaEntryHandle(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry == null) {
            ShowError.showAlert("Complete Agenda Entry", "No entry selected to complete.", null);
        } else {
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedAgendaEntry, session);

            recordEntryController.recordEntryCompletion(agendaEntryDto, false);

            updateAgendaEntryList();
        }
    }


    private void updateAgendaEntryList() {
        allAgendaEntry = addAgendaEntryController.getAgendaEntries(session);

        List<String> agendaEntryList = allAgendaEntry.stream()
                .map(entry -> String.format("Starting Date: %s | Title: %s | Green Space: %s | Team: %s | Vehicles: %s | Progress Status: %s",
                        entry.getStartingDate(), entry.getTitle(), entry.getGreenSpace().getName(),
                        entry.getAssignedTeam() != null ? entry.getAssignedTeam().getTeamAsString() : "No team assigned",
                        entry.getAssignedVehicles() != null && !entry.getAssignedVehicles().isEmpty() ?
                                entry.getAssignedVehicles().stream().map(VehicleDto::getVehiclePlate).collect(Collectors.joining(", ")) :
                                "No vehicles assigned", entry.getProgressStatus()))
                .collect(Collectors.toList());
        ObservableList<String> observableList = FXCollections.observableArrayList(agendaEntryList);
        agendaListView.setItems(observableList);

        agendaListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setStyle(null);
                        } else {
                            setText(item);
                            AgendaEntryDto agendaEntry = allAgendaEntry.stream()
                                    .filter(entry -> item.contains(entry.getTitle()) && item.contains(entry.getGreenSpace().getName()))
                                    .findFirst().orElse(null);

                            if (agendaEntry != null) {
                                if (agendaEntry.getProgressStatus() == ProgressStatus.CANCELLED) {
                                    setStyle("-fx-text-fill: black;");
                                } else {
                                    switch (agendaEntry.getUrgencyStatus()) {
                                        case HIGH:
                                            setStyle("-fx-text-fill: red;");
                                            break;
                                        case MEDIUM:
                                            setStyle("-fx-text-fill: orange;");
                                            break;
                                        case LOW:
                                            setStyle("-fx-text-fill: green;");
                                            break;
                                        default:
                                            setStyle("-fx-text-fill: black;");
                                            break;
                                    }
                                }
                            } else {
                                setStyle("-fx-text-fill: black;");
                            }
                        }
                    }
                };
            }
        });
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
        String date = splittedString[0].split(": ")[1];
        String status = splittedString[5].split(": ")[1];
        LocalDate dateLocal = LocalDate.parse(date);
        for (AgendaEntryDto entry : addAgendaEntryController.getAgendaEntries(GSM)) {
            if (entry.getTitle().equalsIgnoreCase(title) && entry.getStartingDate().isEqual(dateLocal) && entry.getProgressStatus().toString().equals(status)) {
                return entry;
            }
        }

        return null;

    }
}
