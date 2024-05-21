package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;

/**
 * The type Skill mapper.
 */
public class SkillMapper {

    /**
     * To dto skill dto.
     *
     * @param skill the skill
     * @return the skill dto
     */
    public static SkillDto toDto (Skill skill){
      SkillDto s = new SkillDto(skill.getDesignation());
      return s;
    }

    /**
     * To domain skill.
     *
     * @param skill the skill
     * @return the skill
     */
    public static Skill toDomain (SkillDto skill){
      Skill s = new Skill(skill.getDesignation());
      return s;
    }
}
