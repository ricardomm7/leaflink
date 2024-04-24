# US004 - Assign Skills to a Collaborator 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                 | Justification (with patterns)                                                                                 |
|----------------|:----------------------------------------------|:-----------------------|---------------------------------------------------------------------------------------------------------------|
| Step 1 	       | ... interacting with the actor?               | AssignSkillsUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ... coordinating the US?                      |                        |                                                                                                               |
|                | ... knowing the user using the system?        |                        |                                                                                                               |
|                | ... showing a list of collaborators?          |                        |                                                                                                               |
| Step 2         | ... asking to select a collaborator?          |                        |                                                                                                               |
|                | ... selecting a collaborator?                 |                        |                                                                                                               |
| Step 3         | ... showing list of available skills?         |                        |                                                                                                               |
| Step 4         | ... asking to select one or more skills?      |                        |                                                                                                               |
|                | ... selecting desired skills?                 |                        |                                                                                                               |
|                | ... assigning skills to a collaborator?       |                        |                                                                                                               |                |                                             |                        |                                                                                                               |
| Step 7         | ... saving the updated collaborator's skills? |                        |                                                                                                               |
| Step 8         | ... informing of operation success?           |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |
|                |                                               |                        |                                                                                                               |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Collaborator
* AssignSkillsUI
* AssignSkillsController
* CollaboratorRepository
* SkillsRepository

Other software classes (i.e. Pure Fabrication) identified: 

* AssignSkillsUI  
* AssignSkillsController


## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us004-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us004-sequence-diagram-split.svg)

**Assign Skill**

![Sequence Diagram - Partial - Get Task Category List](svg/us004-sequence-diagram-partial-assign-skill.svg)

**Get Collaborator List**

![Sequence Diagram - Partial - Get Task Category List](svg/us004-sequence-diagram-partial-get-collaborator-list.svg)

**Get Skills List**

![Sequence Diagram - Partial - Get Task Category List](svg/us004-sequence-diagram-partial-get-skill-list.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us004-class-diagram.svg)