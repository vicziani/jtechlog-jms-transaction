package employees;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import static employees.JmsConfig.EMPLOYEES_QUEUE_NAME;

@Service
@AllArgsConstructor
public class EmployeesQueueListener {

    private final EventsService eventsService;

    @JmsListener(destination = EMPLOYEES_QUEUE_NAME)
    public void receiveEvent(EmployeeHasCreatedEvent event) {
        eventsService.processEvent(event);
    }
}
