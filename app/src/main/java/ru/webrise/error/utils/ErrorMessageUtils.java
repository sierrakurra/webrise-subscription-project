package ru.webrise.error.utils;

public class ErrorMessageUtils {

    public static String insertParameters(String message, Object[] parameters) {
        if (parameters == null || parameters.length == 0) {
            return message;
        }
        for (int i = 0; i < parameters.length; i++) {
            message = message.replace(buildToken(i), parameters[i].toString());
        }
        return message;
    }

    private static String buildToken(int i) {
        return "{" + i + "}";
    }

}
