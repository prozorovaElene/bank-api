package com.exercise.bank_api.repository;

import com.exercise.bank_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.accounts LEFT JOIN FETCH c.cards WHERE c.id = :id")
    Optional<Customer> findByIdWithDetails(@Param("id") Long id);
}
