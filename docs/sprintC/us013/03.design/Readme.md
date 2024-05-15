# US013 - Apply an algorithm that returns the routes to be opened, with minimum cost

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...         | Answer                    | Justification (with patterns)                                                             |
|:---------------|:----------------------------------------------------|:--------------------------|:------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                    | WaterIrrigationUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class. |
| Step 2         | ... receiving the filepath?                         | WaterIrrigationUI         | Pure Fabrication.                                                                         |
| 			  		 Step 3 | 	... coordinating the US?                           | WaterIrrigationController | Controller.                                                                               |
|                | ... read the CSV and turn all the data into Routes? | ExternalCSV               | Pure Fabrication.                                                                         |
|                | ... calculate the MST?                              | KruskalAlgorithm          | Pure Fabrication.                                                                         |
|                | ... executing the visual graph?                     | Graphviz                  | Pure Fabrication.                                                                         |
|                | ... writing a final .csv?                           | ExternalCSV               | Pure Fabrication.                                                                         |
| Step 4         | ... displaying operation success?                   | WaterIrrigationUI         | Pure Fabrication.                                                                         |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* ExternalCSV
* KruskalAlgorithm
* Graphviz

Other software classes (i.e. Pure Fabrication) identified: 

* WaterIrrigationUI
* WaterIrrigationController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us013-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us013-sequence-diagram-split.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us013-class-diagram.svg)