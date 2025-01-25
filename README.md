# README

## Prerequisites
Before starting, ensure you have the following components installed in your environment:

1. **Java Development Kit (JDK)**: Version 21 or higher.
2. **Maven**: Version 3.6.0 or higher.
3. **Git**: To clone the repository (optional).
4. **Recommended IDE**: IntelliJ IDEA or Eclipse.

---

## Running Tests
The project includes a set of automated tests using JUnit 5, Mockito, and Rest-Assured. To execute them:

### From the Command Line
1. Ensure you are in the root directory of the project.
2. Run the following command:

   ```bash
   mvn clean test
   ```

---

## Running the Spring Boot Application
You can start the Spring Boot application using Maven.

### From the Command Line
1. Ensure you are in the root directory of the project.
2. Run the following command:

   ```bash
   mvn spring-boot:run
   ```

   This will start the embedded server (usually at `http://localhost:8080`).

