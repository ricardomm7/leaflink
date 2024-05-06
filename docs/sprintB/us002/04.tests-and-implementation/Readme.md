# US002 - Register a Job

## 4. Tests 

**Test 1:** Verify if the valid title is considered valid.

	@Test
    public void testSetTitle_ValidTitle() {
        String validTitle = "Software Engineer";
        Job job = new Job(validTitle);
        assertEquals(validTitle, job.getTitle());
    }
	

**Test 2:** Check that it is not possible to create an instance of the Job class with an empty title. 

	@Test
    public void testSetTitle_InvalidTitle_Empty() {
        assertThrows(IllegalArgumentException.class, () -> new Job(""));
    }


**Test 3:** Check that it is not possible to create an instance of the Job class with a title containing blanks.

    @Test
    public void testSetTitle_InvalidTitle_Whitespace() {
        assertThrows(IllegalArgumentException.class, () -> new Job("   "));
    }


**Test 4:** Check that it is not possible to create an instance of the Job class with a title with symbols.

    @Test
    public void testSetTitle_InvalidTitle_SpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new Job("Software Engineer!"));
    }



## 5. Construction (Implementation)

### Class CreateJobController 

```java
public void createJob(String title) {
    jobRepository.createJob(title);
}
```

### Class JobRepository

```java
public void createJob(String title) {
    Job j = new Job(title);
    if (checkForDuplicates(j)) {
        addJob(j);
    }
}
```


## 6. Integration and Demo 

* A new option on the HRM menu & Admin menu, was added.


## 7. Observations

n/a