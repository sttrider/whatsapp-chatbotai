package com.fourhands.chatbotai.service;

import com.fourhands.chatbotai.entity.Agenda;
import com.fourhands.chatbotai.entity.Customer;
import com.fourhands.chatbotai.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final UserService userService;

    public List<Agenda> findByCustomer(Customer customer) {
        return agendaRepository.findByCustomer(customer);
    }

    public Agenda save(Customer customer, String date, String time) {
        Agenda agenda = new Agenda();
        agenda.setCustomer(customer);
        agenda.setUser(userService.findByEmail("joao@4hands.com").orElseThrow());
        agenda.setEventDate(LocalDateTime.parse(date + "T" + time));
        agenda.setCreationDate(LocalDateTime.now());
        return agendaRepository.save(agenda);
    }

    public void delete(Customer customer, String date, String time) {
        agendaRepository.deleteByCustomerAndEventDate(customer, LocalDateTime.parse(date + "T" + time));
    }
}
