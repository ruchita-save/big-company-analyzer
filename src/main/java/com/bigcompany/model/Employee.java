package com.bigcompany.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String managerId;
    private final Double salary;
    private final List<Employee> subordinates = new ArrayList<>();

    public Employee(String id, String firstName, String lastName,Double salary, String managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerId = managerId;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getManagerId() {
        return managerId;
    }

    public Double getSalary() {
        return salary;
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void addSubordinate(Employee subordinate) {
        subordinates.add(subordinate);
    }
}

