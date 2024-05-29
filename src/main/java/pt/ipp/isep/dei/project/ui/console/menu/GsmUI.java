package pt.ipp.isep.dei.project.ui.console.menu;


import pt.ipp.isep.dei.project.ui.*;
import pt.ipp.isep.dei.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class GsmUI implements Runnable {
    public GsmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add entry to To-Do List", new RegisterToDoEntryUI()));
        options.add(new MenuItem("List green spaces managed by me", new ListGreenSpacesUI()));
        options.add(new MenuItem("Postpone a ToDoEntry", new PostponeAgendaEntryUI()));
        options.add(new MenuItem("Assign vehicles to agenda entry", new AssignVehiclesUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}