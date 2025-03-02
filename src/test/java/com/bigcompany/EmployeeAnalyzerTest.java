package com.bigcompany;

import com.bigcompany.analysis.EmployeeAnalyzer;
import com.bigcompany.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeAnalyzerTest {
    private Map<String, Employee> employees;
    private Map<String, Employee> overpaidEmployees;
    private Map<String, Employee> longReportingEmployees;

    @BeforeEach
    void setUp() {
        employees = new HashMap<>();
        overpaidEmployees = new HashMap<>();
        longReportingEmployees = new HashMap<>();

        // Scenario 1: Underpaid Managers
        Employee ceo = new Employee("1", "John", "Doe", 100000.0, null);
        Employee manager1 = new Employee("2", "Alice", "Smith", 55000.0, "1");
        Employee manager2 = new Employee("3", "Bob", "Johnson", 45000.0, "1");
        Employee emp1 = new Employee("4", "Charlie", "Brown", 50000.0, "2");
        Employee emp2 = new Employee("5", "David", "Williams", 49000.0, "2");
        Employee emp3 = new Employee("6", "Eve", "Miller", 40000.0, "3");
        Employee emp4 = new Employee("7", "Frank", "Wilson", 39000.0, "3");

        ceo.addSubordinate(manager1);
        ceo.addSubordinate(manager2);
        manager1.addSubordinate(emp1);
        manager1.addSubordinate(emp2);
        manager2.addSubordinate(emp3);
        manager2.addSubordinate(emp4);

        employees.put("1", ceo);
        employees.put("2", manager1);
        employees.put("3", manager2);
        employees.put("4", emp1);
        employees.put("5", emp2);
        employees.put("6", emp3);
        employees.put("7", emp4);

        // Scenario 2: Overpaid Managers
        Employee overpaidCeo = new Employee("10", "Jack", "Boss", 150000.0, null);
        Employee overpaidManager = new Employee("11", "Oliver", "King", 130000.0, "10");
        Employee underpaidEmp = new Employee("12", "Emma", "Queen", 40000.0, "11");

        overpaidCeo.addSubordinate(overpaidManager);
        overpaidManager.addSubordinate(underpaidEmp);

        overpaidEmployees.put("10", overpaidCeo);
        overpaidEmployees.put("11", overpaidManager);
        overpaidEmployees.put("12", underpaidEmp);

        // Scenario 3: Long Reporting Lines
        Employee longCeo = new Employee("20", "Rick", "Chief", 90000.0, null);
        Employee level1 = new Employee("21", "Morty", "Smith", 80000.0, "20");
        Employee level2 = new Employee("22", "Summer", "Smith", 70000.0, "21");
        Employee level3 = new Employee("23", "Beth", "Smith", 60000.0, "22");
        Employee level4 = new Employee("24", "Jerry", "Smith", 50000.0, "23");
        Employee level5 = new Employee("25", "Mr.", "Meeseeks", 40000.0, "24");

        longCeo.addSubordinate(level1);
        level1.addSubordinate(level2);
        level2.addSubordinate(level3);
        level3.addSubordinate(level4);
        level4.addSubordinate(level5);

        longReportingEmployees.put("20", longCeo);
        longReportingEmployees.put("21", level1);
        longReportingEmployees.put("22", level2);
        longReportingEmployees.put("23", level3);
        longReportingEmployees.put("24", level4);
        longReportingEmployees.put("25", level5);
    }

    @Test
    void testFindUnderpaidManagers() {
        Map<Employee, Double> underpaidManagers = EmployeeAnalyzer.findUnderpaidManagers(employees);
        assertEquals(2, underpaidManagers.size());
        assertTrue(underpaidManagers.containsKey(employees.get("2")));
        assertTrue(underpaidManagers.containsKey(employees.get("3")));
    }

    @Test
    void testFindOverpaidManagers() {
        Map<Employee, Double> overpaidManagers = EmployeeAnalyzer.findOverpaidManagers(overpaidEmployees);
        assertEquals(1, overpaidManagers.size());
        assertTrue(overpaidManagers.containsKey(overpaidEmployees.get("11")));
    }

    @Test
    void testFindEmployeesWithLongReportingLines() {
        Map<Employee, Integer> longReportingLines = EmployeeAnalyzer.findEmployeesWithLongReportingLines(longReportingEmployees);
        assertEquals(1, longReportingLines.size());
        assertTrue(longReportingLines.containsKey(longReportingEmployees.get("25")));
    }
}
