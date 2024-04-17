# US012 - I want to import a .csv file containing routes.


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Spaces Manager (GSM), I want to import a .csv file containing lines
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>   The .csv file containing lines with Water Point X, Water Point Y, and Distance should follow a specific format.


**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** The .csv file must contain columns for Water Point X, Water Point Y, and Distance.
* **AC2:** The data imported from the .csv file must be structured into a unique data structure in the system.
* **AC3:** The system must handle errors gracefully and provide informative messages to the user in case of issues during the import process.

### 1.4. Found out Dependencies

* None

### 1.5 Input and Output Data

**Input Data:**

* Imported data:
    * a .csv file

**Output Data:**

+ confirmation message

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us012-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks

* The imported data should be validated to ensure that it meets the required format and structure before being processed by the system
* Error handling mechanisms should be implemented to handle cases where the .csv file is missing or contains invalid data.