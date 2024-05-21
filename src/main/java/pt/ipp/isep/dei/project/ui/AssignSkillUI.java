package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.util.*;

/**
 * This class provides an user inteface for assigning skills to collaborators.
 * It pronps the user to select a collaborator and a skill, displays the selected choices, and asks for confirmation before assigning the skill to the collaborator.
 */
public class AssignSkillUI implements Runnable {

    private final AssignSkillController assignSkillController;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs an AssignUI object with a new AssignSkillController instance.
     */
    public AssignSkillUI() {
        this.assignSkillController = new AssignSkillController();
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
        List<CollaboratorDto> collaboratorDtoList = assignSkillController.getCollaboratorDtoList();
        for (int i = 0; i < collaboratorDtoList.size(); i++) {
            System.out.println((i + 1) + ". " + collaboratorDtoList.get(i).getName());
        }
        int collaboratorIndex = sc.nextInt();
        sc.nextLine();
        if (collaboratorIndex < 1 || collaboratorIndex > collaboratorDtoList.size()) {
            System.out.println("Invalid selection. Please select a valid collaborator index");
            return;
        }
        CollaboratorDto selectedCollaborator = collaboratorDtoList.get(collaboratorIndex - 1);

        List<SkillDto> selectedSkills = new ArrayList<>();
        Set<String> selectedSkillsIds = new HashSet<>();
        boolean continueSelectingSkills = true;
        while (continueSelectingSkills) {
            System.out.println("Please select a skill to assign to the collaborator:");
            List<SkillDto> skillDtoList = assignSkillController.getSkillDtoList();

            for (int i = 0; i < skillDtoList.size(); i++) {
                System.out.println((i + 1) + ". " + skillDtoList.get(i).getDesignation());
            }
            int skillIndex = sc.nextInt();
            sc.nextLine();
            if (skillIndex < 1 || skillIndex > skillDtoList.size()) {
                System.out.println("Invalid Selection. Please select a valid skill index");
                continue;
            }
        SkillDto selectedSkill = skillDtoList.get(skillIndex - 1);

            if (selectedSkillsIds.contains(selectedSkill.getDesignation())) {
                System.out.println("The selected skill has already been assigned. Please select another skill.");
                continue;
            }

            if (selectedCollaborator.getSkills().contains(selectedSkill)) {
                System.out.println("The selected skill is already assigned to the collaborator. Please select another skill.");
                continue;
            }

            selectedSkills.add(selectedSkill);
            selectedSkillsIds.add(selectedSkill.getDesignation());

            System.out.println("Do you want to select another skill? (Y/N)");
            String decision = sc.nextLine();
            continueSelectingSkills = decision.trim().equalsIgnoreCase("Y");
        }

        System.out.println("\nPlease review the entered information:");
        System.out.println("Collaborator: " + selectedCollaborator.getName());
        System.out.println("Assigned skills:");
        for (SkillDto skill : selectedSkills) {
            System.out.println("- " + skill.getDesignation());
        }

        System.out.println("\nDo you want to assign these skills to this collaborator? (Y/N)");
        String decision = sc.nextLine();
        if (decision.trim().equalsIgnoreCase("Y")) {
            assignSkillController.assignSkills(selectedCollaborator, selectedSkills);
            System.out.println("Skills assigned successfully!");
        } else {
            System.out.println("Operation cancelled!");
        }
    }
}
