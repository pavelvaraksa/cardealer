package by.varaksa.cardealer;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static final String LANGUAGE_RU = "ru";
    public static final String COUNTRY_RU = "RU";

    public static void main(String[] args) {
        Locale locale = new Locale(LANGUAGE_RU, COUNTRY_RU);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);

    }
}
