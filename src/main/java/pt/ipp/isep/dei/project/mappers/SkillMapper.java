package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Skill mapper.
 */
public class SkillMapper implements Serializable {

    /**
     * To dto skill dto.
     *
     * @param skill the skill
     * @return the skill dto
     */
    public static SkillDto toDto(Skill skill) {
        return new SkillDto(skill.getDesignation());
    }

    /**
     * To domain skill.
     *
     * @param skill the skill
     * @return the skill
     */
    public static Skill toDomain(SkillDto skill) {
        return new Skill(skill.getDesignation());
    }

    /**
     * List to domain list.
     *
     * @param skillDtos the skill dtos
     * @return the list
     */
    public static List<Skill> listToDomain(List<SkillDto> skillDtos) {
        List<Skill> skillList = new ArrayList<>();
        for (SkillDto dto : skillDtos) {
            Skill skill = toDomain(dto);
            skillList.add(skill);
        }
        return skillList;
    }

    public static List<SkillDto> ListToDto(List<Skill> skillList) {
        List<SkillDto> copy = new ArrayList<SkillDto>();
        for (Skill skill : skillList) {
            SkillDto skillDto = SkillMapper.toDto(skill);
            copy.add(skillDto);
        }
        return copy;
    }
}
