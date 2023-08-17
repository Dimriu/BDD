package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id='dashboard']");
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс:";
    private final String balanceFinish  = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

//    public int getCardBalance(DataHelper.CardInfo cardInfo) {
//        var text = cards.findBy(text(cardInfo.getCardNumber().substring(15))).getText();
//        return extractBalance(text);
//    }

    public int getCardBalance(int index){
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

//    public int getCardBalance(DataHelper.CardInfo cardInfo){
//        var text = cards.findBy(attribute("data-test-id", cardInfo.getTestId())).getText();
//        return extractBalance(text);
//    }


    public int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.lastIndexOf(balanceFinish);
        var value  = text.substring(start + balanceStart.length(), finish).replaceAll("[^0-9\\.]", "");
        return Integer.parseInt(value);
    }




    public  TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo){
        cards.findBy(text(cardInfo.getCardNumber().substring(15))).$("button").click();
        return new TransferPage();
    }



//    public  TransferPage selectCardToTransfer(int index){
//        cards.get(index).$("button").click();
//        return new TransferPage();
//    }

//        public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo){
//        cards.findBy((attribute("data-test-id", cardInfo.getTestId())).getText()).$("button").click();
//        return new TransferPage();
//    }


}
