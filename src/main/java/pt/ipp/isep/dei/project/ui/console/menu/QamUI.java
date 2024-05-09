package pt.ipp.isep.dei.project.ui.console.menu;

import pt.ipp.isep.dei.project.ui.ListMaintenanceUI;
import pt.ipp.isep.dei.project.ui.RegisterMaintenanceUI;
import pt.ipp.isep.dei.project.ui.RegisterVehicleUI;
import pt.ipp.isep.dei.project.ui.RunTestsUI;
import pt.ipp.isep.dei.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class QamUI implements Runnable {
    public QamUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Option 1", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Run Tests", new RunTestsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
