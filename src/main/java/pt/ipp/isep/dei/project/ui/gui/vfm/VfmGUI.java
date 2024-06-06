package pt.ipp.isep.dei.project.ui.gui.vfm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pt.ipp.isep.dei.project.Main;

public class VfmGUI {


    @FXML
    void vehicleBtnActionHandle(ActionEvent event) {
        try {
            Main.loadNewActivity("mainMenus/vfm/vfmMenu_vehic.fxml", true, 1205, 900, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
