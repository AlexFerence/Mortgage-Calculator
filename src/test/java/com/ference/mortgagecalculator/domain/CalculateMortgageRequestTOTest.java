package com.ference.mortgagecalculator.domain;


import org.junit.jupiter.api.Test;

public class CalculateMortgageRequestTOTest {

  @Test
  void calculateMortgageRequestTOTestSetMethods() {
    CalculateMortgageRequestTO calculateMortgageRequestTO = new CalculateMortgageRequestTO(100000, 20000, 5, 25, PaymentSchedule.MONTHLY);
    calculateMortgageRequestTO.setPropertyPrice(200000);
    calculateMortgageRequestTO.setDownPayment(30000);
    calculateMortgageRequestTO.setAnnualInterestRatePercentage(6);
    calculateMortgageRequestTO.setAmortizationPeriod(30);
    calculateMortgageRequestTO.setPaymentSchedule(PaymentSchedule.BIWEEKLY);

    assert(calculateMortgageRequestTO.getPropertyPrice() == 200000);
    assert(calculateMortgageRequestTO.getDownPayment() == 30000);
    assert(calculateMortgageRequestTO.getAnnualInterestRatePercentage() == 6);
    assert(calculateMortgageRequestTO.getAmortizationPeriod() == 30);
    assert(calculateMortgageRequestTO.getPaymentSchedule() == PaymentSchedule.BIWEEKLY);
  }

}
