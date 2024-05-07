package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Collaborator;

import java.util.Scanner;
import java.util.List;

/**
 * The CreateTeamUI class provides a user interface for generating team proposals.
 * It interacts with the CreateTeamController to obtain skills, minimum and maximum team sizes, and generates a proposal of collaborators for the team.
 */
public class CreateTeamUI {
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
        int minTeamSize = getMinTeamSizeFromUser();
        int maxTeamSize = getMaxTeamSizeFromUser();
        List<Collaborator> collaborators = createTeamController.generateProposal(skills, minTeamSize, maxTeamSize);
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
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
    }
}
