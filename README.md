# Big Company Employee Analysis

## Overview
This project analyzes the organizational structure of a company to ensure:
- Managers earn within an acceptable salary range compared to their subordinates.
- No employee has more than four managers between them and the CEO.

The program reads employee data from a CSV file and provides insights based on the above criteria.

## Features
- Identifies underpaid managers (earning less than 20% above their subordinates' average salary).
- Identifies overpaid managers (earning more than 50% above their subordinates' average salary).
- Detects employees with excessively long reporting lines.

## Technologies Used
- Java SE
- JUnit for testing
- Maven for dependency management and project structure

## Project Structure
```
com.bigcompany
├── analysis        # Contains EmployeeAnalyzer for salary and hierarchy checks
├── model           # Defines Employee class
├── reader          # Reads employee data from CSV file
├── Main.java       # Entry point for the application
└── test            # JUnit test cases
```

## How to Run
1. Clone the repository.
2. Ensure Java and Maven are installed.
3. Run the following command to build the project:
   ```sh
   mvn clean install
   ```
4. Execute the application with the following options:
   ```sh
   java -jar target/BigCompanyAnalysis.jar
   ```
   The program will prompt you to select a query to execute.

## Command-Line Options
Instead of passing arguments directly, the application will prompt the user to choose:
- **1**: Find underpaid managers
- **2**: Find overpaid managers
- **3**: Find employees with long reporting lines

## Sample CSV Format
```
Id,FirstName,LastName,Salary,ManagerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
```

## Running Tests
To execute unit tests:
```sh
mvn test
```

JUnit tests validate:
- Underpaid manager detection
- Overpaid manager detection
- Long reporting line detection

## Assumptions
- The CEO has no manager (ManagerId is empty).
- Every employee has a unique ID.
- Employee salary is stored as a decimal number.
- The input file is well-formatted with valid entries.

## License
This project is open-source and available for modification and distribution.
