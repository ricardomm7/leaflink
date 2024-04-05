# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

Common Functionalities: The system should include core functionalities that are shared across multiple User Stories/User Cases, such as user authentication, data validation, and error handling mechanisms, to ensure consistency and reliability throughout the application.
Audit: The system should maintain a log of user actions for auditing purposes.
Reporting: The system should provide comprehensive reporting capabilities for data analysis and decision-making.
Security: The system should implement robust security measures to protect sensitive data and ensure user privacy.

## Usabilityhttps://1230399.atlassian.net/browse/LFC2-107

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

Error Prevention: The system should include features to prevent user errors and guide users towards correct actions.
Interface Aesthetics and Design: The user interface should be visually appealing and intuitive to use.
Help and Documentation: The system should provide clear and accessible help documentation to assist users in understanding its functionalities.
Consistency and Standards: The user interface should follow established design standards and maintain consistency across all modules.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

Frequency and Severity of Failure: The system should minimize the occurrence and severity of failures to ensure uninterrupted operation and user satisfaction.
Possibility of Recovery: The system should provide mechanisms for data recovery and system restoration in the event of failures or crashes.
Possibility of Prediction: The system should implement monitoring and diagnostic tools to anticipate and mitigate potential issues before they escalate.
Accuracy: The system should produce accurate results and perform calculations with precision to support decision-making processes.
Average Time Between Failures: The system should maintain a high level of uptime and reliability, with a low average time between failures to minimize disruptions in service.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

Response Time: The system should provide prompt responses to user interactions, with minimal latency, to ensure a smooth user experience.
Start-up Time: The system should initialize quickly upon launch, allowing users to access functionalities without delay.
Recovery Time: The system should recover swiftly from failures or crashes to minimize downtime and disruptions.
Memory Consumption: The system should efficiently manage memory resources to optimize performance and support scalability.
CPU Usage: The system should utilize CPU resources efficiently to avoid bottlenecks and ensure responsiveness.
Load Capacity: The system should be able to handle concurrent user sessions and large volumes of data without performance degradation.
Application Availability: The system should maintain high availability, with minimal downtime for maintenance or upgrades, to meet user demands.

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, instability, scalability and more._

Testability: The system should be designed with modular components that are easily testable, allowing for comprehensive testing of individual units and system integration.
Adaptability: The system architecture should be flexible and adaptable to accommodate future changes or enhancements in requirements or technology.
Maintainability: The system codebase should be well-organized and documented, facilitating maintenance tasks and updates by developers.
Compatibility: The system should be compatible with various operating systems, browsers, and devices to ensure broad accessibility and usability.
Configurability: The system should allow administrators to configure settings and parameters according to specific needs and preferences.
Instability: The system should be robust and resilient, with built-in mechanisms to handle errors, exceptions, and unexpected events gracefully.
Scalability: The system architecture should support scalability, enabling it to accommodate increasing user loads and data volumes without compromising performance or reliability.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

Programming Language: The system must be implemented using Java programming language to ensure compatibility and interoperability with existing components and libraries.
Development Tools: The system development must utilize IntelliJ IDE or NetBeans for Java development to maintain consistency and streamline collaboration among team members.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

mplementation Languages: The system backend must be implemented in Java, and the graphical user interface must be developed using JavaFX 11 to meet project requirements and compatibility constraints.
Database Integrity: The system must enforce data integrity constraints and utilize transaction management to maintain consistency and reliability in database operations.

### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

Hardware Requirements: The system should be designed to operate efficiently on standard computing hardware commonly available in office environments, with no specific constraints on material, shape, size, or weight.