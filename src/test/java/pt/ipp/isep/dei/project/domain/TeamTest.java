package pt.ipp.isep.dei.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Collaborator collaborator1 = new Collaborator("John Doe", LocalDate.of(1990, 2, 1), 123456789, 123456789, "john.doe@example.com", "123 Main St", "1234-985", "SomeCity", DocumentType.PASSPORT, "123ABC000", LocalDate.of(2020, 2, 1), new Job("Software Engineer"));
        Collaborator collaborator2 = new Collaborator("Jane Doe", LocalDate.of(1995, 8, 20), 987654321, 987654321, "jane.doe@example.com", "456 Oak St", "5432-100", "AnotherCity", DocumentType.PASSPORT, "XYZ789000", LocalDate.of(2019, 6, 1), new Job("Designer"));
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

}