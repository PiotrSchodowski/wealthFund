package com.example.wealthFund.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthFundException extends RuntimeException {
    String message;
}
