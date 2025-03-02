package com.bigcompany.analysis;

import com.bigcompany.model.Employee;

import java.util.Map;

public class EmployeeAnalyzer {


    public static Map<Employee, Double> findUnderpaidManagers(Map<String, Employee> employees) {
        Map<Employee, Double> underpaidManagers = new java.util.HashMap<>();
        for (Employee manager : employees.values()) {
            if (manager.getSubordinates().isEmpty()) {
                continue; // Skip employees with no subordinates
            }
            double totalSubordinateSalary = 0;
            int count = 0;
            for (Employee subordinate : manager.getSubordinates()) {
                totalSubordinateSalary += subordinate.getSalary();
                count++;
            }
            double averageSubordinateSalary = totalSubordinateSalary / count;
            double minRequiredSalary = averageSubordinateSalary * 1.2;
            if (manager.getSalary() < minRequiredSalary) {
                double deficit = minRequiredSalary - manager.getSalary();
                underpaidManagers.put(manager, deficit);
            }
        }
        return underpaidManagers;
    }

    public static Map<Employee, Double> findOverpaidManagers(Map<String, Employee> employees) {
        Map<Employee, Double> overpaidManagers = new java.util.HashMap<>();
        for (Employee manager : employees.values()) {
            if (manager.getSubordinates().isEmpty()) {
                continue; // Skip employees with no subordinates
            }
            double totalSubordinateSalary = 0;
            int count = 0;
            for (Employee subordinate : manager.getSubordinates()) {
                totalSubordinateSalary += subordinate.getSalary();
                count++;
            }
            double averageSubordinateSalary = totalSubordinateSalary / count;
            double maxAllowedSalary = averageSubordinateSalary * 1.5;
            if (manager.getSalary() > maxAllowedSalary) {
                double excess = manager.getSalary() - maxAllowedSalary;
                overpaidManagers.put(manager, excess);
            }
        }
        return overpaidManagers;
    }

    public static Map<Employee, Integer> findEmployeesWithLongReportingLines(Map<String, Employee> employees) {
        Map<Employee, Integer> longReportingLines = new java.util.HashMap<>();
        for (Employee employee : employees.values()) {
            int depth = getReportingDepth(employee, employees);
            if (depth > 4) {
                longReportingLines.put(employee, depth);
            }
        }
        return longReportingLines;
    }

    private static int getReportingDepth(Employee employee, Map<String, Employee> employees) {
        int depth = 0;
        while (employee.getManagerId() != null && employees.containsKey(employee.getManagerId())) {
            depth++;
            employee = employees.get(employee.getManagerId());
        }
        return depth;
    }
}
