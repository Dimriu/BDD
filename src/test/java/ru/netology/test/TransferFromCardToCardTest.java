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
    void setup(){
    var loginPage = open("http://localhost:9999", LoginPage.class);
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    dashboardPage = verificationPage.validVerify(verificationCode);
}

@Test
    void temp(){
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.getCardBalance(0);
    var secondCardBalance = dashboardPage.getCardBalance(1);
    System.out.println(firstCardBalance);
    System.out.println(secondCardBalance);
    var amount = generateValidAmount(firstCardBalance);
    System.out.println(amount);
    var expectedBalanceFirstCard = firstCardBalance - amount;
    System.out.println(expectedBalanceFirstCard);
    var expectedBalanceSecondCard = secondCardBalance + amount;
    System.out.println(expectedBalanceSecondCard);
    var transferPage = dashboardPage.selectCardToTransfer(DataHelper.getFirstCardInfo());
    dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCardInfo);



    var actualBalanceFirstCard = dashboardPage.getCardBalance(0);
    var actualBalanceSecondCard = dashboardPage.getCardBalance(1);
    Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
}

}