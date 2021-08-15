package by.varaksa.cardealer.model.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle("text", new Locale("en", "EN"))),
    RU(ResourceBundle.getBundle("text", new Locale("ru", "RU")));
    private final ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
}
