package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.domain.Collaborator;
import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;
import pt.ipp.isep.dei.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class CreateTeamController {
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;

    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        this.teamRepository = repositories.getTeamRepository();
        this.skillRepository = repositories.getSkillRepository();
    }

    public List<Skill> getSkills () {
        return skillRepository.getSkillList();
    }

    public void createTeam(List<Collaborator> collaborators, List<Skill> skills, int minTeamSize, int maxTeamSize) {
        List<Collaborator> matchedCollaborator = matchSkills(collaborators, skills);
        teamRepository.generateProposal(matchedCollaborator, minTeamSize, maxTeamSize);
    }

    private List<Collaborator> matchSkills(List<Collaborator> collaborators, List<Skill> skills) {
        return new ArrayList<>();
    }
}
