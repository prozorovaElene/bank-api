package com.exercise.bank_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDetailsDto {
    private String id;
    private String fullName;
    private String type;
    private List<CardDto> cards;
    private List<AccountDto> accounts;
}