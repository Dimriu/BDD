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
        var autoInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldReplenishmentFirstCard(){
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
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





//        var firstCardBalanceWas = DashboardPage.extractBalance(String.valueOf(0));
//        var secondCardBalanceWas = DashboardPage.extractBalance(String.valueOf(1));
//        $("[data-test-id='action-deposit']").click();
//        $("[data-test-id='amount']").click();
//        var sumFirst = ThreadLocalRandom.current().nextInt(1, 10001);
//        if(sumFirst < secondCardBalanceWas){
//            sumFirst = sumFirst;
//        } else {
//            sumFirst = secondCardBalanceWas;
//        }
//        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(sumFirst));
//        $("[data-test-id='from']").click();
//        $("[data-test-id='from'] .input__control").setValue(DataHelper.getSecondCardNumber().getCardNumber());
//        $(".button__content").click();
//        var firstCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(0));
//        var secondCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(1));
//        var actualFirstCard =  firstCardBalanceWas + sumFirst ;
//        var actualSecondCard = secondCardBalanceWas - sumFirst;
//        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(0)), actualFirstCard);
//        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(1)), actualSecondCard);
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println("Тест №1: ");
//        System.out.println("Перевод суммы: "  + sumFirst + " руб.");
//        System.out.println("на карту №1 " + getFirstCardNumber().getCardNumber());
//        System.out.println("с  карты №2 " + DataHelper.getSecondCardNumber().getCardNumber());
//        System.out.println("Баланс карты  №1 было " + firstCardBalanceWas);
//        System.out.println("Баланс карты  №2 было " + secondCardBalanceWas);
//        System.out.println("Баланс карты  №1 стало " + firstCardBalanceBecame);
//        System.out.println("Баланс карты  №2 стало " + secondCardBalanceBecame);
    }


//    @Test
//    void shouldReplenishmentSecondCard(){
//        var firstCardBalanceWas = DashboardPage.extractBalance(String.valueOf(0));
//        var secondCardBalanceWas = DashboardPage.extractBalance(String.valueOf(1));
//        $$("[data-test-id='action-deposit']").last().click();
//        $("[data-test-id='amount']").click();
//        var sumSecond = ThreadLocalRandom.current().nextInt(1, 10001);;
//        if(sumSecond < firstCardBalanceWas){
//            sumSecond = sumSecond;
//        } else {
//            sumSecond = firstCardBalanceWas;
//        }
//        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(sumSecond));
//        $("[data-test-id='from']").click();
//        $("[data-test-id='from'] .input__control").setValue(getFirstCardNumber().getCardNumber());
//        $(".button__content").click();
//        var firstCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(0));
//        var secondCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(1));
//        var actualFirstCard =  firstCardBalanceWas - sumSecond ;
//        var actualSecondCard = secondCardBalanceWas + sumSecond;
//        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(0)), actualFirstCard);
//        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(1)), actualSecondCard);
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println("Тест №2: ");
//        System.out.println("Перевод суммы: "  + sumSecond + " руб.");
//        System.out.println("на карту №2 " + DataHelper.getSecondCardNumber().getCardNumber());
//        System.out.println("с  карты №1 " + getFirstCardNumber().getCardNumber());
//        System.out.println("Баланс карты  №1 было " + firstCardBalanceWas);
//        System.out.println("Баланс карты  №2 было " + secondCardBalanceWas);
//        System.out.println("Баланс карты  №1 стало " + firstCardBalanceBecame);
//        System.out.println("Баланс карты  №2 стало " + secondCardBalanceBecame);
//        System.out.println("-------------------------------------------------------------------------");
//    }

}
