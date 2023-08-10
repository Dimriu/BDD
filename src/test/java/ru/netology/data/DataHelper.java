package ru.netology.data;

import lombok.Value;


public class DataHelper {

    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardValue {
        String cardNumber;
    }

    public static CardValue getFirstCardNumber() {
        return new CardValue("5559 0000 0000 0001");
    }

    public static CardValue getSecondCardNumber() {
        return new CardValue("5559 0000 0000 0002");
    }


}
