package com.ference.mortgagecalculator.domain;

public class CalculateMortgageResponseTO {

  private double monthlyPayment;

  public CalculateMortgageResponseTO(double monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  public double getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(double monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

}
