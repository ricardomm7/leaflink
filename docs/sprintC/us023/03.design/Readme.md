# US006 - Register a Vehicle 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question: Which class is responsible for...       | Answer               | Justification (with patterns)                                                                                  |
|:---------------|:--------------------------------------------------|:---------------------|:---------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                   | AssignTeamUI         | Pure Fabrication                                                                                               |
|                | ... coordinating the US?                          | AssignTeamController | Controller:                                                                                                    |
|                | ... getting EntryRepository?                      | Repositories         | Pure Fabrication, low coupling and high cohesion                                                               |
|                | ... getting TeamRepository?                       | Repositories         | Pure Fabrication, low coupling and high cohesion                                                               |
|                | ... getting agendaEntry list?                     | EntryRepository      | Information Expert, low coupling and high cohesion                                                             |
|                | ... transforms the agendaEntry in agendaEntryDto? | AgendaEntryMapper    | Information Expert, creator, low coupling                                                                      |
|                | ... getting Team list?                            | TeamRepository       | Information Expert, low coupling and high cohesion                                                             |
|                | ... transforms the team in teamDtoList?           | TeamMapper           | Information Expert, creator, low coupling                                                                      |
| Step 2         | ... show the entries in the agenda?               | AssignTeamUI         | Pure Fabrication                                                                                               |
| Step 3         | ... selecting the entry?                          | AssignTeamUI         | Pure Fabrication                                                                                               |
| Step 4         | ... show the teams available?                     | AssignTeamUI         | Pure Fabrication                                                                                               |
| Step 5         | ... selecting the team?                           | AssignTeamUI         | Pure Fabrication                                                                                               |
| Step 6         | ... show data and request confirmation?           | AssignTeamUI         | Pure Fabrication                                                                                               |
| Step 7         | ... confirm data?                                 | AssignTeamUI         | Pure Fabrication                                                                                               |
|                | ... transforms the teamDtoList in team?           | TeamMapper           | Information Expert, creator, low coupling and high cohesion                                                    |
|                | ... transforms the agendaEntryDto in agendaEntry? | AgendaEntryMapper    | Information Expert, creator, low coupling and high cohesion                                                    |
|                | ... updating the agendaEntry?                     | EntryRepository      | Creator: Vehicle is directly created by vehicleRepository, which encapsulates the logic for managing vehicles. |
|                | ... notifying the team (send notifications)?      | NotificationService  | Pure Fabrication, low coupling and high cohesion                                                               |
| Step 8         | ... informing operation success?                  | AssignTeamUI         | Pure Fabrication                                                                                               | 


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* agendaEntry
* agendaEntryDto


Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* AssignTeamUI
* AssignTeamController
* EntryRepository
* TeamRepository
* AgendaEntryMapper
* TeamMapper
* NotificationService



## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us023-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)