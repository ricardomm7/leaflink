# US008 - List Vehicles Needing Check-up

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...          | Answer                    | Justification (with patterns)                                                                                                                                                          |
|:---------------|:-----------------------------------------------------|:--------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                      | ListMaintenanceUI         | Pure Fabrication: There is no need to assign this responsibility to any existing class in the Domain Model. The UI class is a utility class for handling user interaction.             |
|                | ... coordinating the US?                             | ListMaintenanceController | Controller: ListMaintenanceController is responsible for coordinating and controlling the flow of interaction between UI and domain classes, promoting low coupling and high cohesion. |
|                | ... handles the creation of the report?              | ListMaintenanceController | Controller: ListMaintenanceController manages the creation process, applying the Controller pattern by coordinating the interaction between UI and domain classes.                     |
|                | ... get VehicleRepository and MaintenanceRepository? | Repositories              | Pure Fabrication:                                                                                                                                                                      |
|                | ... knowing the vehicles needing maintenance?        | VehicleRepository         | Information Expert: VehicleRepository possesses the logic for determining vehicles needing maintenance, based on predefined criteria, promoting high cohesion.                         |
|                | ... Knowing last check-up of a vehicle?              | MaintenanceRepository     | Information Expert: MaintenanceRepository owns data of every vehicle that did a check-up.                                                                                              |
|                | ... creating a maintenance report?                   | MaintenanceRepository     | Creator: MaintenanceReport is directly created by MaintenanceRepository, which encapsulates the logic for managing maintenance reports.                                                |
|                | ... validating all data (local validation)?          | MaintenanceReport         | Information Expert: MaintenanceReport owns its data and is responsible for local validation of its attributes, ensuring data integrity and consistency.                                | 
|                | ... validating all data (global validation)?         | MaintenanceRepository     | Information Expert: MaintenanceRepository performs global validation, often involving querying data from multiple sources to ensure data consistency.                                  | 
| Step 2         | ... informing created maintenance report?            | ListMaintenanceUI         | Pure Fabrication: ListMaintenanceUI is responsible for informing users about the success of operations, promoting low coupling by encapsulating UI-related logic.                      | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle
* VehicleCheckup


Other software classes (i.e. Pure Fabrication) identified: 

* Repositories
* ListMaintenanceUI  
* MaintenanceRepository
* VehicleRepository
* ListMaintenanceController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us008-sequence-diagram-split.svg)

**Get Vehicle Needing Maintenance List Partial SD**

![Sequence Diagram - Partial - Get Vehicle Needing Maintenance List](svg/us008-sequence-diagram-partial-get-vehicle-needing-maintenance-list.svg)

**Get Maintenance Repository Partial SD**

![Sequence Diagram - Partial - Get Maintenance Repository](svg/us008-sequence-diagram-partial-get-maintenance-repository.svg)

**Create Maintenance Report**

![Sequence Diagram - Partial - Create Maintenance Report](svg/us008-sequence-diagram-partial-create-maintenance-report.svg)



## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)