package com.ference.mortgagecalculator.exceptions;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomErrorResponseTest {

  @Test
  void customErrorResponseTestSetMethods() {
    String now = LocalDateTime.now().toString();
    CustomErrorResponse customErrorResponse = new CustomErrorResponse(now, 400, "Resource not found");
    customErrorResponse.setErrors(List.of("amortizationPeriod: Amortization period must be a multiple of 5"));

    assertEquals(List.of("amortizationPeriod: Amortization period must be a multiple of 5"), customErrorResponse.getErrors());
    assertEquals(customErrorResponse.getStatus(), 400);
    assertEquals(customErrorResponse.getMessage(), "Resource not found");
    assertEquals(customErrorResponse.getTimestamp(), now);

  }
}
