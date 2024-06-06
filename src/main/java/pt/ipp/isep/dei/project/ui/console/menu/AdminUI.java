package pt.ipp.isep.dei.project.ui.console.menu;


import pt.ipp.isep.dei.project.ui.PostponeAgendaEntryUI;
import pt.ipp.isep.dei.project.ui.RecordEntryUI;
import pt.ipp.isep.dei.project.ui.RegisterToDoEntryUI;
import pt.ipp.isep.dei.project.ui.RunTestsUI;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Run Tests for time about Kruskal", new RunTestsUI()));
        options.add(new MenuItem("Add entry to To-Do List", new RegisterToDoEntryUI()));
        options.add(new MenuItem("Postpone a ToDoEntry", new PostponeAgendaEntryUI()));
        options.add(new MenuItem("Record a task competition", new RecordEntryUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}