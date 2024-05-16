package pt.ipp.isep.dei.project.ui.console.menu;

import pt.ipp.isep.dei.project.ui.RunTestsUI;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class QamUI implements Runnable {
    public QamUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Run Tests for time about Kruskal", new RunTestsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- QAM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
