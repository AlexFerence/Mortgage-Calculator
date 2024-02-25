package com.ference.mortgagecalculator.service;

import com.ference.mortgagecalculator.domain.CalculateMortgageRequestTO;

public interface ICalculatorService {

  double calculateMortgage(CalculateMortgageRequestTO calculateMortgageRequestTO);

}
