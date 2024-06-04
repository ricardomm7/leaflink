package pt.ipp.isep.dei.project.ui.gui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import pt.ipp.isep.dei.project.Main;
import pt.ipp.isep.dei.project.application.controller.ListGreenSpacesController;
import pt.ipp.isep.dei.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.project.application.session.UserSession;
import pt.ipp.isep.dei.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.project.dto.GreenSpaceDto;

import java.util.List;
import java.util.stream.Collectors;


public class SpacesGUI {
    private final RegisterGreenSpaceController gsC = new RegisterGreenSpaceController();
    private final ListGreenSpacesController lgsC = new ListGreenSpacesController();
    private List<GreenSpaceDto> allGreenSpaces;

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
    private ListView<String> listGreenSpaces;

    @FXML
    private TextField searchBarGS;

    @FXML
    private Button desassociateBtn;


    @FXML
    void handleEnterSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchBtnHandler(new ActionEvent());
        }
    }

    @FXML
    void searchBtnHandler(ActionEvent event) {
        String searchText = searchBarGS.getText().toLowerCase();
        List<GreenSpaceDto> filteredGreenSpaces = allGreenSpaces.stream()
                .filter(greenSpaceDto -> greenSpaceDto.getName().toLowerCase().contains(searchText) ||
                        greenSpaceDto.getAddress().toLowerCase().contains(searchText) ||
                        greenSpaceDto.getCity().toLowerCase().contains(searchText) ||
                        greenSpaceDto.getType().toString().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        updateListView(filteredGreenSpaces);
    }

    private void updateListView(List<GreenSpaceDto> greenSpaces) {
        ObservableList<String> observableList = FXCollections.observableArrayList(
                greenSpaces.stream()
                        .map(greenSpaceDto -> {
                            String managerEmail = (greenSpaceDto.getManager() != null) ? greenSpaceDto.getManager() : "No Manager";
                            return greenSpaceDto.getName() + " | " +
                                    greenSpaceDto.getAddress() + ", " +
                                    greenSpaceDto.getCity() + " | " +
                                    greenSpaceDto.getType() + " | " +
                                    managerEmail;
                        })
                        .collect(Collectors.toList())
        );
        listGreenSpaces.setItems(observableList);
    }

    @FXML
    void registerNewBtnHandler(ActionEvent event) {
        showDialog();
    }

    private void showDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Register new Green Space");

        ButtonType addButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField();
        ComboBox<GreenSpaceType> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll(GreenSpaceType.values());
        TextField areaField = new TextField();
        TextField streetField = new TextField();
        TextField zipCodeField = new TextField();
        TextField cityField = new TextField();

        ComboBox<GreenSpaceType> gsType = new ComboBox<>(FXCollections.observableArrayList(GreenSpaceType.values()));
        gsType.setCellFactory(lv -> new ListCell<GreenSpaceType>() {
            @Override
            protected void updateItem(GreenSpaceType item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
            }
        });
        gsType.setButtonCell(new ListCell<GreenSpaceType>() {
            @Override
            protected void updateItem(GreenSpaceType item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
            }
        });

        setupEnterKeyTraversal(nameField, areaField, streetField, zipCodeField, cityField);

        zipCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{4}-\\d{3}")) { // Verifica se o ZIP code possui o formato correto
                zipCodeField.setStyle("-fx-border-color: red;"); // Define o estilo do campo se o formato estiver incorreto
            } else {
                zipCodeField.setStyle(null); // Remove qualquer estilo definido anteriormente
            }
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(streetField, 1, 1);
        grid.add(new Label("Zip-Code:"), 0, 2);
        grid.add(zipCodeField, 1, 2);
        grid.add(new Label("City:"), 0, 3);
        grid.add(cityField, 1, 3);
        grid.add(new Label("Area (hectares):"), 0, 4);
        grid.add(areaField, 1, 4);
        grid.add(new Label("Type:"), 0, 5);
        grid.add(gsType, 1, 5);

        dialog.getDialogPane().setContent(grid);

        UserSession manager = ApplicationSession.getInstance().getCurrentSession();


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String name = nameField.getText();
                String zipCode = zipCodeField.getText();
                String city = cityField.getText();
                String street = streetField.getText();
                double area = Double.parseDouble(areaField.getText());
                GreenSpaceType type = gsType.getValue();

                gsC.createNewGS(name, street, zipCode, area, city, manager.getUserEmail(), type);

                updateGSList();
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

    private void updateGSList() {
        listGreenSpaces.getItems().clear();
        allGreenSpaces = gsC.getGreenSpaces();
        updateListView(allGreenSpaces);
    }

    @FXML
    void disassociateHandler(ActionEvent event) {
        int selectedIndex = listGreenSpaces.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            gsC.removeGS(selectedIndex);
            updateGSList();
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
    public void initialize() {
        updateGSList();
        listGreenSpaces.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            desassociateBtn.setDisable(newValue == null);
        });
        desassociateBtn.setDisable(true);
    }
}


