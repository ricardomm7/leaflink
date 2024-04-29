# US011 - Collect data from the user portal

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for... | Answer                   | Justification (with patterns)                                                             |
|:---------------|:--------------------------------------------|:-------------------------|:------------------------------------------------------------------------------------------|
| 		   Step 2    | 	... interacting with the actor?            | CreateTaskUI             | Pure Fabrication: there is no reason to assign this responsibility to any existing class. |
| 			  		        | 	... coordinating the US?                   | AnalyseParkUseController | Controller                                                                                |
| 		             | 	... informing operation success?           | AnalyseParkUseUI         | Model-View-Controller                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* none

Other software classes (i.e. Pure Fabrication) identified: 

* AnalyseParkUseUI
* AnalyseParkUseController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us011-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us011-sequence-diagram-split.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us011-class-diagram.svg)