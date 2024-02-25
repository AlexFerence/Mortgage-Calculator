package com.ference.mortgagecalculator.controller;

import com.ference.mortgagecalculator.domain.CalculateMortgageRequestTO;
import com.ference.mortgagecalculator.domain.CalculateMortgageResponseTO;
import com.ference.mortgagecalculator.service.ICalculatorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mortgage")
public class MortgageController {

  ICalculatorService calculatorService;

  public MortgageController(ICalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  @PostMapping("/calculate")
  public CalculateMortgageResponseTO calculate(@RequestBody @Valid CalculateMortgageRequestTO calculateMortgageRequestTO) {
    if (calculateMortgageRequestTO.getAmortizationPeriod() % 5 != 0) {
      throw new IllegalArgumentException("amortizationPeriod: Amortization period must be a multiple of 5");
    }
    // If the downpayment is less than 2% of the property price throw an error
    if (calculateMortgageRequestTO.getDownPayment() < calculateMortgageRequestTO.getPropertyPrice() * 0.02) {
      throw new IllegalArgumentException("downPayment: Downpayment must be at least 2% of the property price");
    }
    // If the downpayment is greater than the property price throw an error
    if (calculateMortgageRequestTO.getDownPayment() > calculateMortgageRequestTO.getPropertyPrice()) {
      throw new IllegalArgumentException("downPayment: Downpayment must be less than the property price");
    }

    double monthlyPayment = calculatorService.calculateMortgage(calculateMortgageRequestTO);

    return new CalculateMortgageResponseTO(monthlyPayment);
  }

}
