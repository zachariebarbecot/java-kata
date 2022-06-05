package fr.zbar.kata.employeereport;

import java.util.Comparator;
import java.util.List;

public final class EmployeeReport {

    private final List<Employee> employees;

    public EmployeeReport(Employee... employees) {
        this.employees = List.of(employees);
    }

    public List<String> adults() {
        return employees.stream()
                .filter(Employee::isAdult)
                .map(Employee::name)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
