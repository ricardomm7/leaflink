# US020 - Register a green space and its respective area

## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to register a green space (garden, medium-sized park or large-sized park) and its respective area.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> A green space needs to have a name, a type (garden, medium-sized park or large-sized park) and his respective area.

**From the client clarifications:**

> **Question:** The green space needs to have a name?
> 
> **Answer:** Yes. In that case, the name should be the «park» name, defined by the GSM registering it.

> **Question:** To register a green space, what is the criteria needed to classify it as a medium-sized park or a large-sized park?
> 
> **Answer:** It's a GSM responsability to decide the classification.

> **Question:** In which unit should the area be measured in?
>
> **Answer:** Usually, areas are measured in hectares.

> **Question:** Can the GSM register multiple green spaces at once?
> 
> **Answer:** That's a matter of UX/UI, each dev team can decide about it.

> **Question:** Can two green spaces have the same name?
> 
> **Answer:** No.

> **Question:** Dear client, which info about Green Spaces do you want the GSM see when listing? only the name ?
> 
> **Answer:** Each de team can decide about the aspects related to UX/UI.

> **Question:** Good afternoon, I would like to know between what ranges of hectares a green space is classified as garden, medium or large, or if it is possible to register 2 green spaces with the same area but in different typology, depending on the GSM it registers.
> 
> **Answer:** The classification is not automatic, it's up to GSM decide about it.

> **Question:** When creating a green space, is the gsm logged into the system considered the manager of that greenspace or does the gsm have to choose a manager?
> 
> **Answer:** In this proof of concept you can consider that the gsm creating the green space is the manager.

> **Question:** Can two different green spaces have the same address?
> 
> **Answer:** No.

### 1.3. Acceptance Criteria

* **AC1:** All attributes must be filled with a valid form.

### 1.4. Found out Dependencies

* No found out dependencies.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Name
    * Area
    * Address (street, zip-code, city)
	
* Selected data:
    * Type (garden, medium-sized park or large-sized park)

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us020-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* none