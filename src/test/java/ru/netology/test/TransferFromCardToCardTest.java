package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.*;


public class TransferFromCardToCardTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        DataHelper.AuthInfo loginData = DataHelper.getAuthInfo();
        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCode(loginData));
    }

    @Test
    void shouldReplenishmentFirstCard(){
        var firstCardBalanceWas = DashboardPage.extractBalance(String.valueOf(0));
        var secondCardBalanceWas = DashboardPage.extractBalance(String.valueOf(1));
        $("[data-test-id='action-deposit']").click();
        $("[data-test-id='amount']").click();
        var sumFirst = ThreadLocalRandom.current().nextInt(1, 10001);
        if(sumFirst < secondCardBalanceWas){
            sumFirst = sumFirst;
        } else {
            sumFirst = secondCardBalanceWas;
        }
        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(sumFirst));
        $("[data-test-id='from']").click();
        $("[data-test-id='from'] .input__control").setValue(DataHelper.getSecondCardNumber().getCardNumber());
        $(".button__content").click();
        var firstCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(0));
        var secondCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(1));
        var actualFirstCard =  firstCardBalanceWas + sumFirst ;
        var actualSecondCard = secondCardBalanceWas - sumFirst;
        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(0)), actualFirstCard);
        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(1)), actualSecondCard);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Тест №1: ");
        System.out.println("Перевод суммы: "  + sumFirst + " руб.");
        System.out.println("на карту №1 " + DataHelper.getFirstCardNumber().getCardNumber());
        System.out.println("с  карты №2 " + DataHelper.getSecondCardNumber().getCardNumber());
        System.out.println("Баланс карты  №1 было " + firstCardBalanceWas);
        System.out.println("Баланс карты  №2 было " + secondCardBalanceWas);
        System.out.println("Баланс карты  №1 стало " + firstCardBalanceBecame);
        System.out.println("Баланс карты  №2 стало " + secondCardBalanceBecame);
    }


    @Test
    void shouldReplenishmentSecondCard(){
        var firstCardBalanceWas = DashboardPage.extractBalance(String.valueOf(0));
        var secondCardBalanceWas = DashboardPage.extractBalance(String.valueOf(1));
        $$("[data-test-id='action-deposit']").last().click();
        $("[data-test-id='amount']").click();
        var sumSecond = ThreadLocalRandom.current().nextInt(1, 10001);;
        if(sumSecond < firstCardBalanceWas){
            sumSecond = sumSecond;
        } else {
            sumSecond = firstCardBalanceWas;
        }
        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(sumSecond));
        $("[data-test-id='from']").click();
        $("[data-test-id='from'] .input__control").setValue(DataHelper.getFirstCardNumber().getCardNumber());
        $(".button__content").click();
        var firstCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(0));
        var secondCardBalanceBecame = DashboardPage.extractBalance(String.valueOf(1));
        var actualFirstCard =  firstCardBalanceWas - sumSecond ;
        var actualSecondCard = secondCardBalanceWas + sumSecond;
        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(0)), actualFirstCard);
        Assertions.assertEquals(DashboardPage.extractBalance(String.valueOf(1)), actualSecondCard);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Тест №2: ");
        System.out.println("Перевод суммы: "  + sumSecond + " руб.");
        System.out.println("на карту №2 " + DataHelper.getSecondCardNumber().getCardNumber());
        System.out.println("с  карты №1 " + DataHelper.getFirstCardNumber().getCardNumber());
        System.out.println("Баланс карты  №1 было " + firstCardBalanceWas);
        System.out.println("Баланс карты  №2 было " + secondCardBalanceWas);
        System.out.println("Баланс карты  №1 стало " + firstCardBalanceBecame);
        System.out.println("Баланс карты  №2 стало " + secondCardBalanceBecame);
        System.out.println("-------------------------------------------------------------------------");
    }

}
