package pt.ipp.isep.dei.project.ui.console.menu;


import pt.ipp.isep.dei.project.ui.ListMaintenanceUI;
import pt.ipp.isep.dei.project.ui.RegisterVehicleUI;
import pt.ipp.isep.dei.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class VfmUI implements Runnable {
    public VfmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a Vehicle", new RegisterVehicleUI()));
        options.add(new MenuItem("Register a Vehicle's Maintenance", new ShowTextUI("Apagar até ao new e colocar devido UI")));
        options.add(new MenuItem("List Vehicles Needing Maintenance", new ListMaintenanceUI()));
        options.add(new MenuItem("Option 3", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Option 4", new ShowTextUI("You have chosen Option 4.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}