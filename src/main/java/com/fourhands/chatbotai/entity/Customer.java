package com.fourhands.chatbotai.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Override
    public String toString() {
        return String.format("Id: [%s], Name: [%s], Email: [%s], Phone: [%s]", id, name, email, phone);
    }
}
