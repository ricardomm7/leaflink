# US005 - Generate a team 

## 4. Tests 

**Test 1:** Check that the getSkills method returns the correct list of skills.

    @Test
    void getSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Gardener"));
        skills.add(new Skill("Driving Licence"));
        Team team = new Team(skills, new ArrayList<>(), 3, 5);
        assertEquals(skills, team.getSkills());
    }
	

**Test 2:** Check that the getCollaborators method returns the correct list of collaborators.

	@Test
    void getCollaborators() {
        List<Collaborator> collaborators = new ArrayList<>();
        Collaborator collaborator1 = new Collaborator("John Doe", getDate(1990, Calendar.JANUARY, 1), 123456789, 123456789, "john.doe@example.com", "123 Main St", "1234-985", "SomeCity", "ID", "123ABC", getDate(2020, Calendar.JANUARY, 1), new Job("Software Engineer"));
        Collaborator collaborator2 = new Collaborator("Jane Doe", getDate(1995, Calendar.AUGUST, 20), 987654321, 987654321, "jane.doe@example.com", "456 Oak St", "5432-100", "AnotherCity", "Passport", "XYZ789", getDate(2019, Calendar.JUNE, 1), new Job("Designer"));
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(new ArrayList<>(), collaborators, 3, 5);

        assertEquals(collaborators, team.getCollaborators());
    }

**Test 3:** Check that the getMinTeamSize method returns the correct minimum team size.
    
    @Test
    void getMinTeamSize() {
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);
        assertEquals(3, team.getMinTeamSize());
    }

**Test 4:** Check that the getMaxTeamSize method returns the correct maximum team size.
    
    @Test
    void getMaxTeamSize() {
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);
        assertEquals(5, team.getMaxTeamSize());
    }

**Test 5:** Check that a team can be added to the repository.
    
    @Test
    void addTeam() {
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);
        TeamRepository teamRepository = new TeamRepository();
        teamRepository.addTeam(team);
        List<Team> teamList = teamRepository.getTeamList();
        assertTrue(teamList.contains(team));
    }

**Test 6:** Check that the getTeamList method returns the correct list of teams.

    @Test
    void getTeamList() {
        TeamRepository teamRepository = new TeamRepository();
        List<Team> initialTeamList = teamRepository.getTeamList();
        assertTrue(initialTeamList.isEmpty());
        Team team = new Team(new ArrayList<>(), new ArrayList<>(), 3, 5);
        teamRepository.addTeam(team);
        List<Team> updatedTeamList = teamRepository.getTeamList();
        assertTrue(updatedTeamList.contains(team));
    }

**Test 7:**


## 5. Construction (Implementation)

### Class CreateTeamController 

```java
public List<Collaborator> generateProposal(List<Skill> requiredSkills, int minTeamSize, int maxTeamSize) {
    List<Collaborator> allCollaborators = collaboratorRepository.getCollaboratorList();
    List<Collaborator> selectedCollaborators = new ArrayList<>();
    List<Skill> combinedSkills = new ArrayList<>();
        for (Collaborator collaborator : allCollaborators) {
            List<Skill> collaboratorSkills = collaborator.getSkills();
            for (Skill skill : collaboratorSkills) {
                if (!combinedSkills.contains(skill)) {
                    combinedSkills.add(skill);
                }
            }
            selectedCollaborators.add(collaborator);
            if (combinedSkills.containsAll(requiredSkills) && selectedCollaborators.size() >= minTeamSize) {
                teamRepository.addTeam(new Team(requiredSkills, selectedCollaborators, minTeamSize, maxTeamSize));
                return selectedCollaborators;
            }
        }

        System.out.println("Warning: Insufficient number of collaborators meeting the required skills.");
        return new ArrayList<>();
        }
}
```

### Class TeamRepository

```java
public void addTeam(Team team) {
    teamList.add(team);
}

public List<Team> getTeamList(){
        return new ArrayList<>(teamList);
}

```


## 6. Integration and Demo 

* This feature adds a new option on the HRM menu for generating team proposals automatically

## 7. Observations

n/a