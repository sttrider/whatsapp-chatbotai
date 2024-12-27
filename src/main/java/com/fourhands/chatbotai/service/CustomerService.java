package com.fourhands.chatbotai.service;

import com.fourhands.chatbotai.entity.Customer;
import com.fourhands.chatbotai.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public Customer save(String name, String email, String phone) {

        return customerRepository.save(Customer.builder().name(name).email(email).phone(phone).creationDate(LocalDateTime.now()).build());
    }
}
