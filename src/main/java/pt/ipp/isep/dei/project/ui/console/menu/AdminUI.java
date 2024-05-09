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

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- ADMIN MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}