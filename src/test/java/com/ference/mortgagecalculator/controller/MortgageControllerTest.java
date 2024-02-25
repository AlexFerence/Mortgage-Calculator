package com.ference.mortgagecalculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MortgageControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void calculateMortgageTestValidMonthly() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  30,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPayment").value(1610.46));
  }

  @Test
  void calculateMortgageTestValidBiWeekly() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  900000,
          "downPayment":  250000,
          "annualInterestRatePercentage":  2.4,
          "amortizationPeriod":  15,
          "paymentSchedule": "BIWEEKLY"
      }
      """;

    mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPayment").value(1985.39));
  }

  @Test
  void caluclateMortgageTestValidAcceleratedBiWeekly() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  950000,
          "downPayment":  340000,
          "annualInterestRatePercentage":  6.2,
          "amortizationPeriod":  20,
          "paymentSchedule": "ACCELERATED_BIWEEKLY"
      }
      """;

    mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPayment").value(2220.45));
  }

  // Test for amortization period not a multiple of 5
  @Test
  void calculateMortgageTestInvalidAmortizationPeriodMultipleOf5() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  29,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for amortization period greater than 30
  @Test
  void calculateMortgageTestInvalidAmortizationPeriod() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  31,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for downpayment less than 2% of the property price
  @Test
  void calculateMortgageTestInvalidDownPayment() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  5000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  30,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for negative property price
  @Test
  void calculateMortgageTestInvalidPropertyPrice() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  -400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  30,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for negative downpayment
  @Test
  void calculateMortgageTestInvalidDownPaymentNegative() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  -100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  30,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for negative annual interest rate
  @Test
  void calculateMortgageTestInvalidAnnualInterestRateNegative() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  -5,
          "amortizationPeriod":  30,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for less than 5 year amortization period
  @Test
  void calculateMortgageTestInvalidAmortizationPeriodLessThan5() throws Exception {
    String requestBody = """
      {
          "propertyPrice":  400000,
          "downPayment":  100000,
          "annualInterestRatePercentage":  5,
          "amortizationPeriod":  4,
          "paymentSchedule": "MONTHLY"
      }
      """;

      mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
        .contentType("application/json")
        .content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors.size()").value(1));
  }

  // Test for invalid payment schedule
  @Test
  void calculateMortgageTestInvalidPaymentSchedule() throws Exception {
    String requestBody = """
        {
            "propertyPrice":  400000,
            "downPayment":  100000,
            "annualInterestRatePercentage":  5,
            "amortizationPeriod":  30,
            "paymentSchedule": "WEEKLY"
        }
        """;

    mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  // Test for downpayment greater than property price
  @Test
  void calculateMortgageTestInvalidDownPaymentGreaterThanPropertyPrice() throws Exception {
    String requestBody = """
        {
            "propertyPrice":  400000,
            "downPayment":  500000,
            "annualInterestRatePercentage":  5,
            "amortizationPeriod":  30,
            "paymentSchedule": "MONTHLY"
        }
        """;

    mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  // Test when payment schedule is null
  @Test
  void calculateMortgageTestInvalidPaymentScheduleNull() throws Exception {
    String requestBody = """
        {
            "propertyPrice":  400000,
            "downPayment":  100000,
            "annualInterestRatePercentage":  5,
            "amortizationPeriod":  30
        }
        """;

    mvc.perform(MockMvcRequestBuilders.post("/api/mortgage/calculate")
            .contentType("application/json")
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
