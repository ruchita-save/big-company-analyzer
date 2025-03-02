package com.bigcompany.reader;

import com.bigcompany.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeReader {
    public static Map<String, Employee> readEmployeesFromCsv(String filePath) {
        Map<String, Employee> employees = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 4) continue; // Skip invalid lines
                String id = fields[0].trim();
                String firstName = fields[1].trim();
                String lastName = fields[2].trim();
                double salary = Double.parseDouble(fields[3].trim());
                String managerId = (fields.length > 4 && !fields[4].trim().isEmpty()) ? fields[4].trim() : null;
                Employee employee = new Employee(id, firstName, lastName,salary, managerId);
                employees.put(id, employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build hierarchy
        for (Employee emp : employees.values()) {
            if (emp.getManagerId() != null && employees.containsKey(emp.getManagerId())) {
                employees.get(emp.getManagerId()).addSubordinate(emp);
            }
        }

        return employees;
    }
}
