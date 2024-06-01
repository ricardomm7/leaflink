# US029 - Record the completion of a task

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...        | Answer                | Justification (with patterns)                      |
|:---------------|:---------------------------------------------------|:----------------------|:---------------------------------------------------|
| Step 1         | ... interacting with the actor?                    | RecordEntryUI         | Pure Fabrication                                   |
|                | ... coordinating the US?                           | RecordEntryController | Controller                                         |
|                | ... getting EntryRepository?                       | Repositories          | Pure Fabrication, low coupling and high cohesion   |
|                | ... getting agendaEntry list?                      | EntryRepository       | Information Expert, low coupling and high cohesion |
| Step 2         | ... display agendaEntry list?                      | RecordEntryUI         | Pure Fabrication                                   |
| Step 3         | ... select an assigned agendaEntry?                | RecordEntryUI         | Pure Fabrication                                   |
| Step 4         | ... confirms the selected agendaEntry?             | RecordEntryUI         | Pure Fabrication                                   |
| Step 5         | ... handles recording agendaEntry completion?      | RecordEntryController | Controller                                         |
|                | ... get TeamRepository?                            | Repositories          | Information Expert, low coupling and high cohesion |
|                | ... retrieve agendaEntry assigned to collaborator? | TeamRepository        | Information Expert, low coupling and high cohesion |
|                | ... verify existing agendaEntry?                   | EntryRepository       | Information Expert                                 |
|                | ... register agendaEntry completion?               | TeamRepository        | Creator                                            | 
|                | ... update agendaEntry progressStatus?             | AgendaEntry           | Information Expert, low coupling and high cohesion | 
| Step 6         | ... informing operation success?                   | RecordEntryUI         | Pure Fabrication                                   | 



### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* AgendaEntry

Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* RecordEntryUI
* RecordEntryController
* TeamRepository
* EntryRepository


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us029-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us029-class-diagram.svg)