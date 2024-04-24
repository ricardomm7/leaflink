# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

- Common Functionalities: The system should include core functionalities that are shared across multiple User Stories/User Cases, such as user authentication, data validation, and error handling mechanisms, to ensure consistency and reliability throughout the application.
- Repositories: Repositories are commonly used by the system to store a wide variety of program information or user interactions.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- Error Prevention: The system should include features to prevent user errors and guide users towards correct actions.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

(fill in here )

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

(fill in here )

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._

(fill in here )

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- Programming Language: The system must be implemented using Java programming language to ensure compatibility and interoperability with existing components and libraries. 
- Development Tools: The system development must utilize IntelliJ IDE or NetBeans for Java development to maintain consistency and streamline collaboration among team members.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- Implementation Languages: The system backend must be implemented in Java, and the graphical user interface must be developed using JavaFX 11 to meet project requirements and compatibility constraints. 
- Database Integrity: The system must enforce data integrity constraints and utilize transaction management to maintain consistency and reliability in database operations.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

- Hardware Requirements: The system should be designed to operate efficiently on standard computing hardware commonly available in office environments, with no specific constraints on material, shape, size, or weight.