package pt.ipp.isep.dei.project.ui.gui.collab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.project.Main;

public class CollabGUI {


    @FXML
    void tasksBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/collab/collabMenu_tasks.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
