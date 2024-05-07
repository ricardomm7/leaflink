package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.List;
import java.util.Scanner;

/**
 * This class provides an user inteface for assigning skills to collaborators.
 * It pronps the user to select a collaborator and a skill, displays the selected choices, and asks for confirmation before assigning the skillto the collaborator.
 */
public class AssignSkillUI implements Runnable {

    private final AssignSkillController assignSkillController ;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs an AssignUI object with a new AssignSkillController instance.
     */
    public AssignSkillUI() {
        this.assignSkillController =  new AssignSkillController();
    }

    /**
     * Implements the run method of the Runnable interface.
     * Calls the assignSkill method.
     */
    @Override
    public void run() {
        assignSkill();
    }

    /**
     * Displays a menu to assign a skill to a collaborator
     */
    public void assignSkill() {
        System.out.println("Please select a collaborator to assign a skill to:");
        List<Collaborator> collaboratorList = assignSkillController.getCollaboratorList();
        for (int i = 0; i < collaboratorList.size(); i++) {
            System.out.println((i + 1) + ". " + collaboratorList.get(i).getName());
        }
        int collaboratorIndex = sc.nextInt();
        sc.nextLine();
        if (collaboratorIndex < 1 || collaboratorIndex > collaboratorList.size()) {
            System.out.println("Invalid selection. Please select a valid collaborator index");
            return;
        }
        Collaborator selectedCollaborator = collaboratorList.get(collaboratorIndex - 1);

        System.out.println("Please select a skill to assign to the collaborator:");
        List<Skill> skillList = assignSkillController.getSkillList();
        for (int i = 0; i < skillList.size(); i++) {
            System.out.println((i + 1) + ". " + skillList.get(i).getDesignation());
        }
        int skillIndex = sc.nextInt();
        sc.nextLine();
        if (skillIndex < 1 || skillIndex > skillList.size()) {
            System.out.println("Invalid Selection. Please select a valid skill index");
            return;
        }
        Skill selectedSkill = skillList.get(skillIndex - 1);

        System.out.println("\nPlease review the entered information:");
        System.out.println("Collaborator: " + selectedCollaborator.getName());
        System.out.println("Assigned skill: " + selectedSkill.getDesignation());

        System.out.println("\nDo you want to assign this skill to this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            assignSkillController.assignSkill(selectedCollaborator, selectedSkill);
            System.out.println("Skill assigned successfully!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }
}
