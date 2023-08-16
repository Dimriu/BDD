package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.*;


public class TransferFromCardToCardTest {
    DashboardPage dashboardPage;


    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var autoInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldReplenishmentFirstCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance =dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
//        var firstCardBalance = dashboardPage.getCardBalance(Integer.parseInt(String.valueOf(0)));
//        var secondCardBalance = dashboardPage.getCardBalance(Integer.parseInt(String.valueOf(1)));
//        var firstCardBalance = dashboardPage.getCardBalance("0001", firstCardInfo);
//        var secondCardBalance = dashboardPage.getCardBalance("0002", secondCardInfo);
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.extractBalance(String.valueOf(firstCardInfo));
        var actualBalanceSecondCard = dashboardPage.extractBalance(String.valueOf(secondCardInfo));
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

}