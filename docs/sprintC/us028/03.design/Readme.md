# US028 - List the entrys assigned to myself


## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...         | Answer             | Justification (with patterns)                                                                                                                                                    |
|:---------------|:----------------------------------------------------|:-------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                     | ListTaskSpaceUI    | Pure Fabrication: There is no need to assign this responsibility to any existing class in the Domain Model. The UI class is a utility class for handling user interaction.       |
|                | ... coordinating the US?                            | ListTaskController | Controller: ListTaskController is responsible for coordinating and controlling the flow of interaction, applying the Controller pattern.                                         |
| Step 2         | ... display date selection fields?                  | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI displays the input fields for date selection, promoting low coupling by separating UI logic from domain logic.                                 |
| Step 3         | ... selects the beginning and end date?             | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI handles the user's date selection, promoting low coupling by separating UI logic from domain logic.                                            |
| Step 4         | ... selects the type of status he wants to consult? | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI handles the user's status type selection, promoting low coupling by separating UI logic from domain logic.                                     |
| Step 5         | ... asks for confirmation?                          | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI asks for user's confirmation before proceeding with the task list creation, ensuring data integrity and adhering to the Creator pattern.       |
| Step 6         | ... confirms that he wants to create the list?      | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI handles the user's confirmation, promoting low coupling by separating UI logic from domain logic.                                              |
| Step 7         | ... handles the creation of the task list?          | ListTaskController | Controller: ListTaskController manages the task list creation process, ensuring high cohesion and low coupling by encapsulating related functionality.                           |
|                | ... get EntryRepository?                            | Repositories       | Pure Fabrication:  Repositories is responsible for providing access to various repositories. It promotes low coupling and high cohesion by encapsulating data access logic.      |
|                | ... verify existing entries?                        | EntryRepository    | Information Expert: EntryRepository performs global validation, adhering to the Protected Variation pattern by encapsulating data access.                                        |
|                | ... create a task list?                             | EntryRepository    | Creator: Task list is directly created by EntryRepository, which encapsulates the logic for managing entries.                                                                    |
|                | ... validating all data (local validation)?         | Entry              | Information Expert: Entry performs local validation on its attributes, adhering to the Information Expert pattern by encapsulating its own data validation logic.                |
|                | ... validating all data (global validation)?        | EntryRepository    | Information Expert: EntryRepository performs global validation, following the Protected Variation pattern by encapsulating validation rules.                                     |
|                | ... stores task list data?                          | EntryRepository    | Repository Pattern: EntryRepository is responsible for persisting and managing task list data, applying the Low Coupling pattern by decoupling data storage from business logic. |
| Step 8         | ... informing operation success?                    | ListTaskSpaceUI    | Pure Fabrication: ListTaskSpaceUI handles user interaction and displays success/error messages, promoting low coupling and high cohesion by encapsulating UI logic.              |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle


Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* RegisterVehicleUI
* VehicleRepository
* RegisterVehicleController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us021-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us021-class-diagram.svg)