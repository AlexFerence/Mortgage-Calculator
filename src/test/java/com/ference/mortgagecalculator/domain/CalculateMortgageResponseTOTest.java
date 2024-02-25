package com.ference.mortgagecalculator.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateMortgageResponseTOTest {

  @Test
  void calculateMortgageResponseTOTestSetMethods() {
    CalculateMortgageResponseTO calculateMortgageResponseTO = new CalculateMortgageResponseTO(20);
    assertEquals(20, calculateMortgageResponseTO.getMonthlyPayment());
    calculateMortgageResponseTO.setMonthlyPayment(30);
    assertEquals(30, calculateMortgageResponseTO.getMonthlyPayment());
  }
}
