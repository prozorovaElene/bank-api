package com.exercise.bank_api.service;

import com.exercise.bank_api.dto.AccountDto;
import com.exercise.bank_api.dto.CardDto;
import com.exercise.bank_api.dto.CustomerDetailsDto;
import com.exercise.bank_api.entity.Account;
import com.exercise.bank_api.entity.Card;
import com.exercise.bank_api.entity.Customer;
import com.exercise.bank_api.exception.CustomerNotFoundException;
import com.exercise.bank_api.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public CustomerDetailsDto getCustomerDetails(Long customerId) {
        Customer customer = customerRepository.findByIdWithDetails(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        return mapToCustomerDetailsDto(customer);
    }

    private CustomerDetailsDto mapToCustomerDetailsDto(Customer customer) {
        List<CardDto> filteredCards = customer.getCards().stream()
                .filter(card -> !("Business".equals(customer.getType()) && "Credit".equals(card.getType())))
                .map(this::formatCard)
                .collect(Collectors.toList());

        List<AccountDto> formattedAccounts = customer.getAccounts().stream()
                .map(this::formatAccount)
                .collect(Collectors.toList());

        return CustomerDetailsDto.builder()
                .id(customer.getId().toString())
                .fullName(customer.getFullName())
                .type(customer.getType())
                .cards(filteredCards)
                .accounts(formattedAccounts)
                .build();
    }

    private CardDto formatCard(Card card) {
        String lastFourDigits = card.getCardNumber().substring(card.getCardNumber().length() - 4);
        String value = String.format("xxxx xxxx xxxx %s - %s", lastFourDigits, card.getType());
        return new CardDto(card.getId().toString(), value);
    }

    private AccountDto formatAccount(Account account) {
        String value = String.format("%s - %s %s", account.getIban(), account.getBalance(), account.getCurrency());
        return new AccountDto(account.getId().toString(), value);
    }
}