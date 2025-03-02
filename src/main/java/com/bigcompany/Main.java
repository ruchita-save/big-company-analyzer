package com.bigcompany;

import com.bigcompany.analysis.EmployeeAnalyzer;
import com.bigcompany.model.Employee;
import com.bigcompany.reader.EmployeeReader;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Employee> employees = EmployeeReader.readEmployeesFromCsv("src/main/resources/employees.csv");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Find underpaid managers");
            System.out.println("2. Find overpaid managers");
            System.out.println("3. Find employees with long reporting lines");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Map<Employee, Double> underpaidManagers = EmployeeAnalyzer.findUnderpaidManagers(employees);
                    underpaidManagers.forEach(
                            (manager, deficit) ->
                                    System.out.println(manager.getFirstName() + " " + manager.getLastName() + " is underpaid by " + deficit));
                    break;
                case 2:
                    Map<Employee, Double> overpaidManagers = EmployeeAnalyzer.findOverpaidManagers(employees);
                    overpaidManagers.forEach((manager, excess) ->
                            System.out.println(manager.getFirstName() + " " + manager.getLastName() + " is overpaid by " + excess));
                    break;
                case 3:
                    Map<Employee, Integer> employeesWithLongReportingLines= EmployeeAnalyzer.findEmployeesWithLongReportingLines(employees);
                    employeesWithLongReportingLines.forEach((employee, depth) ->
                            System.out.println(employee.getFirstName() + " " + employee.getLastName() + " has a reporting line that is too long by " + (depth - 4)));
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}