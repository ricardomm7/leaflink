# US022 - Add a new toDoEntry in the Agenda 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...                  | Answer                   | Justification (with patterns)                    |
|:---------------|:-------------------------------------------------------------|:-------------------------|:-------------------------------------------------|
| Step 1         | ... interacting with the actor?                              | AddAgendaEntryUI         | Pure Fabrication.                                |
|                | ... coordinating the US?                                     | AddAgendaEntryController | Controller.                                      |
|                | ... knowing the user of the system?                          | ApplicationSession       | Information Expert.                              |
|                | ... transforming the domain objects to DTO?                  | ToDoEntryMapper          | Low coupling                                     |
| Step 2         | ... getting the entry repository?                            | Repositories             | Information Expert, High cohesion, Low coupling. |
|                | ... getting all the to do entries and return them as a list? | EntryRepository          | Information Expert.                              |
|                | ... showing the entries available for selection?             | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 3         | ... handling the user selecting an entry?                    | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 4         | ... requesting to define a starting date?                    | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 5         | ... handling the user defining a starting date?              | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 6         | ... showing the progress status available for selection?     | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 7         | ... handling the user selecting a progress status?           | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 8         | ... showing the confirmation info?                           | AddAgendaEntryUI         | Pure Fabrication.                                |
| Step 9         | ... validating all data (local validation)?                  | AgendaEntry              | Information Expert.                              |
|                | ... validating all data (global validation)?                 | EntryRepository          | Information Expert.                              |
|                | ... saving the new agenda entry?                             | EntryRepository          | Information Expert.                              |
| Step 10        | ... informing of operation success?                          | AddAgendaEntryUI         | Pure Fabrication.                                |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* AgendaEntry



Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* AddAgendaEntryUI
* AddAgendaEntryController
* EntryRepository
* ToDoEntryDto
* ToDoEntryMapper




## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us022-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us022-class-diagram.svg)