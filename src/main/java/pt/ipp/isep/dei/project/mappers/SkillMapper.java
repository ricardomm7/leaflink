
package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Skill;
import pt.ipp.isep.dei.project.dto.SkillDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The SkillMapper class is responsible for mapping between the Skill domain object and the SkillDto data transfer object.
 */
public class SkillMapper implements Serializable {

    /**
     * Converts a Skill domain object to a SkillDto data transfer object.
     *
     * @param skill the Skill domain object
     * @return the SkillDto data transfer object
     */
    public static SkillDto toDto(Skill skill) {
        return new SkillDto(skill.getDesignation());
    }

    /**
     * Converts a SkillDto data transfer object to a Skill domain object.
     *
     * @param skillDto the SkillDto data transfer object
     * @return the Skill domain object
     */
    public static Skill toDomain(SkillDto skillDto) {
        return new Skill(skillDto.getDesignation());
    }

    /**
     * Converts a list of SkillDto data transfer objects to a list of Skill domain objects.
     *
     * @param skillDtos the list of SkillDto data transfer objects
     * @return the list of Skill domain objects
     */
    public static List<Skill> listToDomain(List<SkillDto> skillDtos) {
        List<Skill> skillList = new ArrayList<>();
        for (SkillDto dto : skillDtos) {
            Skill skill = toDomain(dto);
            skillList.add(skill);
        }
        return skillList;
    }

    /**
     * Converts a list of Skill domain objects to a list of SkillDto data transfer objects.
     *
     * @param skillList the list of Skill domain objects
     * @return the list of SkillDto data transfer objects
     */
    public static List<SkillDto> ListToDto(List<Skill> skillList) {
        List<SkillDto> copy = new ArrayList<>();
        for (Skill skill : skillList) {
            SkillDto skillDto = SkillMapper.toDto(skill);
            copy.add(skillDto);
        }
        return copy;
    }
}
