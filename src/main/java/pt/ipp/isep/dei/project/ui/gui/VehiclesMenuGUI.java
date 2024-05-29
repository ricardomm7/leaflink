package pt.ipp.isep.dei.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.RegisterMaintenanceController;
import pt.ipp.isep.dei.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.project.domain.VehicleType;
import pt.ipp.isep.dei.project.dto.VehicleDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VehiclesMenuGUI {
    private final RegisterVehicleController vehicleController = new RegisterVehicleController();
    private final RegisterMaintenanceController registerMaintenanceController = new RegisterMaintenanceController();

    private List<VehicleDto> allVehicle;

    @FXML
    private ListView<String> listViewVehicle;

    @FXML
    private TextField vehiclesSearchTextArea;

    @FXML
    private Label tareLabel;

    @FXML
    private Label plateLabel;

    @FXML
    private Label aquiLabel;

    @FXML
    private Label kmLabel;

    @FXML
    private Label registLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label vinLabel;

    @FXML
    private Label freqLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label grossLabel;

    @FXML
    private VBox vbox_selectedVehicle;

    @FXML
    private Button removeBtn;

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/adminMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
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
    void handleEnterSearchBar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleVehicleSearchBtn(new ActionEvent());
        }
    }

    @FXML
    void handleVehicleSearchBtn(ActionEvent event) {
        String searchText = vehiclesSearchTextArea.getText().toLowerCase();
        List<String> filteredVehicles = allVehicle.stream()
                .filter(vehicle -> vehicle.getBrand().toLowerCase().contains(searchText)
                        || vehicle.getModel().toLowerCase().contains(searchText)
                        || vehicle.getVehiclePlate().toLowerCase().contains(searchText))
                .map(vehicle -> String.format("%s | %s | %s",
                        vehicle.getBrand(), vehicle.getModel(), vehicle.getVehiclePlate()))
                .collect(Collectors.toList());
        ObservableList<String> observableVehicleList = FXCollections.observableArrayList(filteredVehicles);
        listViewVehicle.setItems(observableVehicleList);
    }

    @FXML
    void handleVehicleAddBtn(ActionEvent event) {
        Dialog<VehicleDto> dialog = new Dialog<>();
        dialog.setTitle("New Vehicle");
        dialog.setHeaderText("Vehicle Creation");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField vinField = new TextField();
        vinField.setPromptText("VIN");

        TextField brandField = new TextField();
        brandField.setPromptText("Brand");

        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        ComboBox<VehicleType> typeComboBox = new ComboBox<>(FXCollections.observableArrayList(VehicleType.values()));
        typeComboBox.setPromptText("Select Type");

        DatePicker registrationDateField = new DatePicker();
        registrationDateField.setPromptText("Registration Date");

        TextField plateField = new TextField();
        plateField.setPromptText("Plate");

        TextField tareWeightField = new TextField();
        tareWeightField.setPromptText("Tare Weight");

        TextField grossWeightField = new TextField();
        grossWeightField.setPromptText("Gross Weight");

        TextField currentKmField = new TextField();
        currentKmField.setPromptText("Current Kilometers");

        DatePicker acquisitionDateField = new DatePicker();
        acquisitionDateField.setPromptText("Acquisition Date");

        TextField maintenanceFrequencyField = new TextField();
        maintenanceFrequencyField.setPromptText("Maintenance Frequency (km)");

        grid.add(new Label("VIN:"), 0, 0);
        grid.add(vinField, 1, 0);
        grid.add(new Label("Brand:"), 0, 1);
        grid.add(brandField, 1, 1);
        grid.add(new Label("Model:"), 0, 2);
        grid.add(modelField, 1, 2);
        grid.add(new Label("Type:"), 0, 3);
        grid.add(typeComboBox, 1, 3);
        grid.add(new Label("Registration Date:"), 0, 4);
        grid.add(registrationDateField, 1, 4);
        grid.add(new Label("Plate:"), 0, 5);
        grid.add(plateField, 1, 5);
        grid.add(new Label("Tare Weight:"), 0, 6);
        grid.add(tareWeightField, 1, 6);
        grid.add(new Label("Gross Weight:"), 0, 7);
        grid.add(grossWeightField, 1, 7);
        grid.add(new Label("Current Kilometers:"), 0, 8);
        grid.add(currentKmField, 1, 8);
        grid.add(new Label("Acquisition Date:"), 0, 9);
        grid.add(acquisitionDateField, 1, 9);
        grid.add(new Label("Maintenance Frequency (km):"), 0, 10);
        grid.add(maintenanceFrequencyField, 1, 10);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String vin = vinField.getText();
                String brand = brandField.getText();
                String model = modelField.getText();
                VehicleType type = typeComboBox.getValue();
                LocalDate registerDate = registrationDateField.getValue();
                String plate = plateField.getText();
                double tare = Double.parseDouble(tareWeightField.getText());
                double gross = Double.parseDouble(grossWeightField.getText());
                int currentKm = Integer.parseInt(currentKmField.getText());
                LocalDate acquisition = acquisitionDateField.getValue();
                int maintenance = Integer.parseInt(maintenanceFrequencyField.getText());

                vehicleController.registerVehicle(vin, brand, model, type, registerDate, plate, tare, gross, currentKm, acquisition, maintenance);

                updateVehicleList();
            }
            return null;
        });
        dialog.showAndWait();

    }

    @FXML
    void handleVehicleRemoveBtn(ActionEvent event) {
        String selectedItem = listViewVehicle.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] parts = selectedItem.split(" \\|");
            String plate = parts[2].trim();
            vehicleController.removeVehicle(plate);
            updateVehicleList();
        } else {
            showErrorDialog("No vehicle selected for removal.");
        }
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    void handelMaintenanceRemoveBtn(ActionEvent event) {
    }

    @FXML
    void handleMaintenanceAddBtn(ActionEvent event) {
    }

    @FXML
    void handleMaintenanceSearchBtn(ActionEvent event) {
    }

    private void updateVehicleList() {
        List<VehicleDto> vehicleList = vehicleController.getVehicleList();
        allVehicle = vehicleList;
        List<String> vehicleDisplayList = vehicleList.stream()
                .map(vehicleDto -> String.format("%s | %s | %s",
                        vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getVehiclePlate()))
                .collect(Collectors.toList());
        ObservableList<String> observableVehicleList = FXCollections.observableArrayList(vehicleDisplayList);
        listViewVehicle.setItems(observableVehicleList);
    }

    @FXML
    void initialize() {
        vbox_selectedVehicle.setVisible(false);

        updateVehicleList();

        listViewVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateVehicleDetails(newValue);
                vbox_selectedVehicle.setVisible(true);
            }
        });

        listViewVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            removeBtn.setDisable(newValue == null);
        });
        removeBtn.setDisable(true);
    }

    private void updateVehicleDetails(String selectedVehicle) {
        // Extrai os detalhes do veículo selecionado
        String[] parts = selectedVehicle.split(" \\| ");
        String selectedPlate = parts[2].trim();

        VehicleDto vehicle = allVehicle.stream()
                .filter(v -> v.getVehiclePlate().equals(selectedPlate))
                .findFirst()
                .orElse(null);

        if (vehicle != null) {
            tareLabel.setText(vehicle.getTareWeight() + " kg");
            plateLabel.setText(vehicle.getVehiclePlate());
            aquiLabel.setText(vehicle.getAcquisitionDate().toString()); // Substitua com o método correto
            kmLabel.setText(vehicle.getCurrentKm() + " km");
            registLabel.setText(vehicle.getRegistrationDate().toString());
            modelLabel.setText(vehicle.getModel());
            vinLabel.setText(vehicle.getVIN());
            freqLabel.setText(vehicle.getMaintenanceFrequency() + " km");
            brandLabel.setText(vehicle.getBrand());
            typeLabel.setText(vehicle.getType().toString());
            grossLabel.setText(vehicle.getGrossWeight() + " kg");
        }
    }
}
