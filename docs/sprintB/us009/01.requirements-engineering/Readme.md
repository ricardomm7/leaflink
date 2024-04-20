# US009 - See the exact costs referring to water consumption of specific green spaces


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to know the exact costs referring to water consumption of specific green spaces so that I may manage these expenses efficiently. Therefore, within this user story, the aim is to carry out a statistical analysis concerning the water consumption costs in all parks. The "WaterUsed.csv" file provides the necessary data to carry out the study. This file records daily water consumption (in m³) since the day each park opened. The amount paid for water is $0.7 per m³, up to a consumption of 50 m³, with a fee of 15% added for higher consumption levels. The data file contains records of the following information: "Park Identification," "Year," "Month," "Day," "Consumption."

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Water consumption is measured in m³.
> The cost of water is in USD.
> The "WaterUsed.csv" file contains records of "Park Identification," "Year," "Month," "Day," and "Consumption."

**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>
> **Answer:** Duration is estimated in days.

### 1.3. Acceptance Criteria

* AC1: Successfully generate a bar plot showing monthly water consumption for a specified park and time period.
* AC2: Calculate the average monthly costs of water consumption for specified parks.
* AC3: Conduct statistical analysis for parks with the highest and lowest water consumption, including mean, median, standard deviation, and coefficient of skewness.
* AC4: Create relative and absolute frequency tables considering 5 classes.
* AC5: Identify outliers in the data and handle them appropriately.
* AC6: Visualize data through histograms with 10 classes.

### 1.4. Found out Dependencies

* No dependencies.

### 1.5 Input and Output Data

**Input Data:**

* Data from "WaterUsed.csv":

  * Park Identification
  * Year
  * Month
  * Day
  * Consumption (m³)

* User specifications:

  * Time period (StartMonth, EndMonth)
  * Park identification
  * Number of parks to be analyzed 

**Output Data:**

* Bar plot illustrating monthly water consumption
* Average monthly costs of water consumption
* Statistical indicators for parks with highest and lowest consumption
* Relative and absolute frequency tables
* Identified outliers
* Histograms with 10 classes
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us009-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks
* none
