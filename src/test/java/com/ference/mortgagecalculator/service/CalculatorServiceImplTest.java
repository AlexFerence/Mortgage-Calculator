package com.ference.mortgagecalculator.service;

import com.ference.mortgagecalculator.domain.CalculateMortgageRequestTO;
import com.ference.mortgagecalculator.domain.PaymentSchedule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorServiceImplTest {

  @Test
  void calculateMortgageMonthlyTest1() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
      400000,
      100000,
      5,
      30,
      PaymentSchedule.MONTHLY
    );
    double monthlyPayments = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    assertEquals(1610.46, monthlyPayments);
  }

  @Test
  void calculateMortgageMonthlyTest2() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
      364000,
      92000,
      2.1,
      25,
      PaymentSchedule.MONTHLY
    );
    double res = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    assertEquals(1166.17, res);
  }

  @Test
  void calculateMortgageBiweeklyTest1() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
        400000,
        100000,
        5,
        30,
        PaymentSchedule.BIWEEKLY
    );
    double res = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    assertEquals(742.93, res);
  }

  @Test
  void calculateMortgageBiweeklyTest2() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
      350000,
      200000,
      6.3,
      15,
      PaymentSchedule.ACCELERATED_BIWEEKLY
    );
    double res = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    System.out.println(res);
    assertEquals(645.12, res);
  }

  @Test
  void calculateMortgageAcceleratedBiweeklyTest1() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
      400000,
      100000,
      5,
      30,
      PaymentSchedule.ACCELERATED_BIWEEKLY
    );
    double res = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    assertEquals(805.23, res);
  }

  @Test
  void calculateMortgageAcceleratedBiweeklyTest2() {
    ICalculatorService calculatorService = new CalculatorServiceImpl();
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(
      364000,
      92000,
      2.1,
      30,
      PaymentSchedule.ACCELERATED_BIWEEKLY
    );
    double res = calculatorService.calculateMortgage(calculateMortgageRequestTO);
    assertEquals(509.51, res);
  }





}
