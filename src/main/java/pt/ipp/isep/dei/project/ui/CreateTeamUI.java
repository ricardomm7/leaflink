package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * The CreateTeamUI class provides a user interface for generating team proposals.
 * It interacts with the CreateTeamController to obtain skills, minimum and maximum team sizes, and generates a proposal of collaborators for the team.
 */
public class CreateTeamUI implements Runnable {
    private final CreateTeamController createTeamController;
    private final Scanner scanner;

    /**
     * Constructs a new CreateTeamUI object
     * Initializes the CreateTeamController and scanner instances
     */
    public CreateTeamUI() {
        this.createTeamController = new CreateTeamController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the CreateTeamUI functionality.
     * Retrieves skills, minimum and maximum team sizes, generates a proposal of collaborators for the team, and displays the proposal.
     */
    public void run() {
        List<Skill> skills = createTeamController.getSkills();
        if (skills.isEmpty()) {
            System.out.println("No skills available. Exiting.");
            return;
        }

        System.out.println("Available skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getDesignation());
        }

        List<Skill> selectedSkills = getSelectedSkillsFromUser(skills);
        if (selectedSkills.isEmpty()) {
            System.out.println("No skills selected. Exiting.");
            return;
        }

        int minTeamSize = getMinTeamSizeFromUser();
        int maxTeamSize = getMaxTeamSizeFromUser();

        List<Collaborator> collaborators = createTeamController.generateProposal(selectedSkills, minTeamSize, maxTeamSize);
        displayProposal(collaborators);
    }

    /**
     * Retrieves the minimum team size input from the user
     *
     * @return the minimum team size input by the user
     */
    private int getMinTeamSizeFromUser() {
        System.out.print("Enter minimum team size: ");
        return scanner.nextInt();
    }

    /**
     * Retrieves the maximum team size input from the user
     *
     * @return the maximum team size input by the user
     */
    private int getMaxTeamSizeFromUser() {
        System.out.print("Enter maximum team size: ");
        return scanner.nextInt();
    }

    /**
     * Displays the generated team proposal.
     *
     * @param collaborators the list of collaborators in the proposed team
     */
    private void displayProposal(List<Collaborator> collaborators) {
        System.out.println("Generated Team Proposal:");
        for (Collaborator collaborator : collaborators) {
            System.out.println(collaborator.getName());
        }
    }

    /**
     * Prompts the user to select skills from the provided list of skills.
     *
     * @param skills the list of available skills
     * @return the list of selected skills
     */
    private List<Skill> getSelectedSkillsFromUser(List<Skill> skills) {
        List<Skill> selectedSkills = new ArrayList<>();
        System.out.print("Enter the numbers of the selected skills (comma-separated): ");
        String input = scanner.nextLine();
        String[] skillNumbers = input.split(",");
        for (String skillNumber : skillNumbers) {
            int index = Integer.parseInt(skillNumber.trim()) - 1;
            if (index >= 0 && index < skills.size()) {
                selectedSkills.add(skills.get(index));
            } else {
                System.out.println("Invalid skill number: " + skillNumber);
            }
        }
        return selectedSkills;
    }
}
