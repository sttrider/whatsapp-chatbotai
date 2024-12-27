package com.fourhands.chatbotai.langchain4j;

import com.fourhands.chatbotai.entity.Agenda;
import com.fourhands.chatbotai.entity.Customer;
import com.fourhands.chatbotai.service.AgendaService;
import com.fourhands.chatbotai.service.CustomerService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleTools {

    private final CustomerService customerService;
    private final AgendaService agendaService;

    @Tool("Load the information from a Customer using cellphone")
    Customer loadCustomerInformation( @P("The user's cellphone") String cellphone) {
        log.info("load customer data: cellphone {}", cellphone);
        Optional<Customer> customer = customerService.findByPhone(cellphone);
        return customer.orElse(null);

    }

    @Tool("Save the customer information using his email, cellphone and name")
    Customer createCustomer(@P("The customer's email") String email, @P("The customer's cellphone") String cellphone, @P("The customer's name") String name) {
        log.info("save customer: name {} email {} cellphone {}", name, email, cellphone);
        return customerService.save(name, email, cellphone);
    }

    @Tool("Get the meeting for a specific user")
    List<Agenda> getMeetingInformation(@P("The user's data I want to get the schedule") Customer customer) {
        log.info("Meetings for a specific email {} {}", customer.getEmail(), customer.getPhone());
        return agendaService.findByCustomer(customer);
    }

    @Tool("Add a new appointment to a schedule for a specific user")
    Agenda addMeeting(@P("The user data I want to create a new appointment") Customer customer, @P("The date for the new appointment") String date, @P("The time for the new appointment") String time) {
        log.info("Adding schedule for user {} on date {} at time {}", customer, date, time);
        return agendaService.save(customer, date, time);
    }

    @Tool("Cancel a meeting for a specific user, date and time")
    String cancelMeeting(@P("The user data I want to delete the appointment") Customer customer, @P("The date for the cancellation") String date, @P("The time for the cancellation") String time) {
        log.info("Canceling schedule for email {} on date {} at time {}", customer, date, time);
        agendaService.delete(customer, date, time);
        return "Schedule canceled";
    }
}
