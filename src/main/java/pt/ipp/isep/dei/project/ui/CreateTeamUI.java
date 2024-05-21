package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.TeamDto;

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
        List<SkillDto> skillsDto = createTeamController.getSkillsDto();
        if (skillsDto.isEmpty()) {
            System.out.println("No skills available. Exiting.");
            return;
        }
        System.out.println("Available skills:");
        for (int i = 0; i < skillsDto.size(); i++) {
            System.out.println((i + 1) + ". " + skillsDto.get(i).getDesignation());
        }
        List<SkillDto> selectedSkills = getSelectedSkillsFromUser(skillsDto);
        if (selectedSkills.isEmpty()) {
            System.out.println("No skills selected. Exiting.");
            return;
        }
        int minTeamSize = getMinTeamSizeFromUser();
        int maxTeamSize = getMaxTeamSizeFromUser();
        List<CollaboratorDto> collaborators = createTeamController.generateProposal(selectedSkills, minTeamSize, maxTeamSize);
        if (collaborators.isEmpty()) {
            System.out.println("Warning: Insufficient number of collaborators meeting the required skills.");
            if (promptToCreateOwnTeam()) {
                collaborators = selectCollaboratorsManually();
            } else {
                return;
            }
        }
        if (!confirmTeam()) {
            collaborators = selectCollaboratorsManually();
        }

        saveTeam(collaborators);
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
     * @param skillsDto The list of available skills Dto.
     * @return The list of selected skills.
     */
    private List<SkillDto> getSelectedSkillsFromUser(List<SkillDto> skillsDto) {
        List<SkillDto> selectedSkills = new ArrayList<>();
        System.out.print("Enter the numbers of the selected skills (comma-separated): ");
        String input = scanner.nextLine();
        String[] skillNumbers = input.split(",");
        for (String skillNumber : skillNumbers) {
            int index = Integer.parseInt(skillNumber.trim()) - 1;
            if (index >= 0 && index < skillsDto.size()) {
                selectedSkills.add(skillsDto.get(index));
            } else {
                System.out.println("Invalid skill number: " + skillNumber);
            }
        }
        return selectedSkills;
    }

    /**
     * Prompts the user to confirm the team.
     *
     * @return true if the user accepts the team, false otherwise.
     */
    private boolean confirmTeam() {
        System.out.print("Do you accept this team? (Y/N): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("Y");
    }

    /**
     * Prompts the user if they want to create their own team.
     *
     * @return true if the user wants to create their own team, false otherwise.
     */
    private boolean promptToCreateOwnTeam() {
        System.out.print("Do you want to create your own team? (Y/N): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("Y");
    }

    /**
     * Allows the user to manually select collaborators for the team.
     *
     * @return The list of manually selected collaborators.
     */
    private List<CollaboratorDto> selectCollaboratorsManually() {
        List<CollaboratorDto> collaboratorList = createTeamController.getCollaboratorList();
        if (collaboratorList.isEmpty()) {
            System.out.println("No collaborators available. Exiting.");
            return new ArrayList<>();
        }
        System.out.println("Available collaborators:");
        for (int i = 0; i < collaboratorList.size(); i++) {
            System.out.println((i + 1) + ". " + collaboratorList.get(i).getName());
        }
        List<CollaboratorDto> selectedCollaborators = new ArrayList<>();
        System.out.print("Enter the numbers of the selected collaborators (comma-separated): ");
        String input = scanner.nextLine();
        String[] collaboratorNumbers = input.split(",");
        for (String collaboratorNumber : collaboratorNumbers) {
            int index = Integer.parseInt(collaboratorNumber.trim()) - 1;
            if (index >= 0 && index < collaboratorList.size()) {
                selectedCollaborators.add(collaboratorList.get(index));
            } else {
                System.out.println("Invalid collaborator number: " + collaboratorNumber);
            }
        }
        return selectedCollaborators;
    }

    /**
     * Saves the selected team of collaborators.
     *
     * @param collaboratorsDto The list of collaborators to be saved as a team.
     */
    private void saveTeam(List<CollaboratorDto> collaboratorsDto) {
        createTeamController.saveTeam(collaboratorsDto);
        System.out.println("Team saved successfully.");
    }

    /**
     * Displays the list of saved teams.
     */
    private void displaySavedTeams() {
        List<TeamDto> savedTeams = createTeamController.getSavedTeams();
        if (savedTeams.isEmpty()) {
            System.out.println("No teams saved yet.");
        } else {
            System.out.println("Saved Teams:");
            for (int i = 0; i < savedTeams.size(); i++) {
                TeamDto team = savedTeams.get(i);
                System.out.println("Team " + (i + 1) + ":");
                System.out.println("Skills: " + team.getSkills());
                System.out.println("Collaborators: " + team.getCollaboratorsDtoList());
                System.out.println("Minimum Team Size: " + team.getMinTeamSize());
                System.out.println("Maximum Team Size: " + team.getMaxTeamSize());
                System.out.println("--------------------");
            }
        }
    }
}
