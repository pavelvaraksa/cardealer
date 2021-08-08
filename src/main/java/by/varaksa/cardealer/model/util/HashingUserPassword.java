package by.varaksa.cardealer.model.util;

import java.util.Base64;

public class HashingUserPassword {
    public static String encodePassword(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(value.getBytes());
    }

    public static String decodePassword(String encrypted) {
        byte[] bytes = Base64.getDecoder().decode(encrypted);
        return new String(bytes);
    }
}
