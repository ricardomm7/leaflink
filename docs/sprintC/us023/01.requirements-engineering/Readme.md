# US023 - Assign a Team to an agendaEntry in the Agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign a Team to an agendaEntry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>
 
**From the client clarifications:**

> **Question:** Can a Team be assigned to multiple entries?
> 
> **Answer:** yes.

> **Question:** Can an Agenda entry have more than one team assigned to it?
>
> **Answer:** No.

### 1.3. Acceptance Criteria

* **AC1:** A message must be sent to all team members informing them about the assignment.
* **AC2:** Different email services can send the message. These services must be defined through a configuration file to allow the use of different platforms (e.g. Gmail, DEI’s email service, etc.).

### 1.4. Found out Dependencies

* US021 - Add a New Entry to the To-Do List: The toDoEntry must exist in the To-Do list before a team can be assigned to it.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * none
	
* Selected data:
    * Team
    * Entry

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us023-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* 