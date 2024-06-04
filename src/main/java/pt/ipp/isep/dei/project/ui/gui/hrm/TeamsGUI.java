package pt.ipp.isep.dei.project.ui.gui.hrm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.project.Main;

public class TeamsGUI {
    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_teams.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void analysBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_analysis.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void spacesBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_spaces.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void routeBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_routes.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void usersBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/HRM/hrmMenu_users.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}