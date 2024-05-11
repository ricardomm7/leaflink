# US004 - Assign skills to a collaborator 

## 4. Tests 

**Test 1:** Check that a collaborator can be assigned one or more skills successfully.

	@Test
    public void testAssignSkills_Success() {
        Collaborator collaborator = new Collaborator("John Doe", getDate(1990, Calendar.JANUARY, 1), 123456789, 123456789, "john.doe@example.com", "123 Main St", "12345", "Anytown", "ID", "123456789", getDate(2020, Calendar.JANUARY, 1), new Job("Developer"));

        Skill skill1 = new Skill("Java Programming");
        Skill skill2 = new Skill("Database Management");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        collaborator.assignSkills(skills);

        assertEquals(2, collaborator.getSkills().size());
        assertTrue(collaborator.getSkills().contains(skill1));
        assertTrue(collaborator.getSkills().contains(skill2));
    }
	

**Test 2:** Check that assigning duplicate skills to a collaborator is handled properly. 

	@Test
    public void testAssignSkills_DuplicateSkills() {
        Collaborator collaborator = new Collaborator("John Doe", getDate(1990, Calendar.JANUARY, 1), 123456789, 123456789, "john.doe@example.com", "123 Main St", "12345", "Anytown", "ID", "123456789", getDate(2020, Calendar.JANUARY, 1), new Job("Developer"));

        Skill skill1 = new Skill("Java Programming");

        List<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        collaborator.assignSkills(skills);
        collaborator.assignSkills(skills); // Try assigning the same skills again

        assertEquals(1, collaborator.getSkills().size()); // Ensure only one instance of the skill is assigned
    }


## 5. Construction (Implementation)

### Class AssignSkillController

```java
public void assignSkill(Collaborator collaborator, List<Skill> skills) {
        collaborator.assignSkills(skills);
        collaboratorRepository.updateCollaborator(collaborator);
        }
```

### Class CollaboratorRepository

```java
public void updateCollaborator(Collaborator collaborator) {
    for (int i = 0; i < collaboratorList.size(); i++) {
        if (collaboratorList.get(i).getTaxpayerNumber() == collaborator.getTaxpayerNumber()) {
        collaboratorList.set(i, collaborator);
        return;
        }
    }
    throw new IllegalArgumentException("Collaborator not found in the repository.");
    }
```
### Class Collaborator

```java
public void assignSkills(Skill[] selectedSkills) {
    for (Skill skill : selectedSkills) {
        if (!skills.contains(skill)) {
        skills.add(skill);
        }
    }
}

public void addSkill(Skill skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }
```

## 6. Integration and Demo 

* The functionality to assign skills to a collaborator has been integrated into the HRM menu and Admin menu.
* Demo: The Assign Skill option is accessible through the HRM and Admin menus, allowing users to assign skills to collaborators.

## 7. Observations

n/a