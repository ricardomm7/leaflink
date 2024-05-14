package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class TeamTest {

    @Test
    void getSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Gardener"));
        skills.add(new Skill("Driving Licence"));

        Team team = new Team(skills, new ArrayList<>(), 3, 5);

        assertEquals(skills, team.getSkills());
    }

    @Test
    void getCollaborators() {
        List<Collaborator> collaborators = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("John Doe", new Date(1990, 2, 1), 123456789, 123456789, "john.doe@example.com", "123 Main St", "1234-985", "SomeCity", "ID", "123ABC", new Date(2020, 2, 1), new Job("Software Engineer"));
        Collaborator collaborator2 = new Collaborator("Jane Doe", new Date(1995, 8, 20), 987654321, 987654321, "jane.doe@example.com", "456 Oak St", "5432-100", "AnotherCity", "Passport", "XYZ789", new Date(2019, 6, 1), new Job("Designer"));
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(new ArrayList<>(), collaborators, 3, 5);

        assertEquals(collaborators, team.getCollaborators());
    }

    @Test
    void getMinTeamSize() {

        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        assertEquals(3, team.getMinTeamSize());
    }

    @Test
    void getMaxTeamSize() {

        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);

        assertEquals(5, team.getMaxTeamSize());
    }
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

}