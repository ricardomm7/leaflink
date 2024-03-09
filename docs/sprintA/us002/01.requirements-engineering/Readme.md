# US002 - Register a Job 


## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager (HRM), I want to register a job.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> A Job needed is submitted by the human resources manager (HRM) and saved by the system as an option.

**From the client clarifications:**

> **Question:** Will a list of jobs be provided ?
>
> **Answer:** No. The human resources manager (HRM) defines it and the system will save it as an option for the future.

### 1.3. Acceptance Criteria

* **AC1:** All job fields must be filled in.
* **AC2:** To register a determinate job, sometimes the system have to require some skill.
* **AC3:** When creating a job with a required skill that doesn't exist, the system should prevent submission and ask the user to either create the skill (link to US001) or choose an existing skill.

### 1.4. Found out Dependencies

* There is a dependency on "US001 - Register a Skill" as there a job must have at least one associated skill.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a job title
    * a job description
   
	
* Selected data:
  * (Optional) Skills required (references existing skills in the system)

**Output Data:**
* List of existing skills required
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us002-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us002-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created job stays in a "not published" state in order to distinguish from "published" tasks.