package com.epam.engx.cleancode.dry;

import com.epam.engx.cleancode.dry.thirdpartyjar.DateCalculator;
import com.epam.engx.cleancode.dry.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Date;

public class InterestCalculator implements Profitable {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return getInterest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return DateCalculator.durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private BigDecimal getInterest(AccountDetails accountDetails) {
        double interest;
        double principalAmount = accountDetails.getBalance().doubleValue();
        if (AGE <= accountDetails.getAge()) {
            interest = principalAmount * durationSinceStartDateInYears(accountDetails.getStartDate()) * SENIOR_PERCENT / 100;
        } else {
            interest = principalAmount * durationSinceStartDateInYears(accountDetails.getStartDate()) * INTEREST_PERCENT / 100;
        }
        return BigDecimal.valueOf(interest);
    }

    int durationSinceStartDateInYears(Date startDate) {
        return DateCalculator.durationBetweenDatesInYears(startDate, new Date());
    }


}