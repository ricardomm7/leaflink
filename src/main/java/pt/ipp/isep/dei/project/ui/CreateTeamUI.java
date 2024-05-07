package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Collaborator;

import java.util.Scanner;
import java.util.List;

public class CreateTeamUI {
    private final CreateTeamController createTeamController;
    private final Scanner scanner;

    public CreateTeamUI() {
        this.createTeamController = new CreateTeamController();
        this.scanner = new Scanner(System.in);
    }
    public void run() {
        List<Skill> skills = createTeamController.getSkills();
        int minTeamSize = getMinTeamSizeFromUser();
        int maxTeamSize = getMaxTeamSizeFromUser();
        List<Collaborator> collaborators = createTeamController.generateProposal(skills, minTeamSize, maxTeamSize);
        displayProposal(collaborators);
    }

    private int getMinTeamSizeFromUser() {
        System.out.print("Enter minimum team size: ");
        return scanner.nextInt();
    }

    private int getMaxTeamSizeFromUser() {
        System.out.print("Enter maximum team size: ");
        return scanner.nextInt();
    }
    private void displayProposal(List<Collaborator> collaborators) {
        System.out.println("Generated Team Proposal:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println((i + 1) + ". " + collaborators.get(i).getName());
        }
    }

    public static void main(String[] args) {
        CreateTeamUI createTeamUI = new CreateTeamUI();
        createTeamUI.run();
    }


}
