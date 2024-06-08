package pt.ipp.isep.dei.project.ui.gui.gsm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.project.Main;

public class GsmGUI {


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
}
