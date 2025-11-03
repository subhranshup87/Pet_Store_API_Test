# Pet Store API Test Automation

## Overview
This project implements automated API tests for the Pet Store API using REST Assured, Cucumber, and JUnit 5. The tests cover CRUD (Create, Read, Update, Delete) operations for the pet endpoints, with Allure reporting integration for detailed test results.

## Technologies Used
- Java 17
- REST Assured 5.3.0
- Cucumber 7.14.0
- JUnit 5.9.3
- Allure Reports 2.24.0
- Jackson Databind 2.15.0
- Maven

## Project Structure
```
src/
├── test/
    ├── java/
    │   ├── features/      # Cucumber feature files
    │   ├── model/         # POJO classes for request/response
    │   ├── runner/        # Test runner configuration
    │   ├── steps/         # Step definitions
    │   └── utils/         # Utility classes
    └── resources/
        └── allure.properties
```

## Features
- BDD-style test scenarios using Cucumber
- CRUD operations for Pet endpoints
- Data-driven testing using Cucumber Scenario Outline
- Allure reporting integration
- POJO-based request/response handling
- Modular and maintainable test structure

## Prerequisites
- Java JDK 17 or higher
- Maven 3.6 or higher
- Allure command-line tools (optional, for viewing reports)

## Running the Tests
1. Clone the repository:
   ```bash
   git clone https://github.com/subhranshup87/Rest_Assured_Pet_Store_API_Test.git
   cd Rest_Assured_Pet_Store_API_Test
   ```

2. Run tests using Maven:
   ```bash
   mvn clean test
   ```

3. Generate and view Allure report:
   ```bash
   mvn allure:serve
   ```

## Test Scenarios
The project includes the following test scenarios:
- Create a new pet with different statuses
- Retrieve pet details
- Update existing pet information
- Delete a pet

## Reporting
The project uses Allure for test reporting, which provides:
- Detailed test execution results
- Step-by-step test execution
- Test execution timeline
- Attachments and screenshots (if any)
- Test suite analytics

## CI/CD Integration
This project is configured with GitHub Actions for Continuous Integration and Continuous Deployment:

- Automated test execution on push and pull requests to main branch
- Java 17 environment setup with Maven caching
- Automatic Allure report generation
- Report deployment to GitHub Pages
- View the latest test results at: https://subhranshup87.github.io/Rest_Assured_Pet_Store_API_Test/

### CI/CD Workflow
1. On push/PR to main branch, GitHub Actions:
   - Sets up JDK 17
   - Builds the project
   - Runs all tests
   - Generates Allure report
   - Deploys report to GitHub Pages