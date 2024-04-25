# US006 - Register a Vehicle 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...      | Answer                    | Justification (with patterns)                                                                 |
|:---------------|:-------------------------------------------------|:--------------------------|:----------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                 | RegisterVehicleUI         | The UI class handles user input and forwards it to the controller for further processing.     |
|                | ... display vehicle data input fields?           | RegisterVehicleUI         | The UI class displays the input fields for vehicle data, facilitating user interaction.       |
|                | ... confirms the user's input data?              | RegisterVehicleUI         | The UI class confirms the user's input data before proceeding with the registration process.  |
| Step 2			  		  | 	... coordinating the US?                        | RegisterVehicleController | Controller: responsible for coordinating and controlling the flow of interaction.             |
| 			  		        | ... knowing the user using the system?           | UserSession               | IE: cf. Authentication & Authorization component documentation.                               |
| Step 3  		     | 	... handles the registration of the vehicle?    | RegisterVehicleController | The controller class manages the registration process.                                        |
| 		             | 	... verify existed vehicle?						               | VehicleRepository         | IE: The Repository object performs global validation.                                         |
| Step 4  		     | 	... ensuring the registration process?          | VehicleRepository         | The repository class creates a new instance of the vehicle in the system.                     |
| 		             | 	... ensures data integrity during registration? | VehicleRepository         | The repository class ensures that only valid and consistent data is stored in the system.     |
| Step 5		       | ... stores vehicle registration data?            | VehicleRepository         | The repository class is responsible for persisting and managing vehicle registration data.    |
| Step 6  		     | 	... validating all data (local validation)?     | Vehicle                   | IE: The Vehicle object performs local validation on its attributes.                           | 
| 			  		        | 	... validating all data (global validation)?    | VehicleRepository         | Repository (Rule 2): global validation often involves querying data from multiple sources.    | 
| Step 7			  		  | 	... saving the vehicle register?                | Vehicle                   | IE: The Vehicle object encapsulates its data and handles persistence.                         | 
| Step 8  		     | 	... informing operation success?                | RegisterVehicleUI         | IE: The RegisterVehicleUI class handles user interaction and displays success/error messages. | 


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle


Other software classes (i.e. Pure Fabrication) identified: 

* RegisterVehicleUI  
* RegisterVehicleController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Vehicle Repository Partial SD**

![Sequence Diagram - Partial - Get Vehicle Repository](svg/us006-sequence-diagram-partial-get-vehicle-repository.svg)

**Verify Existed Vehicle Partial SD**

![Sequence Diagram - Partial - Verify Existed Vehicle](svg/us006-sequence-diagram-partial-verify-existed-vehicle.svg)

**Register a Vehicle**

![Sequence Diagram - Partial - Register a Vehicle](svg/us006-sequence-diagram-partial-register-vehicle.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)