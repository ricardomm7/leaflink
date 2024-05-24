package pt.ipp.isep.dei.project.mappers;

import pt.ipp.isep.dei.project.domain.Team;
import pt.ipp.isep.dei.project.dto.TeamDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeamMapper implements Serializable {

    public static TeamDto toDto(Team team) {
        return new TeamDto(team.getSkills(), team.getCollaborators(), team.getMinTeamSize(), team.getMaxTeamSize());
    }

    public static Team toDomain(TeamDto teamDto) {
        return new Team(teamDto.getSkills(), teamDto.getCollaboratorsDtoList(), teamDto.getMinTeamSize(), teamDto.getMaxTeamSize());
    }

    public static List<Team> listToDomain(List<TeamDto> teamDtos) {
        List<Team> teamList = new ArrayList<>();
        for (TeamDto dto : teamDtos) {
            Team team = toDomain(dto);
            teamList.add(team);
        }
        return teamList;
    }

    public static List<TeamDto> ListToDto(List<Team> teamList) {
        List<TeamDto> copy = new ArrayList<>();
        for (Team team : teamList) {
            TeamDto teamDto = TeamMapper.toDto(team);
            copy.add(teamDto);
        }
        return copy;
    }
}
