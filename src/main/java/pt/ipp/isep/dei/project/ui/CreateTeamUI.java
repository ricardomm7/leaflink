package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The CreateTeamUI class provides a user interface for creating teams.
 */
public class CreateTeamUI implements Runnable {
    private final CreateTeamController createTeamController;
    private final Scanner scanner;

    /**
     * Constructs a new CreateTeamUI object.
     */
    public CreateTeamUI() {
        this.createTeamController = new CreateTeamController();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for creating teams.
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
     * Prompts the user to enter the minimum team size and returns the entered value.
     *
     * @return The minimum team size entered by the user.
     */
    private int getMinTeamSizeFromUser() {
        System.out.print("Enter minimum team size: ");
        int minTeamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return minTeamSize;
    }

    /**
     * Prompts the user to enter the maximum team size and returns the entered value.
     *
     * @return The maximum team size entered by the user.
     */
    private int getMaxTeamSizeFromUser() {
        System.out.print("Enter maximum team size: ");
        int maxTeamSize = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return maxTeamSize;
    }

    /**
     * Displays the proposal of collaborators for the team.
     *
     * @param collaborators The list of collaborators proposed for the team.
     */
    private void displayProposal(List<Collaborator> collaborators) {
        System.out.println("Generated Team Proposal:");
        for (Collaborator collaborator : collaborators) {
            System.out.println(collaborator.getName());
        }
    }

    /**
     * Prompts the user to select skills from the available list and returns the selected skills.
     *
     * @param skills The list of available skills.
     * @return The list of selected skills.
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