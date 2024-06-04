package pt.ipp.isep.dei.project.ui.gui.hrm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.project.Main;

public class HrmGUI {

    @FXML
    void caollabBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/hrm/hrmMenu_collab.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/hrm/hrmMenu_teams.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
