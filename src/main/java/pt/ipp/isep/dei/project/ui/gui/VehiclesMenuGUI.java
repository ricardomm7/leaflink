package pt.ipp.isep.dei.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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

    private List<String> allVehicle;

    @FXML
    private ListView<String> listViewVehicle;

    @FXML
    private TextField VehicleSearchTextArea;

    @FXML
    private Button removeBtnVehicle;

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
    }

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
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
        try {
            Main.loadNewActivity("mainMenus/adminMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleVehicleSearchBtn(ActionEvent event) {
        String searchText = VehicleSearchTextArea.getText().toLowerCase();
        List<String> filteredVehicles = allVehicle.stream()
                .filter(vehicle -> vehicle.toLowerCase().contains(searchText)).collect(Collectors.toList());
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
                LocalDate aquisition = acquisitionDateField.getValue();
                int maintenance = Integer.parseInt(maintenanceFrequencyField.getText());

                vehicleController.registerVehicle(vin, brand, model, type, registerDate, plate, tare, gross, currentKm, aquisition, maintenance);

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
        allVehicle = vehicleList.stream()
                .map(vehicleDto -> String.format("%s | %s | %s",
                        vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getVehiclePlate()))
                .collect(Collectors.toList());
        ObservableList<String> observableVehicleList = FXCollections.observableArrayList(allVehicle);
        listViewVehicle.setItems(observableVehicleList);
    }

    @FXML
    void initialize() {
        updateVehicleList();
    }


}
