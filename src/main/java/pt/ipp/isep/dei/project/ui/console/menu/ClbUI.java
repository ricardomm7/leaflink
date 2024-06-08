package pt.ipp.isep.dei.project.ui.console.menu;

import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClbUI implements Runnable {
    public ClbUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- CLB MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
