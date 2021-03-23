package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {

    private static int ACCOUNT_NAME_LENGTH = 5;
    private static int ACCOUNT_PASSWORD_LENGTH = 8;

    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void register(Account account) {
        validateAccountName(account);
        validateAccountPassword(account);
        fillAccountDetails(account);
        accountManager.createNewAccount(account);
    }

    private void validateAccountName(Account account) {
        if (account.getName().length() <= ACCOUNT_NAME_LENGTH) {
            throw new WrongAccountNameException();
        }
    }

    private void validateAccountPassword(Account account) {
        String password = account.getPassword();
        if (password.length() <= ACCOUNT_PASSWORD_LENGTH) {
            throw new TooShortPasswordException();
        }
        if (passwordChecker.validate(password) != OK) {
            throw new WrongPasswordException();
        }
    }

    private void fillAccountDetails(Account account) {
        account.setCreatedDate(new Date());
        List<Address> addresses = new ArrayList<>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }
}