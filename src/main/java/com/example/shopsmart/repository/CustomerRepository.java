package com.example.shopsmart.repository;

import com.example.shopsmart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // email se customer dhundega..
    Optional<Customer> findByEmail(String email);

    // check — email is valid or not
    boolean existsByEmail(String email);
}