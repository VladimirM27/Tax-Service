# Tax Service
## Introduction

Tax Service is a web application that enables authorized users, both individuals and legal entities, to register and submit tax reports for tax inspection. Inspectors have authorized personnel who can view and accept or refuse the reports submitted by users and view statistics by the user and report type.
The application supports two languages: Ukrainian and English.

This project is based on the Front Controller pattern and uses a MySQL database for storing user and report data. The connection pool is managed using the dbcp2 library, and Apache Tomcat is used as the servlet container. Additionally, the application implements protection against re-sending data to the server when the page refreshes, known as the PRG pattern. An event log is also implemented using the log4j library, and the application is covered by unit tests with a minimum coverage percentage of 40%. JUnit 5 and Mockito 3 frameworks were chosen for testing.

## Getting Started

To use the Tax Service application, you will need to have the following installed:

 - Java SE Development Kit 16 or higher
 - Apache Maven 3.6.0 or higher
 - MySQL Server 8.0 or higher 
 - Apache Tomcat 10 or higher

## Installation

Once you have installed the above software, you can follow these steps to get started:

 - Clone the project repository to your local machine.
 - Set up the MySQL database using the tax_service.sql script provided in the src/main/resources directory.
 - Configure the database connection in the ```src/main/resources/db.properties ```  file with your MySQL credentials.
 - Build the project using the ```mvn clean install``` command in the project directory.
 - Deploy the ```tax-service.war``` file generated in the target directory to your Apache Tomcat server.



## Usage

To use the Tax Service application, open a web browser and navigate to ```http://localhost:8080/tax-service/jsp/index.jsp```. The main page of the service should open in front of you

### User
If you select the user role, you will be prompted to register or login. Once you have registered or logged in, you will be able to submit reports in XML or JSON format or fill out the form. You can also edit previously submitted reports, view submitted reports by date and filter by status (submitted, accepted, not accepted).

### Inspector
If you select the inspector role, you will be prompted to login. Once you have logged in, you will be able to view submitted reports by user and report type, and accept or refuse the reports with a reason for refusal.

### Logging

The application implements logging using the log4j library. The log files are stored in the logs directory in the project root.

### Testing

The application is covered by unit tests with a minimum coverage percentage of 40%. You can run the tests using the ```mvn test``` command in the project directory.

## Conclusion
The Tax Service application provides a simple and convenient way for authorized users to submit tax reports and for inspectors to review and accept or refuse those reports. The application is easy to install and use, and the code is covered by unit tests with a minimum coverage percentage of 40%. If you have any questions or feedback, please feel free to contact us.

## License

[MIT](https://choosealicense.com/licenses/mit/)


