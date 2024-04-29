# US002 - Create a Job 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer              | Justification (with patterns)                                                             |
|:---------------|:----------------------------------------------|:--------------------|:------------------------------------------------------------------------------------------|
| Step 2		       | 	... interacting with the actor?              | CreateJobUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class. |
| 		             | 	... coordinating the US?                     | CreateJobController | Controller  (Model-View-Controller)                                                       |
| 	  	Step 5	    | 	... instantiating a new Job?                 | JobRepository       | Creator Pattern                                                                           |
| 		             | 	...saving the inputted data?                 | Job                 | Information Expert                                                                        |
| 		             | 	... saving the job?                          | JobRepository       | Repository Pattern                                                                        |
| 		             | 	... validating all data (global validation)? | JobRepository       | Information Expert                                                                        | 
| 		             | 	... informing operation success?             | CreateJobUI         | Model-View-Controller                                                                     | 
|                | ... have all the repositories?                | Repositories        | Repository Pattern                                                                        |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Job

Other software classes (i.e. Pure Fabrication) identified: 

* CreateJobUI  
* CreateJobController
* JobRepository
* Repositories


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us002-sequence-diagram-split.svg)

**Create Job**

![Sequence Diagram - Partial - Create Job](svg/us002-sequence-diagram-partial-create-job.svg)

**Get Job Repository**

![Sequence Diagram - Partial - Create Job](svg/us002-sequence-diagram-partial-get-job-repository.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)