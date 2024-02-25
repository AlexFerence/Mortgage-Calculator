# Mortgage Calculator App

This API allows you to calculate the monthly payment for a mortgage based on various different parametes.

## Endpoint

`POST /api/mortgage/calculate`

### Request
The request body should be a JSON object with the following properties:

- `propertyPrice` (double): The price of the property. Must be greater than or equal to  0.
- `downPayment` (double): The down payment on the property. Must be greater than or equal to  0, at least  2% of the property price, and less than the property price.
- `annualInterestRatePercentage` (double): The annual interest rate as a percentage. Must be greater than or equal to  0.
- `amortizationPeriod` (int): The amortization period in years. Must be at least  5 years and at most  30 years, and a multiple of  5.
- `paymentSchedule` (PaymentSchedule): Must be either `MONTHLY`, `BIWEEKLY` or `ACCELERATED_BIWEEKLY`

#### Example Request
```json
{
      "propertyPrice": 200000.00,
      "downPayment": 40000.00,
      "annualInterestRatePercentage": 3.5,
      "amortizationPeriod": 30,
      "paymentSchedule": "MONTHLY"
}
```

### Response
The response will be a JSON object with the following properties:

- `monthlyPayment` (double): The calculated monthly payment.

### Example Response
```json
{
      "monthlyPayment": 718.47
}
```

## To Run Locally
1. Ensure you have maven and Java 17 or greater running on your machine. Verify by running `java -version`
2. Clone the repository using `git clone ` and navigate to the project directory
3. Run `./mvnw clean install` to build the project
4. Then run `./mvnw spring-boot:run` to run the server.
5. The server will be running on `http://localhost:8080`
