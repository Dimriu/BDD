package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement transferSum = $("[data-test-id='amount'] .input__control");
    private final SelenideElement transferWhere = $("[data-test-id='from']");
    private final SelenideElement transferTopUpButton = $("[data-test-id='action-transfer']");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage(){
        transferHead.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        doTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void doTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        transferSum.setValue(amountToTransfer);
        transferWhere.setValue(cardInfo.getCardNumber());
        transferTopUpButton.click();
    }

    public void findErrorMessage(String expectedText){
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
