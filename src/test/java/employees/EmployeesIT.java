package employees;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @Autowired
    EmployeesController employeesController;

    @MockBean
    EventsService eventsService;

    @Test
    void testSendAndReceive() {
        employeesController.createEmployee(new CreateEmployeeCommand("John Doe"));

        var employees = employeesController.findAllEmployees();

        assertThat(employees).extracting(Employee::getName).containsExactly("John Doe");
        verify(eventsService, timeout(4000).times(1)).processEvent(argThat(e -> e.getName().equals("John Doe")));
    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> employeesController.createEmployee(new CreateEmployeeCommand("invalid")));

        var employees = employeesController.findAllEmployees();

        assertThat(employees).isEmpty();
        verify(eventsService, Mockito.after(4000).never()).processEvent(any());
    }
}
