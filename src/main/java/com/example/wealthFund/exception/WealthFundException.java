package com.example.wealthFund.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WealthFundException extends RuntimeException {
    String message;
}
