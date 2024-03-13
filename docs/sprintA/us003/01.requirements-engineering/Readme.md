# US003 - Register a colaborator with a job and fundamental characteristics

//cria um id para o collaborador que criou
## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager, I want to register a collaborator with a job and fundamental characteristics.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each job is characterized by having a designation, an informal and a technical description and a collaborator associated. 

>   Each  collaborator is charecterized by a name, a birthdate, admission date, an address, contact (email or phone number), an identification document and its identification number.

**From the client clarifications:**

> **Question:** The term "fundamental characteristics" is mentioned in US003. What, precisely, are those characteristics?
>
> **Answer:** .The essencial data are name, birthdate, admission date, address, contact (email or phone number), an identification document and its identification number.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** Every collaborator must have an associated job

### 1.4. Found out Dependencies

* There is a dependency on "US002 - Register a Job" as there must be a job associated to every collaborator.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * A name
    * A birthdate 
    * An address
    * A contact
    * An identification document
    * An identification number
	
* Selected data:
    * a Job

**Output Data:**

* Colaborator and its job associated 
* (In)Success of the operation
* Collaborator ID

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us003-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us003-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* None