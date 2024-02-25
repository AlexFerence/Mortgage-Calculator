package com.ference.mortgagecalculator.service;


import com.ference.mortgagecalculator.domain.CalculateMortgageRequestTO;
import com.ference.mortgagecalculator.domain.PaymentSchedule;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements ICalculatorService {

  @Override
  public double calculateMortgage(CalculateMortgageRequestTO calculateMortgageRequestTO) {
    double principle = calculateMortgageRequestTO.getPropertyPrice() - calculateMortgageRequestTO.getDownPayment();

    double r;
    double n;

    if (calculateMortgageRequestTO.getPaymentSchedule() == PaymentSchedule.MONTHLY ||
      calculateMortgageRequestTO.getPaymentSchedule() == PaymentSchedule.ACCELERATED_BIWEEKLY
    ) {
      r = calculateMortgageRequestTO.getAnnualInterestRatePercentage() / 100 / 12;
      n = calculateMortgageRequestTO.getAmortizationPeriod() * 12.0;

      double monthlyPayment = monthlyPaymentFormula(principle, r, n);
      if (calculateMortgageRequestTO.getPaymentSchedule() == PaymentSchedule.MONTHLY) {
        return monthlyPayment;
      }
      else {
        return roundToTwoDecimalPlaces(monthlyPayment / 2.0);
      }
    }

    r = calculateMortgageRequestTO.getAnnualInterestRatePercentage() / 100 / 26;
    n = calculateMortgageRequestTO.getAmortizationPeriod() * 26;
    return monthlyPaymentFormula(principle, r, n);
  }

  private double monthlyPaymentFormula(double principle, double r, double n) {
    double m = principle * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    return roundToTwoDecimalPlaces(m);
  }

  private double roundToTwoDecimalPlaces(double value) {
    return Math.round(value * 100.0) / 100.0;
  }

}
