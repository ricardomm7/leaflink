package pt.ipp.isep.dei.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.ipp.isep.dei.project.Main;

public class AdminGUI {

    @FXML
    void tasksBtnActionHandle(ActionEvent event) {

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

    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {

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

    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {

    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {

    }

    @FXML
    void handleVehicleSearchBtn(ActionEvent event){

    }

    @FXML
    void handleVehicleAddBtn(ActionEvent event){

    }

    @FXML
    void handelVehicleRemoveBtn(ActionEvent event){

    }

    @FXML
    void handleEnterSearchBar(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            handleVehicleSearchBtn(new ActionEvent());
        }
    }
}
