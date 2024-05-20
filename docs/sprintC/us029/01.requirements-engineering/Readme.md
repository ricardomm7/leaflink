# US029 - Record the completion of a task


## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I want to record the completion of a task.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>  To record the completion of a task, it is necessary to ensure that the task exists in both the Agenda and the To-Do List. 
> The task should be marked as completed in both lists once the completion is recorded.

**From the client clarifications:**

> **Question:** The collaborator can see what type of entry's? Like what status can he filter? Can he see canceled Entry's?
>
> **Answer:** The ones assigned to him. He can filter by the different values the status of the status, like planned, executed, canceled ...

> **Question:** When a collaborator records a task, it should be asked for any observations regarding the completed task?
>
> **Answer:** Maybe if optional, not mandatory.

> **Question:**
>
> **Answer:**

> **Question:**
>
> **Answer:**

> **Question:** 
> 
> **Answer:**

### 1.3. Acceptance Criteria

* **AC1** The task must exist in both the Agenda and the To-Do List before recording completion.


### 1.4. Found out Dependencies

* User Story US021 - Add a new entry to the To-Do List.

* User Story US022 - Add a new entry in the Agenda.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * None.
	
* Selected data:
    * Task

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us029-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* Implement appropriate permissions and access controls to restrict the ability to record the completion of a task to an authorized Collaborator user.