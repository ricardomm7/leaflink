package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The CreateTeamUI class provides a user interface for creating teams.
 */
public class CreateTeamUI implements Runnable {
    private final CreateTeamController createTeamController;
    private final Scanner scanner;
    private final TeamRepository teamRepository;

    public CreateTeamUI() {
        Repositories repositories = Repositories.getInstance();
        this.createTeamController = new CreateTeamController();
        this.scanner = new Scanner(System.in);
        this.teamRepository = repositories.getTeamRepository();
    }

    public void run() {
        List<Skill> skills = createTeamController.getSkills();
        if (skills.isEmpty()) {
            System.out.println("No skills available. Exiting.");
            return;
        }
        List<Collaborator> availableCollaborators = createTeamController.getAvailableCollaborators();
        if (availableCollaborators.isEmpty()) {
            System.out.println("No collaborators available. Exiting.");
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
        int maxTeamSize = getMaxTeamSizeFromUser(minTeamSize);
        Team proposedTeam = createTeamController.generateProposal(selectedSkills, minTeamSize, maxTeamSize);

        if (proposedTeam != null) {
            System.out.println("Generated Team Proposal:");
            displayProposal(proposedTeam.getCollaborators());
            System.out.print("Do you accept this team? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                teamRepository.addTeam(proposedTeam); // Add team to repository
                System.out.println("Team successfully created!");
            } else {
                promptForCustomTeamCreation(selectedSkills, minTeamSize, maxTeamSize);
            }
        } else {
            System.out.println("No suitable team could be generated.");
            promptForCustomTeamCreation(selectedSkills, minTeamSize, maxTeamSize);
        }
        displayCreatedTeams();
    }


    private void promptForCustomTeamCreation(List<Skill> selectedSkills, int minTeamSize, int maxTeamSize) {
        System.out.print("Do you want to create your own team? (y/n): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            List<Collaborator> availableCollaborators = createTeamController.getAvailableCollaborators();
            List<Collaborator> selectedCollaborators = getSelectedCollaboratorsFromUser(availableCollaborators);

            boolean hasRequiredSkills = checkIfCollaboratorsHaveRequiredSkills(selectedCollaborators, selectedSkills);
            if (!hasRequiredSkills) {
                System.out.print("Warning: The selected collaborators don't have the required skills. Proceed anyway? (y/n): ");
                String proceedResponse = scanner.nextLine();
                if (!proceedResponse.equalsIgnoreCase("y")) {
                    return;
                }
            }

            if (selectedCollaborators.size() < minTeamSize) {
                System.out.print("Warning: The selected collaborators do not meet the minimum team size requirement. Proceed anyway? (y/n): ");
                String proceedResponse = scanner.nextLine();
                if (!proceedResponse.equalsIgnoreCase("y")) {
                    return;
                }
            }

            System.out.println("Selected Collaborators:");
            displayProposal(selectedCollaborators);
            System.out.print("Do you confirm this selection? (y/n): ");
            String confirmResponse = scanner.nextLine();
            if (confirmResponse.equalsIgnoreCase("y")) {
                Team customTeam = createTeamController.createCustomTeam(selectedSkills, selectedCollaborators, minTeamSize, maxTeamSize);
                if (customTeam != null) {
                    System.out.println("Team successfully created!");
                } else {
                    System.out.println("Unable to create a custom team.");
                }
            } else {
                System.out.println("Team creation aborted.");
            }
        }
    }


    private boolean checkIfCollaboratorsHaveRequiredSkills(List<Collaborator> selectedCollaborators, List<Skill> requiredSkills) {
        List<Skill> combinedSkills = new ArrayList<>();
        for (Collaborator collaborator : selectedCollaborators) {
            for (Skill skill : collaborator.getSkills()) {
                if (!combinedSkills.contains(skill)) {
                    combinedSkills.add(skill);
                }
            }
        }
        return combinedSkills.containsAll(requiredSkills);
    }

    private int getMinTeamSizeFromUser() {
        int minTeamSize;
        while (true) {
            System.out.print("Enter minimum team size: ");
            if (scanner.hasNextInt()) {
                minTeamSize = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (minTeamSize > 0) {
                    break;
                } else {
                    System.out.println("Minimum team size must be greater than zero.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
        return minTeamSize;
    }

    private int getMaxTeamSizeFromUser(int minTeamSize) {
        int maxTeamSize;
        while (true) {
            System.out.print("Enter maximum team size: ");
            if (scanner.hasNextInt()) {
                maxTeamSize = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (maxTeamSize > minTeamSize) {
                    break;
                } else {
                    System.out.println("Maximum team size must be greater than minimum team size.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
        return maxTeamSize;
    }

    private void displayProposal(List<Collaborator> collaborators) {
        for (Collaborator collaborator : collaborators) {
            System.out.print(collaborator.getName() + " - Skills: ");
            List<Skill> collaboratorSkills = collaborator.getSkills();
            for (int j = 0; j < collaboratorSkills.size(); j++) {
                System.out.print(collaboratorSkills.get(j).getDesignation());
                if (j < collaboratorSkills.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }


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

    private List<Collaborator> getSelectedCollaboratorsFromUser(List<Collaborator> collaborators) {
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        System.out.println("Available collaborators:");
        for (int i = 0; i < collaborators.size(); i++) {
            Collaborator collaborator = collaborators.get(i);
            System.out.print((i + 1) + ". " + collaborator.getName() + " - Skills: ");
            List<Skill> collaboratorSkills = collaborator.getSkills();
            for (int j = 0; j < collaboratorSkills.size(); j++) {
                System.out.print(collaboratorSkills.get(j).getDesignation());
                if (j < collaboratorSkills.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.print("Enter the numbers of the selected collaborators (comma-separated): ");
        String input = scanner.nextLine();
        String[] collaboratorNumbers = input.split(",");
        for (String collaboratorNumber : collaboratorNumbers) {
            int index = Integer.parseInt(collaboratorNumber.trim()) - 1;
            if (index >= 0 && index < collaborators.size()) {
                selectedCollaborators.add(collaborators.get(index));
            } else {
                System.out.println("Invalid collaborator number: " + collaboratorNumber);
            }
        }
        return selectedCollaborators;
    }

    public void displayCreatedTeams() {
        List<Team> teams = teamRepository.getTeamList();
        if (teams.isEmpty()) {
            System.out.println("No teams have been created yet.");
        } else {
            System.out.println("Created Teams:");
            for (int i = 0; i < teams.size(); i++) {
                Team team = teams.get(i);
                System.out.println("Team " + (i + 1) + ":");
                System.out.println("Collaborators:");
                List<Collaborator> collaborators = team.getCollaborators();
                for (Collaborator collaborator : collaborators) {
                    List<Skill> collaboratorSkills = collaborator.getSkills();
                    StringBuilder skillsString = new StringBuilder();
                    for (int j = 0; j < collaboratorSkills.size(); j++) {
                        skillsString.append(collaboratorSkills.get(j).getDesignation());
                        if (j < collaboratorSkills.size() - 1) {
                            skillsString.append(", ");
                        }
                    }
                    System.out.println("- " + collaborator.getName() + " (Skills: " + skillsString.toString() + ")");
                }
                System.out.println();
            }
        }
    }
}
