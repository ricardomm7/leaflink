package pt.ipp.isep.dei.project.ui.gui.gsm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.*;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.ProgressStatus;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TasksGUI {

    private final RegisterToDoEntryController registerToDoEntryController = new RegisterToDoEntryController();
    private final AddAgendaEntryController addAgendaEntryController = new AddAgendaEntryController();
    private final AssignVehiclesController assignVehiclesController = new AssignVehiclesController();
    private final PostponeAgendaEntryController postponeAgendaEntryController = new PostponeAgendaEntryController();
    private final AssignTeamController assignTeamController = new AssignTeamController();

    private final RecordEntryController recordEntryController = new RecordEntryController();

    private final UserSession session = ApplicationSession.getInstance().getCurrentSession();

    private final CancelAgendaEntryController cancelAgendaEntryController = new CancelAgendaEntryController();

    private List<VehicleDto> vehicleListDto;
    @FXML
    private ListView<String> toDoListView;
    @FXML
    private ListView<String> agendaListView;
    @FXML
    private List<ToDoEntryDto> allToDoEntry;
    @FXML
    private List<AgendaEntryDto> allAgendaEntry;

    @FXML
    private Button addEntryBtn;

    @FXML
    private Button removeEntryBtn;

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
    private VBox taskDetailsVBox;
    @FXML
    private VBox AgendaDetailsVBox;
    @FXML
    private Button PostponeAgendaEntryBtn;
    @FXML
    private Button AddAgendaEntryBtn;
    @FXML
    private Button CancelAgendaEntryBtn;
    @FXML
    private Button CompleteAgendaEntryBtn;

    @FXML
    void CompleteAgendaEntryHandle(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry == null) {
            ShowError.showAlert("Complete Agenda Entry", "No entry selected to complete.", null);
        } else {
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedAgendaEntry, session);

            recordEntryController.recordEntryCompletion(agendaEntryDto, true);

            updateAgendaEntryList();
        }
    }

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/gsm/gsmMenu_tasks.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/gsm/gsmMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/gsm/gsmMenu_routes.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/gsm/gsmMenu_spaces.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAddTeamBtn(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry == null) {
            ShowError.showAlert("Assign Team", "No entry selected to assign a team.", null);
            return;
        }

        Dialog<TeamDto> dialog = createTeamSelectionDialog();
        Optional<TeamDto> result = dialog.showAndWait();

        result.ifPresent(selectedTeam -> {
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedAgendaEntry, session);

            if (agendaEntryDto == null) {
                ShowError.showAlert("Assign Team", "The selected agenda entry does not exist.", null);
                return;
            }

            assignTeamController.updateEntryWithTeam(agendaEntryDto, selectedTeam);
            assignTeamController.setTeamAvailable(selectedTeam, false);

            updateAgendaEntryList();

            StringBuilder stringBuilder = new StringBuilder();
            if (agendaEntryDto.getAssignedTeam() != null) {
                stringBuilder.append(agendaEntryDto.getAssignedTeam().getCollaboratorsDtoList()).append(" | ");
            } else {
                stringBuilder.append("No team assigned");
            }
            teamLabelA.setText(stringBuilder.toString());

            showAgendaEntryDetails(selectedAgendaEntry);
        });
    }


    private Dialog<TeamDto> createTeamSelectionDialog() {
        Dialog<TeamDto> dialog = new Dialog<>();
        dialog.setTitle("Select Team");

        List<TeamDto> teams = assignTeamController.getTeamList();

        VBox vBox = new VBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton[] radioButtons = new RadioButton[teams.size()];

        for (int i = 0; i < teams.size(); i++) {
            radioButtons[i] = new RadioButton(teams.get(i).getTeamAsString());
            radioButtons[i].setToggleGroup(toggleGroup);
            vBox.getChildren().add(radioButtons[i]);
        }

        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                for (int i = 0; i < teams.size(); i++) {
                    if (radioButtons[i].isSelected()) {
                        return teams.get(i);
                    }
                }
            }
            return null;
        });

        return dialog;
    }


    @FXML
    void handleAddVehicleBtn(ActionEvent event) {
        Dialog<List<VehicleDto>> dialog = createVehicleSelectionDialog();
        Optional<List<VehicleDto>> result = dialog.showAndWait();

        result.ifPresent(selectedVehicles -> {
            String selectedItem = agendaListView.getSelectionModel().getSelectedItem();
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedItem, session);

            assignVehiclesController.updateEntryWithVehicles(agendaEntryDto, selectedVehicles);
            assignVehiclesController.setVehicleAvailability(selectedVehicles, false);
            updateAgendaEntryList();

            StringBuilder a = new StringBuilder();

            if (agendaEntryDto != null && agendaEntryDto.getAssignedVehicles() != null) {
                for (VehicleDto v : agendaEntryDto.getAssignedVehicles()) {
                    a.append(v.getVehiclePlate()).append(" | ").append(v.getType()).append("\n");
                }
                vehicleLabelA.setText(a.toString());
            } else {
                vehicleLabelA.setText("No vehicle assigned");
            }
        });
    }

    private Dialog<List<VehicleDto>> createVehicleSelectionDialog() {
        Dialog<List<VehicleDto>> dialog = new Dialog<>();
        dialog.setTitle("Select from the available vehicles");

        List<VehicleDto> vehicles = assignVehiclesController.getVehicleList();
        int entryIndex = agendaListView.getSelectionModel().getSelectedIndex();
        List<VehicleDto> associatedVehicles = assignVehiclesController.getAgendaEntryList(ApplicationSession.getInstance().getCurrentSession()).get(entryIndex).getAssignedVehicles();

        VBox vBox = new VBox();
        CheckBox[] checkBoxes = new CheckBox[vehicles.size()];
        for (int i = 0; i < vehicles.size(); i++) {
            VehicleDto vehicle = vehicles.get(i);
            checkBoxes[i] = new CheckBox(vehicle.getVehiclePlate() + " - " + vehicle.getType());
            if (associatedVehicles != null && associatedVehicles.contains(vehicle)) {
                checkBoxes[i].setSelected(true);
                checkBoxes[i].setDisable(true); // Desativar a checkbox
            }
            vBox.getChildren().add(checkBoxes[i]);
        }

        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                List<VehicleDto> selectedVehicles = new ArrayList<>();
                for (int i = 0; i < vehicles.size(); i++) {
                    if (checkBoxes[i].isSelected()) {
                        selectedVehicles.add(vehicles.get(i));
                    }
                }
                return selectedVehicles;
            }
            return null;
        });

        return dialog;
    }

    @FXML
    void AddAgendaEntryHandle(ActionEvent event) {
        // Carregar a lista de todas as entradas da To-Do list
        List<ToDoEntryDto> allEntries = registerToDoEntryController.getToDoEntry();
        if (allEntries.isEmpty()) {
            // Exibe uma janela de erro se não houver entradas de ToDo disponíveis
            showErrorAlert("No To Do Entries", "There are no To Do entries available to add to the agenda.");
            return;
        }
        // Criar um ToggleGroup para garantir que apenas uma entrada possa ser selecionada
        ToggleGroup toggleGroup = new ToggleGroup();

        // Criar uma lista de RadioButton para cada entrada da To-Do list
        List<RadioButton> radioButtonList = allEntries.stream()
                .map(entry -> {
                    RadioButton radioButton = new RadioButton(String.format("Title: %s | Duration: %dh | Green Space: %s",
                            entry.getTitle(), entry.getDuration(), entry.getGreenSpace().getName()));
                    radioButton.setToggleGroup(toggleGroup); // Adicionar ao ToggleGroup
                    radioButton.setUserData(entry); // Associar cada RadioButton com a sua entrada correspondente
                    return radioButton;
                })
                .collect(Collectors.toList());

        // Criar um VBox para exibir os RadioButtons
        VBox radioButtonsVBox = new VBox();
        radioButtonsVBox.getChildren().addAll(radioButtonList);

        // Criar um diálogo personalizado para exibir os RadioButtons
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Select To-Do Entry");
        dialog.setHeaderText("Select a To-Do entry to add to the agenda:");

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<ProgressStatus> progressStatusComboBox = new ComboBox<>(FXCollections.observableArrayList(ProgressStatus.values()));
        progressStatusComboBox.setPromptText("Select Progress Status");

        DatePicker startingDate = new DatePicker();
        startingDate.setPromptText("Starting Date");

        grid.add(new Label("Progress Status:"), 0, 3);
        grid.add(progressStatusComboBox, 1, 3);
        grid.add(new Label("Starting Date:"), 0, 4);
        grid.add(startingDate, 1, 4);

        VBox content = new VBox();
        content.getChildren().addAll(radioButtonsVBox, grid);
        dialog.getDialogPane().setContent(content);

        // Mostrar o diálogo e aguardar a seleção do utilizador
        Optional<ButtonType> result = dialog.showAndWait();

        // Se o utilizador clicou em "Add", adicionar a entrada selecionada à agenda
        if (result.isPresent() && result.get() == addButton && toggleGroup.getSelectedToggle() != null) {
            ToDoEntryDto selectedEntry = (ToDoEntryDto) toggleGroup.getSelectedToggle().getUserData();

            // Adicionar a entrada selecionada à agenda
            AgendaEntryDto agendaEntryDto = new AgendaEntryDto(selectedEntry.getTitle(),
                    selectedEntry.getDescription(), selectedEntry.getDuration(),
                    selectedEntry.getUrgencyStatus(), selectedEntry.getGreenSpace(),
                    startingDate.getValue(), progressStatusComboBox.getValue());

            // Adicionar a nova entrada de agenda usando o controlador
            addAgendaEntryController.addAgendaEntry(agendaEntryDto);
            allAgendaEntry.add(agendaEntryDto);

            updateToDoEntry();

            // Atualizar a lista de entradas de agenda
            updateAgendaEntryList();
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    void PostponeAgendaEntryHandle(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry != null) {
            if (PostponeAgendaEntryBtn.isDisabled()) {
                ShowError.showAlert("Postpone", "No entry selected to postpone.", null);
                return;
            }
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Agenda Entry");
            dialog.setHeaderText("Postpone Agenda Entry");

            ButtonType postpone = new ButtonType("Postpone", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(postpone, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            DatePicker dateField = new DatePicker();
            dateField.setPromptText("New date");

            grid.add(new Label("Date:"), 0, 0);
            grid.add(dateField, 1, 0);

            dialog.getDialogPane().setContent(grid);

            ValidationListenersDate(dateField);

            Node postponeButton = dialog.getDialogPane().lookupButton(postpone);
            postponeButton.addEventFilter(ActionEvent.ACTION, event1 -> {
                boolean valid = validateInputsDate(dateField);
                if (valid) {
                    LocalDate date = dateField.getValue();
                    AgendaEntryDto agendaEntry = getAgendaEntry(selectedAgendaEntry, session);

                    // Verifique se a equipe foi atribuída antes de adiar a entrada
                    if (agendaEntry != null && agendaEntry.getAssignedTeam() == null) {
                        ShowError.showAlert("Postpone Agenda Entry", "No team assigned to this entry.", null);
                        return;
                    }

                    postponeAgendaEntryController.postponeEntry(agendaEntry, date);
                    updateAgendaEntryList();
                } else {
                    event1.consume(); // Prevenir fechamento do diálogo
                }
            });
            dialog.showAndWait();
        }
    }


    private boolean validateInputsDate(DatePicker dateField) {
        boolean valid = true;

        if (dateField.getValue() == null || dateField.getValue().isBefore(LocalDate.now())) {
            dateField.setStyle("-fx-border-color: red;");
            valid = false;
        }
        return valid;
    }

    private void ValidationListenersDate(DatePicker dateField) {
        dateField.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue == null) || dateField.getValue().isBefore(LocalDate.now())) {
                dateField.setStyle("-fx-border-color: red;");
            } else {
                dateField.setStyle(null);
            }
        });
    }

    @FXML
    void cancelAgendaEntryHandle(ActionEvent event) {
        String selectedAgendaEntry = agendaListView.getSelectionModel().getSelectedItem();
        if (selectedAgendaEntry != null) {
            AgendaEntryDto agendaEntryDto = getAgendaEntry(selectedAgendaEntry, session);

            Optional<ButtonType> result = getButtonType(agendaEntryDto);
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (cancelAgendaEntryController.cancelAgendaEntry(agendaEntryDto)) {
                    ShowError.showAlertConfirm("Success", "Agenda entry cancelled successfully.", null);
                    updateAgendaEntryList();
                } else {
                    ShowError.showAlert("Error", "Failed to cancel agenda entry.", null);
                }
            }
        }
    }

    private static Optional<ButtonType> getButtonType(AgendaEntryDto agendaEntryDto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancelation");
        alert.setHeaderText("Are you sure you want to cancel this agenda entry?");
        if (agendaEntryDto != null) {
            alert.setContentText("Title: " + agendaEntryDto.getTitle() + "\nDescription: " + agendaEntryDto.getDescription());
        }

        // Mostra a janela e espera pela resposta do utilizador
        return alert.showAndWait();
    }

    @FXML
    void handleAddEntry(ActionEvent event) {
        Dialog<ToDoEntryDto> dialog = new Dialog<>();
        dialog.setTitle("Add New To-Do Entry");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        TextField titleField = new TextField();
        Label titleErrorLabel = new Label();
        titleErrorLabel.setStyle("-fx-text-fill: red;");

        TextField descriptionField = new TextField();
        Label descriptionErrorLabel = new Label();
        descriptionErrorLabel.setStyle("-fx-text-fill: red;");

        TextField durationField = new TextField();
        Label durationErrorLabel = new Label();
        durationErrorLabel.setStyle("-fx-text-fill: red;");

        ComboBox<UrgencyStatus> urgencyComboBox = new ComboBox<>();
        urgencyComboBox.getItems().setAll(UrgencyStatus.values());

        ComboBox<GreenSpaceDto> greenSpaceComboBox = new ComboBox<>();
        greenSpaceComboBox.getItems().setAll(registerToDoEntryController.getGreenSpacesDto());
        greenSpaceComboBox.setCellFactory(new Callback<ListView<GreenSpaceDto>, ListCell<GreenSpaceDto>>() {
            @Override
            public ListCell<GreenSpaceDto> call(ListView<GreenSpaceDto> param) {
                return new ListCell<GreenSpaceDto>() {
                    @Override
                    protected void updateItem(GreenSpaceDto item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        greenSpaceComboBox.setButtonCell(new ListCell<GreenSpaceDto>() {
            @Override
            protected void updateItem(GreenSpaceDto item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getName());
                } else {
                    setText(null);
                }
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(titleErrorLabel, 2, 0);

        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(descriptionErrorLabel, 2, 1);

        grid.add(new Label("Duration (hours):"), 0, 2);
        grid.add(durationField, 1, 2);
        grid.add(durationErrorLabel, 2, 2);

        grid.add(new Label("Urgency:"), 0, 3);
        grid.add(urgencyComboBox, 1, 3);

        grid.add(new Label("Green Space:"), 0, 4);
        grid.add(greenSpaceComboBox, 1, 4);

        dialog.getDialogPane().setContent(grid);

        addValidationListeners(titleField, descriptionField, durationField, urgencyComboBox, greenSpaceComboBox);

        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.addEventFilter(ActionEvent.ACTION, event1 -> {
            boolean isValid = validateInputs(titleField, descriptionField, durationField, urgencyComboBox, greenSpaceComboBox);
            if (isValid) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                int duration = Integer.parseInt(durationField.getText());
                UrgencyStatus urgency = urgencyComboBox.getValue();
                GreenSpaceDto greenSpace = greenSpaceComboBox.getValue();
                registerToDoEntryController.createNewToDoEntry(title, description, duration, urgency, greenSpace);

                dialog.setResult(new ToDoEntryDto(title, description, duration, urgency, greenSpace));
            } else {
                ShowError.showAlert("Invalid Input", "Please correct the highlighted fields.", null);
                event1.consume(); // Prevent the dialog from closing
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return null; // This should not close the dialog
            }
            return null;
        });

        dialog.showAndWait().ifPresent(entryDto -> {
            allToDoEntry.add(entryDto);
            updateToDoEntry();
        });
        updateToDoEntry();
    }

    private void addValidationListeners(TextField titleField, TextField descriptionField, TextField
            durationField, ComboBox<UrgencyStatus> urgencyComboBox, ComboBox<GreenSpaceDto> greenSpaceComboBox) {
        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\w\\s,./-áéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ]+")) {
                titleField.setStyle("-fx-border-color: red;");
            } else {
                titleField.setStyle(null);
            }
        });

        descriptionField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\w\\s,./-áéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ]+")) {
                descriptionField.setStyle("-fx-border-color: red;");
            } else {
                descriptionField.setStyle(null);
            }
        });

        durationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d+") || Integer.parseInt(newValue) <= 0) {
                durationField.setStyle("-fx-border-color: red;");
            } else {
                durationField.setStyle(null);
            }
        });

        urgencyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                urgencyComboBox.setStyle("-fx-border-color: red;");
            } else {
                urgencyComboBox.setStyle(null);
            }
        });

        greenSpaceComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                greenSpaceComboBox.setStyle("-fx-border-color: red;");
            } else {
                greenSpaceComboBox.setStyle(null);
            }
        });
    }

    private boolean validateInputs(TextField titleField, TextField descriptionField, TextField
            durationField, ComboBox<UrgencyStatus> urgencyComboBox, ComboBox<GreenSpaceDto> greenSpaceComboBox) {
        boolean valid = true;

        if (!titleField.getText().matches("[\\w\\s,./-áéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ]+")) {
            titleField.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (!descriptionField.getText().matches("[\\w\\s,./-áéíóúãõâêîôûçÁÉÍÓÚÃÕÂÊÎÔÛÇ]+")) {
            descriptionField.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (!durationField.getText().matches("\\d+") || Integer.parseInt(durationField.getText()) <= 0) {
            durationField.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (urgencyComboBox.getValue() == null) {
            urgencyComboBox.setStyle("-fx-border-color: red;");
            valid = false;
        }

        if (greenSpaceComboBox.getValue() == null) {
            greenSpaceComboBox.setStyle("-fx-border-color: red;");
            valid = false;
        }

        return valid;
    }

    @FXML
    void handleRemoveToDoEntry(ActionEvent event) {
        String selectedItem = toDoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] splittedString = selectedItem.split(" \\| ");
            String title = splittedString[0].split(": ")[1];
            String space = splittedString[2].split(": ")[1];

            registerToDoEntryController.removeToDoEntry(title, space);
            updateToDoEntry();
        } else {
            ShowError.showAlert("ToDo Entry", "No ToDo entry selected for removal.", null);
        }

    }

    @FXML
    void initialize() {
        // Inicialização das caixas de detalhes e botões
        taskDetailsVBox.setVisible(false);
        AgendaDetailsVBox.setVisible(false);
        PostponeAgendaEntryBtn.setDisable(true);
        CancelAgendaEntryBtn.setDisable(true);
        removeEntryBtn.setDisable(true);
        CompleteAgendaEntryBtn.setDisable(true);
        updateToDoEntry();
        updateAgendaEntryList();

        // Listener para seleção de itens na lista ToDo
        toDoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showToDoEntryDetails(newValue);
                removeEntryBtn.setDisable(false);
            } else {
                taskDetailsVBox.setVisible(false);
                removeEntryBtn.setDisable(true);
            }
        });

        // Listener para seleção de itens na lista de Agenda
        agendaListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showAgendaEntryDetails(newValue);
                AgendaDetailsVBox.setVisible(true);
                PostponeAgendaEntryBtn.setDisable(false);
                CancelAgendaEntryBtn.setDisable(false);
                CompleteAgendaEntryBtn.setDisable(false);
            } else {
                AgendaDetailsVBox.setVisible(false);
                PostponeAgendaEntryBtn.setDisable(true);
                CancelAgendaEntryBtn.setDisable(true);
                CompleteAgendaEntryBtn.setDisable(true);
            }
        });

        // Definir fábrica de células personalizada para a lista ToDo
        toDoListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                            setStyle(null);
                            ToDoEntryDto entry = getToDoEntryDto(item);
                            if (entry != null) {
                                switch (entry.getUrgencyStatus()) {
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
                        }
                    }
                };
            }
        });

        // Recarregar entradas de agenda ao inicializar
        reloadAgendaEntries();
    }

    private void reloadAgendaEntries() {
        allAgendaEntry = addAgendaEntryController.getAgendaEntries(session);
        updateAgendaEntryList();
    }

    private void updateToDoEntry() {
        allToDoEntry = registerToDoEntryController.getToDoEntry();
        List<String> toDoEntryList = allToDoEntry.stream()
                .map(entry -> String.format("Title: %s | Duration: %dh | Green Space: %s",
                        entry.getTitle(), entry.getDuration(), entry.getGreenSpace().getName()))
                .collect(Collectors.toList());
        ObservableList<String> observableList = FXCollections.observableArrayList(toDoEntryList);
        toDoListView.getItems().setAll(observableList);
    }

    private void showToDoEntryDetails(String string) {
        ToDoEntryDto entry = getToDoEntryDto(string);

        if (entry != null) {
            titleLabel.setText(entry.getTitle());
            descriptionLabel.setText(entry.getDescription());
            durationLabel.setText(String.valueOf(entry.getDuration()) + "h");
            urgencyLabel.setText(entry.getUrgencyStatus().toString());
            greenSpaceLabel.setText(entry.getGreenSpace().getName());
            taskDetailsVBox.setVisible(true);
        } else {
            taskDetailsVBox.setVisible(false);
        }
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

    private ToDoEntryDto getToDoEntryDto(String string) {
        ToDoEntryDto entry = null;
        String[] splittedString = string.split(" \\| ");
        String title = splittedString[0].split(": ")[1];
        String space = splittedString[2].split(": ")[1];

        for (ToDoEntryDto toDoEntry : allToDoEntry) {
            if (toDoEntry.getTitle().equalsIgnoreCase(title) && toDoEntry.getGreenSpace().getName().equalsIgnoreCase(space)) {
                entry = toDoEntry;
                return entry;
            }
        }
        return null;
    }

}
