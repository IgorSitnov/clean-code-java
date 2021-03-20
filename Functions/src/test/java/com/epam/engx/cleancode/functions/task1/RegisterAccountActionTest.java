package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;
import org.junit.Before;
import org.junit.Test;

public class RegisterAccountActionTest {

    private AccountManagerMock accountManagerMock;

    private final RegisterAccountAction registerAccountAction = new RegisterAccountAction();
    private final Account validAccountStub = new ValidAccountStub();
    private final ValidAccountMock validAccountMock = new ValidAccountMock();

    @Before
    public void setUp() {
        accountManagerMock = new AccountManagerMock();
        registerAccountAction.setAccountManager(accountManagerMock);
        registerAccountAction.setPasswordChecker(new OkPasswordChecker());
    }

    @Test
    public void registerAccount() {
        registerAccountAction.register(validAccountStub);
        accountManagerMock.assertAccountRegistered(validAccountStub);
    }

    @Test
    public void populateAccountWhenCreate() {
        registerAccountAction.register(validAccountMock);
        validAccountMock.assertCreationDateExist();
        validAccountMock.assertHomeAddressInAddresses();
        validAccountMock.assertWorkAddressInAddresses();
        validAccountMock.assertAdditionalAddressInAddresses();
    }

    @Test (expected = WrongAccountNameException.class)
    public void throwExceptionWhenNameIsTooShort() {
        registerAccountAction.register(new ShortNameAccountStub());
    }

    @Test (expected = TooShortPasswordException.class)
    public void throwExceptionWhenPasswordIsTooShort() {
        registerAccountAction.register(new ShortPasswordAccountStub());
    }


    @Test (expected = WrongPasswordException.class)
    public void throwExceptionWhenPasswordIsNotOk() {
        registerAccountAction.setPasswordChecker(new NotOkPasswordChecker());
        registerAccountAction.register(validAccountStub);
    }

}
