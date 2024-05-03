package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.SkillRepository;

import java.util.List;
import java.util.Scanner;

/**
 * The AssignSkillUI class provides a user interface for assigning a skill to a collaborator.
 */
public class AssignSkillUI implements Runnable {

    private static Scanner sc =  new Scanner(System.in);

    public void assignSkill() {
        AssignSkillController as = new AssignSkillController();
        System.out.println("Please select a collaborator to assign a skill to:");
        List<Collaborator> collaboratorIdentification = as.getCollaborator();
        for (int i = 0; i < collaboratorIdentification.size(); i++) {
            System.out.println((i + 1) + ". " + collaboratorIdentification.get(i));
        }
        int collaboratorIndex = sc.nextInt();
        sc.nextLine();
        if (collaboratorIndex < 1 || collaboratorIndex > collaboratorIdentification.size()) {
            System.out.println("Invalid selection. Please select a valid collaborator index");
            return;
        }
        Collaborator collaborator = collaboratorIdentification.get(collaboratorIndex - 1);

        System.out.println("Please select a skill to assign to the collaborator:");
        List<Skill> skillDesignation = as.getSkill();
        for (int i = 0; i < skillDesignation.size(); i++) {
            System.out.println((i + 1) + ". " + skillDesignation.get(i));
        }
        int skillIndex = sc.nextInt();
        sc.nextLine();
        if (skillIndex < 1 || skillIndex > skillDesignation.size()) {
            System.out.println("Invalid Selection. Please select a valid skill index");
            return;
        }
        Skill skill = skillDesignation.get(skillIndex - 1);

        System.out.println("\n Please review the entered information:");
        System.out.println("Collaborator: " + collaborator.getIdentification());
        System.out.println("Assigned skills: " + skill.getSkillDesignation());

        System.out.println("\n Do you want to assign these skills to this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            as.assignSkill();
            System.out.println("Skills assigned successfully!");
        } else { //devia ser else ou especificar mesmo para o N? se carregar noutre tecla qualquer vai cencelar
            System.out.println("Operation cancelled!");
        }
    }
    @Override
    public void run() {

    }
}
