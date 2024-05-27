package pt.ipp.isep.dei.project.ui.console.menu;


import pt.ipp.isep.dei.project.ui.*;
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
        options.add(new MenuItem("Register a Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Register Job", new CreateJobUI()));
        options.add(new MenuItem("Register a Skill", new CreateSkillUI()));
        options.add(new MenuItem("Assign a Skill to Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Generate Team Proposal", new CreateTeamUI()));
        options.add(new MenuItem("Register a Vehicle", new RegisterVehicleUI()));
        options.add(new MenuItem("Register a Vehicle's Maintenance", new RegisterMaintenanceUI()));
        options.add(new MenuItem("List Vehicles Needing Maintenance", new ListMaintenanceUI()));
        options.add(new MenuItem("Calculate the minimum cost Irrigation Route", new WaterIrrigationUI()));
        options.add(new MenuItem("Run Tests for time about Kruskal", new RunTestsUI()));
        options.add(new MenuItem("Create a new Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("Add entry to To-Do List", new RegisterToDoEntryUI()));
        options.add(new MenuItem("List green spaces managed by a specific GSM", new ListGreenSpacesUI()));
        options.add(new MenuItem("Postpone a ToDoEntry", new PostponeAgendaEntryUI()));
        options.add(new MenuItem("Record a task competition", new RecordEntryUI()));
        options.add(new MenuItem("Assign vehicles to agenda entry", new AssignVehiclesUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}