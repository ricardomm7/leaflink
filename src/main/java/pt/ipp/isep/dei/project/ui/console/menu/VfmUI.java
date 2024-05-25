package pt.ipp.isep.dei.project.ui.console.menu;


import pt.ipp.isep.dei.project.ui.ListMaintenanceUI;
import pt.ipp.isep.dei.project.ui.RegisterMaintenanceUI;
import pt.ipp.isep.dei.project.ui.RegisterVehicleUI;
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
        options.add(new MenuItem("Register a Vehicle's Maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("List Vehicles Needing Maintenance", new ListMaintenanceUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}