# US006 - Create a Task 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...    | Answer                    | Justification (with patterns)                                                                                 |
|:---------------|:-----------------------------------------------|:--------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | ... interacting with the actor?                | ListMaintenanceUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	  		          | ... coordinating the US?                       | ListMaintenanceController | Controller                                                                                                    |
| 			  		        | ... instantiating a new MaintenanceReport?     | MaintenanceReport         | Creator (Rule 1): in the DM MaintenanceReport is directly created.                                            |
| 			  		        | ... knowing the user using the system?         | UserSession               | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                        | Organization              | IE: knows/has its own Employees                                                                               |
| 			  		        | 							                                        | Employee                  | IE: knows its own data (e.g. email)                                                                           |
| Step 2  		     | ... knowing the vehicles list							           | VehicleRepository         | Repository (Rule2): responsible for storing and retrieving vehicle data.                                      |
| Step 3  		     | 	...saving the inputted data?                  | MaintenanceReport         | IE: object created in step 1 has its own data.                                                                |
| Step 4  		     | 	...knowing the vehicles needing maintenance?  | System                    | IE: Vehicles needing maintenance are determined by system-wide logic.                                         |
| Step 5  		     | 	... saving the maintenance report created?    | MaintenanceReport         | IE: object created in step 3 contains the maintenance report created.                                         |
| Step 6  		     | 	... creating a maintenance report?						      | MaintenanceReport         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |              
| Step 7  		     | 	... validating all data (local validation)?   | MaintenanceReport         | IE: owns its data.                                                                                            | 
| 			  		        | 	... validating all data (global validation)?  | MaintenanceRepository     | Repository (Rule 2): global validation often involves querying data from multiple sources.                    | 
| 			  		        | 	... saving the created maintenance report?    | MaintenanceRepository     | Repository (Rule 2): responsible for storing and retrieving maintenance reports.                              | 
| Step 8  		     | 	... informing operation success?              | ListMaintenanceUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Task

Other software classes (i.e. Pure Fabrication) identified: 

* CreateTaskUI  
* CreateTaskController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us008-sequence-diagram-split.svg)

**Get Vehicle List Partial SD**

![Sequence Diagram - Partial - Get Vehicle List](svg/us008-sequence-diagram-partial-get-vehicle-list.svg)

**Create Maintenance Report*

![Sequence Diagram - Partial - Create Maintenance Report](svg/us008-sequence-diagram-partial-create-maintenance-report.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)