package pt.ipp.isep.dei.project.ui.gui.admin;

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
import pt.ipp.isep.dei.project.application.controller.RegisterToDoEntryController;
import pt.ipp.isep.dei.project.domain.UrgencyStatus;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;
import pt.ipp.isep.dei.project.dto.ToDoEntryDto;
import pt.ipp.isep.dei.project.ui.ShowError;

import java.util.List;
import java.util.stream.Collectors;

public class TasksGUI {

    private final RegisterToDoEntryController registerToDoEntryController = new RegisterToDoEntryController();

    @FXML
    private ListView<String> toDoListView;

    @FXML
    private List<ToDoEntryDto> allToDoEntry;

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
    private VBox taskDetailsVBox;

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_tasks.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_teams.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_routes.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_spaces.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (entryDto != null) {
                allToDoEntry.add(entryDto);
                updateToDoEntry();
            }
        });
    }

    private void addValidationListeners(TextField titleField, TextField descriptionField, TextField durationField, ComboBox<UrgencyStatus> urgencyComboBox, ComboBox<GreenSpaceDto> greenSpaceComboBox) {
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

    private boolean validateInputs(TextField titleField, TextField descriptionField, TextField durationField, ComboBox<UrgencyStatus> urgencyComboBox, ComboBox<GreenSpaceDto> greenSpaceComboBox) {
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
    void usersBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/admin/adminMenu_users.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        taskDetailsVBox.setVisible(false);
        removeEntryBtn.setDisable(true);
        allToDoEntry = registerToDoEntryController.getToDoEntry();
        updateToDoEntry();

        toDoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showTaskDetails(newValue);
                removeEntryBtn.setDisable(false);
            } else {
                taskDetailsVBox.setVisible(false);
                removeEntryBtn.setDisable(true);
            }
        });


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


    private void showTaskDetails(String string) {
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

    private ToDoEntryDto getToDoEntryDto(String string) {
        ToDoEntryDto entry = null;
        String[] splittedString = string.split(" \\| ");
        String title = splittedString[0].split(": ")[1];
        String duration = splittedString[1].split(": ")[1].replace("h", "");
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
