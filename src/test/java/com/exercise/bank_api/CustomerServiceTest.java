package com.exercise.bank_api;

import com.exercise.bank_api.dto.CustomerDetailsDto;
import com.exercise.bank_api.entity.Card;
import com.exercise.bank_api.entity.Customer;
import com.exercise.bank_api.repository.CustomerRepository;
import com.exercise.bank_api.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getCustomerDetails_forBusinessCustomer_shouldExcludeCreditCards() {
        Customer businessCustomer = new Customer();
        businessCustomer.setId(1L);
        businessCustomer.setFullName("Jane Doe");
        businessCustomer.setType("Business");

        Card creditCard = new Card();
        creditCard.setId(2L);
        creditCard.setCardNumber("7841 2345 8912 7452");
        creditCard.setType("Credit");

        Card debitCard = new Card();
        debitCard.setId(3L);
        debitCard.setCardNumber("1254 2538 8965 1245");
        debitCard.setType("Debit");

        businessCustomer.setCards(new HashSet<>(Arrays.asList(creditCard, debitCard)));
        businessCustomer.setAccounts(new HashSet<>());

        when(customerRepository.findByIdWithDetails(1L)).thenReturn(Optional.of(businessCustomer));

        CustomerDetailsDto result = customerService.getCustomerDetails(1L);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getFullName());
        assertEquals(1, result.getCards().size(), "Should only contain one card after filtering.");
        assertEquals("3", result.getCards().getFirst().getId(), "The remaining card should be the Debit card.");
        assertTrue(result.getCards().getFirst().getValue().contains("Debit"), "Card value should contain 'Debit'.");
    }
}
