package pt.ipp.isep.dei.project.application.controller;

import pt.ipp.isep.dei.project.repository.Repositories;
import pt.ipp.isep.dei.project.repository.SkillRepository;

public class CreateSkillController {

    private final SkillRepository skillRepository;

    public CreateSkillController(){
        Repositories repositories = Repositories.getInstance();
        skillRepository = repositories.getSkillRepository();
    }

    public void createSkill(String designation){
        skillRepository.createSkill(designation);
    }
}
