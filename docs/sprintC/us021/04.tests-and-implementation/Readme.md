# US021 - Add a new toDoEntry to the To-Do List

## 4. Tests 

**Test 1:** Check if it is possible to register a To-Do Entry with valid parameters.

    @Test
    public void testToDoEntryConstructorValidInput() {
        // Arrange
        String title = "Gardening";
        String description = "Gardening the backyard";
        int duration = 2;
        UrgencyStatus urgencyStatus = UrgencyStatus.valueOf("Low");
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));

        // Act
        ToDoEntry entry = new ToDoEntry(title, description, duration, urgencyStatus, gs);

        // Assert
        assertEquals(title, entry.getTitle());
        assertEquals(description, entry.getDescription());
        assertEquals(duration, entry.getDuration());
        assertEquals(urgencyStatus, entry.getUrgencyStatus());
        assertEquals(gs, entry.getGreenSpace());
    }
**Test 2:** Check if it is possible to register a To-Do Entry with valid parameters.

        @Test
    public void testToDoEntryConstructorInvalidInput() {
        // Arrange
        String title = null; // Invalid title
        String description = null; // Invalid description
        int duration = -1; // Invalid duration
        UrgencyStatus urgencyStatus = UrgencyStatus.HIGH;
        GreenSpace gs = new GreenSpace("Garden", GreenSpaceType.GARDEN, 12, "a@a.com", new Address("Rua", "4000-007", "Porto"));


        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new ToDoEntry(title, description, duration, urgencyStatus, gs);
        });

        assertEquals("Error when setting the To-Do Entry attributes.", exception.getMessage());
    }

**Test 3:** Check if it is possible to register a To-Do Entry with an empty name. 

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setTitle(""));
    }

**Test 4:** Check if it is possible to register a To-Do Entry with an empty description.

    @Test
    public void testSetDescription_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> toDoEntry.setDescription("###Gardening$&"));
    }


## 5. Construction (Implementation)

### Class RegisterToDoEntryController

```java

public RegisterToDoEntryController() {
    repositories = Repositories.getInstance();
    greenSpaceRepository = repositories.getGreenSpaceRepository();
    entryRepository = repositories.getEntryRepository();
}

public void createNewToDoEntry(String title, String description, int duration, UrgencyStatus urg, GreenSpaceDto greenSpaceDto) {
    ToDoEntry newEntry = new ToDoEntry(title, description, duration, urg, GreenSpaceMapper.toDomain(greenSpaceDto));
    entryRepository.create(newEntry);
}

```

### EntryRepository

````java

public EntryRepository() {
    agendaEntryList = new ArrayList<>();
    toDoEntryList = new ArrayList<>();
}

public void create(ToDoEntry dto) {
    if (checkForDuplicates(dto)) {
        addToDoEntry(dto);
    } else {
        throw new IllegalArgumentException("There is already a ToDoEntry with the same description.");
    }
}

private boolean checkForDuplicates(ToDoEntry toDoEntry) {
    return toDoEntryList.stream()
            .noneMatch(e -> e.getDescription().equalsIgnoreCase(toDoEntry.getDescription()));
}

private void addToDoEntry(ToDoEntry toDoEntry) {
    toDoEntryList.add(toDoEntry);
}

````

### ToDoEntry

````java

public class ToDoEntry implements Serializable {

    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpace greenSpace;
    
    public ToDoEntry(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpace greenSpace) {
        try {
            setTitle(title);
            setDescription(description);
            setDuration(duration);
            setDegreeOfUrgency(urgencyStatus);
            setGreenSpace(greenSpace);
        } catch (Exception e) {
            ShowError.showAlert("To-Do Entry", e.getMessage(), "Error when setting the To-Do Entry attributes.");
        }
    }
}
````


## 6. Integration and Demo 

* A new option on the VFM menu options was added.

* For demo purposes some vehicles are bootstrapped while system starts.


## 7. Observations

n/a