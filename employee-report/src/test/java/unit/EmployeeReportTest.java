package unit;

import fr.zbar.kata.employeereport.Employee;
import fr.zbar.kata.employeereport.EmployeeReport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeReportTest {

    private final EmployeeReport employeeReport = new EmployeeReport(
            new Employee("Max", 17),
            new Employee("Sepp", 18),
            new Employee("Nina", 15),
            new Employee("Mike", 51)
    );

    @Test
    void should_get_only_adult_employees() {
        assertThat(employeeReport.adults())
                .isEqualTo(List.of("SEPP", "MIKE"));
    }

    @Test
    void should_reserved_sort_adult_employees_by_name() {
        assertThat(employeeReport.adults())
                .isEqualTo(List.of("SEPP", "MIKE"));
    }

    @Test
    void should_capitalized_adult_employees() {
        assertThat(employeeReport.adults())
                .isEqualTo(List.of("SEPP", "MIKE"));
    }
}
