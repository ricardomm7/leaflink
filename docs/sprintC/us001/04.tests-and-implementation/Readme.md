# US001 - Register a Skill

## 4. Tests 

**Test 1:** Check if a valid designation is considered valid by the program. 

    @Test
    public void testValidSkillDesignation() {
        String validDesignation = "Programming";

        Skill skill = new Skill(validDesignation);

        assertEquals(validDesignation, skill.getDesignation());
    }

**Test 2:** Check if an invalid designation (with special characters) is considered invalid by the program.

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithSpecialCharacters() {
        String invalidDesignation = "Progra**mming";

        new Skill(invalidDesignation);
    }

**Test 3:** Check if an empty designation is considered invalid by the program.

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithEmptyString() {
        String invalidDesignation = "";

        new Skill(invalidDesignation);

    }

**Test 4:** Check whether a designation full of spaces is considered invalid by the program.

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSkillDesignationWithOnlySpaces() {
        String invalidDesignation = "    ";

        new Skill(invalidDesignation);
    }


## 5. Construction (Implementation)

### Class CreateSkillController 

```java
public void createSkill(String designation){
    skillRepository.createSkill(designation);
}
```

### Class SkillRepository

```java
public void createSkill (String designation){
    Skill skill = new Skill(designation);
    if (checkForDuplicates(skill)) {
        addSkill(skill);
    }
}
```


## 6. Integration and Demo 

* A new option on the HRM menu and Admin menu was added.

## 7. Observations

n/a