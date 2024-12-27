package com.fourhands.chatbotai.repository;

import com.fourhands.chatbotai.entity.Agenda;
import com.fourhands.chatbotai.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByCustomer(Customer customer);

    @Modifying
    @Transactional
    void deleteByCustomerAndEventDate(Customer customer, LocalDateTime eventDate);
}
