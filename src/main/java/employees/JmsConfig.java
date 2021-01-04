package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.Map;

@Configuration
public class JmsConfig {

    public final static String EMPLOYEES_QUEUE_NAME = "employees.queue";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter
                = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");
        converter.setTypeIdMappings(
                Map.of("EmployeeHasCreatedEvent", EmployeeHasCreatedEvent.class));
        return converter;
    }

}
