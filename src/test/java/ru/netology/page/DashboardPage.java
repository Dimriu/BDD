package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private static ElementsCollection cards = $$(".list__item div");
    private String button = "[data-test-id=action-deposit]";
    private static String balanceStart = "баланс:";
    private static String balanceFinish  = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static  int extractBalance(String text) {
        String cardValue = cards.get(Integer.parseInt(text)).text();
        val start = cardValue.indexOf(balanceStart);
        val finish = cardValue.lastIndexOf(balanceFinish);
        val value  = cardValue.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(value);
    }
    
}
