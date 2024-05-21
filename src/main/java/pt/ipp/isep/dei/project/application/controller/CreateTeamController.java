package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.CollaboratorDto;
import pt.ipp.isep.dei.project.dto.SkillDto;
import pt.ipp.isep.dei.project.dto.TeamDto;
import pt.ipp.isep.dei.project.mappers.CollaboratorMapper;
import pt.ipp.isep.dei.project.mappers.SkillMapper;
import pt.ipp.isep.dei.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.HashSet;
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
     * Initializes the TeamRepository and SkillRepository instances.
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
    public List<SkillDto> getSkillsDto() {
        return skillRepository.getSkillDtoList();
    }

    public List<CollaboratorDto> getCollaboratorList() {
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Generates a proposal for a team based on required skills, minimum and maximum team size.
     *
     * @param requiredSkills The list of skills required for the team.
     * @param minTeamSize    The minimum size of the team.
     * @param maxTeamSize    The maximum size of the team.
     * @return A list of selected collaborators for the team, or an empty list if no suitable team can be formed.
     */
    public List<CollaboratorDto> generateProposal(List<SkillDto> requiredSkills, int minTeamSize, int maxTeamSize) {
        List<CollaboratorDto> allCollaborators = collaboratorRepository.getCollaboratorList();
        List<CollaboratorDto> selectedCollaboratorsDto = new ArrayList<>();
        List<SkillDto> combinedSkillsDto = new ArrayList<>();

        for (CollaboratorDto collDto : allCollaborators) {
            if (isCollaboratorAssignedToTeam(collDto)) {
                continue;
            }
            List<SkillDto> collaboratorSkillsDto = collDto.getSkills();
            for (SkillDto skillDto : collaboratorSkillsDto) {
                if (!combinedSkillsDto.contains(skillDto)) {
                    combinedSkillsDto.add(skillDto);
                }
            }
            selectedCollaboratorsDto.add(collDto);
            if (new HashSet<>(combinedSkillsDto).containsAll(requiredSkills) && selectedCollaboratorsDto.size() >= minTeamSize) {
                teamRepository.addTeam(new TeamDto(SkillMapper.listToDomain(requiredSkills), CollaboratorMapper.toDomainList(selectedCollaboratorsDto), minTeamSize, maxTeamSize));
                return selectedCollaboratorsDto;
            }
        }

        return new ArrayList<>();
    }

    /**
     * Checks if a collaborator is already assigned to a team.
     *
     * @param collaborator The collaborator to check.
     * @return true if the collaborator is already assigned to a team, false otherwise.
     */
    private boolean isCollaboratorAssignedToTeam(CollaboratorDto collaborator) {
        List<TeamDto> teamListDto = teamRepository.getTeamDtoList();
        for (TeamDto teamDto : teamListDto) {
            if (teamDto.getCollaboratorsDtoList().contains(collaborator)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the team of collaborators.
     *
     * @param allCollaboratorsDto The list of collaborators to be saved as a team.
     */
    public void saveTeam(List<CollaboratorDto> allCollaboratorsDto) {
        TeamDto team = new TeamDto(CollaboratorMapper.toDomainList(allCollaboratorsDto));
        teamRepository.addTeam(team);
    }

    /**
     * Retrieves the list of saved teams.
     *
     * @return The list of saved teams.
     */
    public List<TeamDto> getSavedTeams() {
        return teamRepository.getTeamDtoList();
    }

}