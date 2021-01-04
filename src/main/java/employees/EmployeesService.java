package employees;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static employees.JmsConfig.EMPLOYEES_QUEUE_NAME;

@Service
@AllArgsConstructor
public class EmployeesService {

    private final JmsTemplate jmsTemplate;

    private final EmployeeRepository employeeRepository;

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public Employee createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee(command.getName());
        employeeRepository.save(employee);

        jmsTemplate.convertAndSend(EMPLOYEES_QUEUE_NAME, new EmployeeHasCreatedEvent(command.getName()));

        if (command.getName().equals("invalid")) {
            throw new IllegalArgumentException("Invalid name, rollback.");
        }

        return employee;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
