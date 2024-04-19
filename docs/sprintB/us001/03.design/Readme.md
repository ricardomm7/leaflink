# US001 - Create a Skill 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                        | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | CreateSkillUI                 | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | CreateSkillController         | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Task?                | skillRepository               | Repository Pattern                                                                                            |
| 			  		        | ... knowing the user using the system?        | N/A                           | N/A                                                                                                           |
| Step 2  		     | 	...saving the inputted data?                 | CreateSkillUI and CreateSkill | MVC  (Model-View-Controller)                                                                                  |
| Step 3  		     | 	... saving the skill?                        | skillRepository               | Repository                                                                                                    |
| Step 4  		     | 	... validating all data (global validation)? | skillRepository               |                                                                                                               | 
| Step 5  		     | 	... informing operation success?             | CreateSkillUI                 | Is responsible for user interactions.                                                                         | 
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Skill

Other software classes (i.e. Pure Fabrication) identified: 

* CreateSkillUI
* CreateSkillController
* skillRepository


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us001-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us001-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us001-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us001-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us001-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us001-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us001-class-diagram.svg)