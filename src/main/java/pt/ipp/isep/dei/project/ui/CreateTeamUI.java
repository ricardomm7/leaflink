package pt.ipp.isep.dei.project.ui;

import pt.ipp.isep.dei.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.SkillMapper;
import pt.ipp.isep.dei.project.mappers.TeamMapper;
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
        List<SkillDto> skills = createTeamController.getSkills();
        if (skills.isEmpty()) {
            System.out.println("No skills available. Exiting.");
            return;
        }
        List<CollaboratorDto> availableCollaborators = createTeamController.getAvailableCollaborators();
        if (availableCollaborators.isEmpty()) {
            System.out.println("No collaborators available. Exiting.");
            return;
        }
        System.out.println("Available skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println((i + 1) + ". " + skills.get(i).getDesignation());
        }
        List<SkillDto> selectedSkills = getSelectedSkillsFromUser(skills);
        if (selectedSkills.isEmpty()) {
            System.out.println("No skills selected. Exiting.");
            return;
        }
        int minTeamSize = getMinTeamSizeFromUser();
        int maxTeamSize = getMaxTeamSizeFromUser(minTeamSize);
        TeamDto proposedTeam = createTeamController.generateProposal(selectedSkills, minTeamSize, maxTeamSize);

        if (proposedTeam != null) {
            System.out.println("Generated Team Proposal:");
            displayProposal(proposedTeam.getCollaboratorsDtoList());
            System.out.print("Do you accept this team? (y/n): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("y")) {
                teamRepository.addTeam(TeamMapper.toDomain(proposedTeam)); // Add team to repository
                System.out.println("Team successfully created!");
            } else {
                //promptForCustomTeamCreation(selectedSkills, minTeamSize, maxTeamSize);
            }
        } else {
            System.out.println("No suitable team could be generated.");
            //promptForCustomTeamCreation(selectedSkills, minTeamSize, maxTeamSize);
        }
        //displayCreatedTeams();
    }


    /*private void promptForCustomTeamCreation(List<SkillDto> selectedSkills, int minTeamSize, int maxTeamSize) {
        System.out.print("Do you want to create your own team? (y/n): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            List<CollaboratorDto> availableCollaborators = createTeamController.getAvailableCollaborators();
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

            TeamDto customTeam = createTeamController.createCustomTeam(selectedSkills, selectedCollaborators, minTeamSize, maxTeamSize);
            System.out.println("Custom team successfully created:");
            displayProposal(customTeam.getCollaboratorsDtoList());
        }
    }*/


    private boolean checkIfCollaboratorsHaveRequiredSkills(List<Collaborator> selectedCollaborators, List<SkillDto> requiredSkills) {
        List<SkillDto> combinedSkills = new ArrayList<>();
        for (Collaborator collaborator : selectedCollaborators) {
            for (SkillDto skill : SkillMapper.ListToDto(collaborator.getSkills())) {
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


    private List<SkillDto> getSelectedSkillsFromUser(List<SkillDto> skills) {
        List<SkillDto> selectedSkills = new ArrayList<>();
        System.out.print("Enter the numbers of the skills required (comma-separated): ");
        String input = scanner.nextLine();
        String[] skillNumbers = input.split(",");
        for (String number : skillNumbers) {
            try {
                int index = Integer.parseInt(number.trim()) - 1;
                if (index >= 0 && index < skills.size()) {
                    selectedSkills.add(skills.get(index));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + number);
            }
        }
        return selectedSkills;
    }

    /*private List<CollaboratorDto> getSelectedCollaboratorsFromUser(List<CollaboratorDto> availableCollaborators) {
        List<Collaborator> selectedCollaborators = new ArrayList<>();
        System.out.println("Available collaborators:");
        for (int i = 0; i < availableCollaborators.size(); i++) {
            System.out.println((i + 1) + ". " + availableCollaborators.get(i).getName() + " with skills: " + availableCollaborators.get(i).getSkills());
        }
        System.out.print("Enter the numbers of the collaborators to add (comma-separated): ");
        String input = scanner.nextLine();
        String[] collaboratorNumbers = input.split(",");
        for (String number : collaboratorNumbers) {
            try {
                int index = Integer.parseInt(number.trim()) - 1;
                if (index >= 0 && index < availableCollaborators.size()) {
                    selectedCollaborators.add(availableCollaborators.get(index));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + number);
            }
        }
        return selectedCollaborators;
    }*/

   /* private void displayCreatedTeams() {
        System.out.println("Created Teams:");
        List<TeamDto> teams = createTeamController.getTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams have been created.");
        } else {
            for (TeamDto team : teams) {
                System.out.println("Team with required skills: " + team.getSkillsDtoList());
                for (Collaborator collaborator : team.getCollaboratorsDtoList()) {
                    System.out.println(collaborator.getName() + " with skills: " + collaborator.getSkills());
                }
            }
        }
    }*/
}