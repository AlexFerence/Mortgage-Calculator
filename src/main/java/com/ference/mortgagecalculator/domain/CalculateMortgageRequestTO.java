package com.ference.mortgagecalculator.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CalculateMortgageRequestTO {

  @DecimalMin(value = "0.0", message = "Property price must be greater than or equal to  0")
  private double propertyPrice;

  @DecimalMin(value = "0.0", message = "Down payment must be greater than or equal to  0")
  private double downPayment;

  @DecimalMin(value = "0.0", message = "Annual interest rate must be greater than or equal to  0")
  private double annualInterestRatePercentage;

  @Min(value = 5, message = "Amortization period must be at least 5 years")
  @Max(value = 30, message = "Amortization period must be at most 30 years")
  private int amortizationPeriod;

  @NotNull(message = "Payment schedule must be provided")
  private PaymentSchedule paymentSchedule;

  public CalculateMortgageRequestTO(double propertyPrice, double downPayment, double annualInterestRatePercentage, int amortizationPeriod, PaymentSchedule paymentSchedule) {
    this.propertyPrice = propertyPrice;
    this.downPayment = downPayment;
    this.annualInterestRatePercentage = annualInterestRatePercentage;
    this.amortizationPeriod = amortizationPeriod;
    this.paymentSchedule = paymentSchedule;
  }

  public void setPropertyPrice(double propertyPrice) {
    this.propertyPrice = propertyPrice;
  }

  public void setDownPayment(double downPayment) {
    this.downPayment = downPayment;
  }

  public void setAnnualInterestRatePercentage(double annualInterestRatePercentage) {
    this.annualInterestRatePercentage = annualInterestRatePercentage;
  }

  public void setAmortizationPeriod(int amortizationPeriod) {
    this.amortizationPeriod = amortizationPeriod;
  }

  public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
    this.paymentSchedule = paymentSchedule;
  }

  public double getPropertyPrice() {
    return propertyPrice;
  }

  public double getDownPayment() {
    return downPayment;
  }

  public double getAnnualInterestRatePercentage() {
    return annualInterestRatePercentage;
  }

  public double getAmortizationPeriod() {
    return (double) amortizationPeriod;
  }

  public PaymentSchedule getPaymentSchedule() {
    return paymentSchedule;
  }

}
