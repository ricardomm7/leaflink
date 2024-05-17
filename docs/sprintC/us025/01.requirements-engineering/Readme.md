# US025 - Cancel an entry in the Agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to cancel an entry in the Agenda to manage and update task statuses effectively.
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The person who cancels the entries in the Agenda is the GSM, and the entry status is updated in the system.
> A canceled task should not be deleted but rather change its state.

**From the client clarifications:**

> **Question:** To register a vehicle is necessary all attributes ?
> 
> **Answer:** Yes. To register a vehicle in the system, is request every attribute, in other case, the system should reject.

> **Question:** Should the application identify a registered vehicle by a serial number or other attribute?
>
> **Answer:** By plate id

> **Question:** Should the application a group the vehicles by their brand, serial number or other attribute?
>
> **Answer:** No requirements were set concerning groups of vehicles

> **Question:** If the Fm inserts the same vehicle by mistake, should it inform the user of the mistake and give him the option to add another vehicle?
>
> **Answer:** Duplication of data is not a business rule is technical one, since by definition in a set you cant have duplicates

> **Question:** When a vehicle is registered, are there specific requirements for accepting the brand? For example, does the system need to check if the brand is on a predetermined list? Does this also apply to the model or any other characteristics?
>
> **Answer:** No. One can consider a list os brands and a list of models previously inserted in the system. No need to go through validations.

> **Question:** Can a fm register no vehicles or does he have to register at least one?
> 
> **Answer:** The VFM is a role or system user profile that has the rights to perform some system actions (like the ones described by the US06, US07 and US08). In theory If there is no need to register a vehicle, no vehicles will be registered but that would be rather odd.



### 1.3. Acceptance Criteria

* **AC1:** A canceled task should not be deleted but rather change its state to "canceled."
* **AC2:** The system should notify all team members assigned to the task about the cancellation.
* **AC3:** The reason for cancellation must be recorded.

### 1.4. Found out Dependencies

* US022 - Add a new entry in the Agenda: An entry must exist in the agenda first in order to be cancelled.
* US023 - Assign a Team to an Entry in the Agenda: Team members need to be notified upon task cancellation.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Unique Entry ID
    * Reason for cancellation
	
* Selected data:
    * None

**Output Data:**

* (In)Success of the operation
* Notifications sent to team members.

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* Implement appropriate permissions and access controls to restrict the ability to cancel agenda entries to authorized GSM users only.
* Provide clear feedback and error messages to GSM in case of any issues during the cancellation process.