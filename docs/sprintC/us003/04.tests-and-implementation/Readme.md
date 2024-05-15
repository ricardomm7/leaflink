# US003 - Register a collaborator with a job and fundamental characteristics

## 4. Tests 

**Test 1:** Check that a valid name is considered valid in the program. 

    @Test
    public void testSetName_ValidName() {
        collaborator.setName("Jane Doe");
        assertEquals("Jane Doe", collaborator.getName());
    }
	

**Test 2:** Check that an empty name is not considered valid by the program.

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setName(""));
    }


**Test 3:** Verify that a valid date of birth that is over 18 years old is considered valid by the program.

    @Test
    public void testSetBirthdate_ValidDate() {
        Date expectedDate = getDate(1980, Calendar.JANUARY, 1);

        collaborator.setBirthdate(expectedDate);

        Date actualDate = collaborator.getBirthdate();
        assertEquals(expectedDate.getTime(), actualDate.getTime());
    }

**Test 4:** Checks whether a non-adult age is considered invalid by the program.

    @Test
    public void testSetAdmissionDate_NotAdultAtAdmission() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setAdmissionDate(getDate(2002, Calendar.JANUARY, 1)));
    }

**Test 5:** Check that a null date is not considered valid.

    @Test
    public void testSetBirthdate_NullDate() {
        assertThrows(IllegalArgumentException.class, () -> collaborator.setBirthdate(null));
    }


## 5. Construction (Implementation)

### Class RegisterCollaboratorController 

```java
public void createCLB(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
    collaboratorRepository.create(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
}
```

### Class CollaboratorRepository

```java
public void create(String name, Date birthdate, int contactMobile, int taxpayerNumber, String email, String address, String zipCode, String city, String documentType, String identificationNumber, Date admissionDate, Job job) {
    Collaborator c = new Collaborator(name, birthdate, contactMobile, taxpayerNumber, email, address, zipCode, city, documentType, identificationNumber, admissionDate, job);
    if (checkForDuplicates(c)) {
        addCollaborator(c);
    }
}
```


## 6. Integration and Demo 

* A new option on the HRM menu & Admin menu, was added.


## 7. Observations

n/a