package by.varaksa.cardealer;

import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String s1 = "mm";

        String hash = encodePassword(s1);
        String hash2 = decodePassword(hash);

        System.out.println(hash);
        System.out.println(hash2);


    }

    public static String encodePassword(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(value.getBytes());
    }

    public static String decodePassword(String encrypted) {
        byte[] bytes = Base64.getDecoder().decode(encrypted);
        return new String(bytes);
    }
}
