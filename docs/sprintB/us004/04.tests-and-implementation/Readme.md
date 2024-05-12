# US004 - Assign skills to a collaborator 

## 4. Tests 

**Test 1:** Checks that the assignSkills() method correctly assigns skills to a collaborator and that the getSkills() method retrieves the assigned skills accurately.

    @Test
    public void testAssignSkillsAndGetSkills() {
        Skill skill1 = new Skill("Java Programming");
        Skill skill2 = new Skill("Project Management");
        collaborator.assignSkills(new Skill[] {skill1, skill2});

        List<Skill> assignedSkills = collaborator.getSkills();

        assertTrue(assignedSkills.contains(skill1));
        assertTrue(assignedSkills.contains(skill2));
        assertEquals(2, assignedSkills.size());
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