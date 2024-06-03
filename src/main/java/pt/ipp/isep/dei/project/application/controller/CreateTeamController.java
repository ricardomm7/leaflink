package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.mappers.SkillMapper;
import pt.ipp.isep.dei.project.mappers.TeamMapper;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The CreateTeamController class handles the logic for creating teams.
 * It interacts with the TeamRepository and SkillRepository to retrieve skills and generate team proposals.
 */
public class CreateTeamController {
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;

    /**
     * Constructs a new CreateTeamController object.
     * Initializes the TeamRepository, SkillRepository, and CollaboratorRepository instances.
     */
    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.skillRepository = repositories.getSkillRepository();
        this.collaboratorRepository = repositories.getCollaboratorRepository();
    }

    /**
     * Retrieves the list of skills from the repository.
     *
     * @return the list of skills
     */
    public List<SkillDto> getSkills() {
        return SkillMapper.ListToDto(skillRepository.getSkillList());
    }

    /**
     * Retrieves the list of available collaborators who are not assigned to any team.
     *
     * @return The list of available collaborators.
     */
    public List<CollaboratorDto> getAvailableCollaborators() {
        List<CollaboratorDto> availableCollaborators = new ArrayList<>();
        List<CollaboratorDto> allCollaborators = CollaboratorMapper.toDtoList(collaboratorRepository.getCollaboratorList());

        for (CollaboratorDto collaborator : allCollaborators) {
            if (!isCollaboratorAssignedToTeam(collaborator)) {
                availableCollaborators.add(collaborator);
            }
        }

        return availableCollaborators;
    }

    /**
     * Generates a proposal for a team based on required skills, minimum and maximum team size.
     *
     * @param requiredSkills The list of skills required for the team.
     * @param minTeamSize    The minimum size of the team.
     * @param maxTeamSize    The maximum size of the team.
     * @return A Team object representing the proposed team, or null if no suitable team can be formed.
     */
    public TeamDto generateProposal(List<SkillDto> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<CollaboratorDto> allCollaborators = CollaboratorMapper.toDtoList(collaboratorRepository.getCollaboratorList());
        List<CollaboratorDto> selectedCollaborators = new ArrayList<>();
        List<SkillDto> combinedSkills = new ArrayList<>();
        List<SkillDto> domainRequiredSkills = requiredSkills;

        for (CollaboratorDto collaborator : allCollaborators) {
            if (isCollaboratorAssignedToTeam(collaborator)) {
                continue;
            }
            List<SkillDto> collaboratorSkills = collaborator.getSkills();

            for (SkillDto skill : collaboratorSkills) {
                if (!combinedSkills.contains(skill)) {
                    combinedSkills.add(skill);
                }
            }
            selectedCollaborators.add(collaborator);

            if (combinedSkills.containsAll(domainRequiredSkills) && selectedCollaborators.size() >= minTeamSize) {
                if (selectedCollaborators.size() <= maxTeamSize) {
                    Team proposedTeam = new Team(SkillMapper.listToDomain(domainRequiredSkills), CollaboratorMapper.toDomainList(selectedCollaborators), minTeamSize, maxTeamSize);
                    teamRepository.addTeam(proposedTeam); // Add team to repository

                    return TeamMapper.toDto(proposedTeam);
                }
            }
        }

        return null;
    }

    /**
     * Checks if a collaborator is already assigned to a team.
     *
     * @param collaborator The collaborator to check.
     * @return True if the collaborator is assigned to a team, false otherwise.
     */
    private boolean isCollaboratorAssignedToTeam(CollaboratorDto collaborator) {
        for (Team team : teamRepository.getTeamList()) {
            if (team.getCollaborators().contains(CollaboratorMapper.toDomain(collaborator))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a custom team with the specified skills, collaborators, and team size.
     *
     * @param requiredSkills        The list of skills required for the team.
     * @param selectedCollaborators The list of collaborators selected for the team.
     * @param minTeamSize           The minimum size of the team.
     * @param maxTeamSize           The maximum size of the team.
     * @return The created Team object.
     */
    public TeamDto createCustomTeam(List<SkillDto> requiredSkills, List<Collaborator> selectedCollaborators, int minTeamSize, int maxTeamSize) {
        Team team = new Team(SkillMapper.listToDomain(requiredSkills), selectedCollaborators, minTeamSize, maxTeamSize);
        teamRepository.addTeam(team);
        return TeamMapper.toDto(team);
    }


    public boolean checkTeamSize(int teamSize, int minTeamSize, int maxTeamSize) {
        return teamSize >= minTeamSize && teamSize <= maxTeamSize;
    }

    public boolean checkMinTeamSize(int minTeamSize) {
        return minTeamSize > 0;
    }

    public boolean checkMaxTeamSize(int maxTeamSize, int minTeamSize) {
        return maxTeamSize > minTeamSize;
    }

    public boolean checkIfCollaboratorsHaveRequiredSkills(List<Collaborator> selectedCollaborators, List<SkillDto> requiredSkills) {
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


    public List<TeamDto> getTeams() {
        return TeamMapper.ListToDto(teamRepository.getTeamList());
    }
}