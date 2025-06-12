package com.cpf.model;

import java.time.LocalDate;

public class CpfConfig {
    // Basic information
    private int ageOfBrs;
    private LocalDate birthdate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double salary;
    private double salaryCap;
    private int cpfPayoutAge;
    private String payoutType;
    private boolean ownHdb;
    private boolean pledgeYourHdbAt55;

    // Account balances
    private double oaBalance;
    private double saBalance;
    private double maBalance;
    private double raBalance;
    private double excessBalance;

    // Interest rates
    private double interestRatesOaBelow55;
    private double interestRatesOaAbove55;
    private double interestRatesSa;
    private double interestRatesMa;
    private double interestRatesRa;
    private double extraInterestBelow55;
    private double extraInterestFirst30kAbove55;
    private double extraInterestNext30kAbove55;

    // Contribution rates below 55
    private double cpfContributionRatesBelow55Employee;
    private double cpfContributionRatesBelow55Employer;

    // Contribution rates 55-60
    private double cpfContributionRates55to60Employee;
    private double cpfContributionRates55to60Employer;

    // Contribution rates 60-65
    private double cpfContributionRates60to65Employee;
    private double cpfContributionRates60to65Employer;

    // Contribution rates 65-70
    private double cpfContributionRates65to70Employee;
    private double cpfContributionRates65to70Employer;

    // Contribution rates above 70
    private double cpfContributionRatesAbove70Employee;
    private double cpfContributionRatesAbove70Employer;

    // Allocation below 55
    private double allocationBelow55OaAllocation;
    private double allocationBelow55OaAmount;
    private double allocationBelow55SaAllocation;
    private double allocationBelow55SaAmount;
    private double allocationBelow55MaAllocation;
    private double allocationBelow55MaAmount;

    // Allocation above 55 (56-60)
    private double allocationAbove55Oa56to60Allocation;
    private double allocationAbove55Oa56to60Amount;
    private double allocationAbove55Ma56to60Allocation;
    private double allocationAbove55Ma56to60Amount;
    private double allocationAbove55Ra56to60Allocation;
    private double allocationAbove55Ra56to60Amount;

    // Allocation above 55 (61-65)
    private double allocationAbove55Oa61to65Allocation;
    private double allocationAbove55Oa61to65Amount;
    private double allocationAbove55Ma61to65Allocation;
    private double allocationAbove55Ma61to65Amount;
    private double allocationAbove55Ra61to65Allocation;
    private double allocationAbove55Ra61to65Amount;

    // Allocation above 55 (66-70)
    private double allocationAbove55Oa66to70Allocation;
    private double allocationAbove55Oa66to70Amount;
    private double allocationAbove55Ma66to70Allocation;
    private double allocationAbove55Ma66to70Amount;
    private double allocationAbove55Ra66to70Allocation;
    private double allocationAbove55Ra66to70Amount;

    // Allocation above 55 (Above 70)
    private double allocationAbove55OaAbove70Allocation;
    private double allocationAbove55OaAbove70Amount;
    private double allocationAbove55MaAbove70Allocation;
    private double allocationAbove55MaAbove70Amount;
    private double allocationAbove55RaAbove70Allocation;
    private double allocationAbove55RaAbove70Amount;
    private double allocationAbove55SaAllocation;
    private double allocationAbove55SaAmount;

    // Loan details
    private double loanAmount;
    private double loanBalance;
    private double loanPaymentsYear12;
    private double loanPaymentsYear3;
    private double loanPaymentsYear4beyond;

    // Retirement sums
    private double retirementSumsBrsAmount;
    private double retirementSumsBrsPayout;
    private double retirementSumsFrsAmount;
    private double retirementSumsFrsPayout;
    private double retirementSumsErsAmount;
    private double retirementSumsErsPayout;

    // Constructors, getters, and setters
    public CpfConfig() {
        // Default constructor
    }

    // Getters and setters for all fields (to be generated as needed)
    // ...

    // Utility method to convert yes/no strings to boolean
    public static boolean convertYesNoToBoolean(String value) {
        return "yes".equalsIgnoreCase(value);
    }
}
