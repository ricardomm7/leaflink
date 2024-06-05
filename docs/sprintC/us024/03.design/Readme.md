# US024 - Postpone an agendaEntry in the Agenda to a future date.

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...       | Answer                        | Justification (with patterns)                               |
|:---------------|:--------------------------------------------------|:------------------------------|:------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                   | PostponeAgendaEntryUI         | Pure Fabrication                                            |
|                | ... coordinating the US?                          | PostponeAgendaEntryController | Controller                                                  |
|                | ... getting EntryRepository?                      | Repositories                  | Pure Fabrication, low coupling and high cohesion            |
|                | ... getting agendaEntry list?                     | EntryRepository               | Information Expert, low coupling and high cohesion          |
|                | ... transforms the agendaEntry in agendaEntryDto? | AgendaEntryMapper             | Information Expert, creator, low coupling                   |
| Step 2         | ... show the agendaEntry list?                    | PostponeAgendaEntryUI         | Pure Fabrication                                            |
| Step 3         | ... selecting a agendaEntry ?                     | PostponeAgendaEntryUI         | Pure Fabrication                                            |
| Step 4         | ... requesting new date for the agendaEntry?      | PostponeAgendaEntryUI         | Pure Fabrication                                            |
| Step 5         | ... submitting the new date?                      | PostponeAgendaEntryController | Controller                                                  |
| Step 6         | ... displaying agendaEntry  data?                 | PostponeAgendaEntryUI         | Pure Fabrication                                            |
| Step 7         | ... confirms data                                 | PostponeAgendaEntryUI         | Pure Fabrication                                            |
|                | ... validate submitted data?                      | EntryRepository               | Information Expert                                          |
|                | ... transforms the agendaEntryDto in agendaEntry? | AgendaEntryMapper             | Information Expert, creator, low coupling and high cohesion |
|                | ... postponing the agendaEntry?                   | EntryRepository               | Information Expert, low coupling and high cohesion          | 
|                | ... create new agendaEntry?                       | EntryRepository               | creator                                                     |
|                | ... verify local data?                            | AgendaEntry                   | Information Expert                                          |
|                | ... notifying the team?                           | TeamRepository                | Information Expert                                          | 
|                | ... sending notifications?                        | NotificationService           | Pure Fabrication, low coupling and high cohesion            |
| Step 8         | ... informing operation success?                  | PostponeAgendaEntryUI         | Pure Fabrication                                            | 


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* agendaEntry
* agendaEntryDto


Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* PostponeAgendaEntryUI
* PostponeAgendaEntryController
* EntryRepository
* TeamRepository
* NotificationService
* AgendaEntryMapper



## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us024-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us024-class-diagram.svg)