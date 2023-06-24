package com.example.wealthFund.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WealthFundSingleException extends RuntimeException {
    String message;
}
