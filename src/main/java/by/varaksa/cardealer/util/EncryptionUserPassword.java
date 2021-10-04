package by.varaksa.cardealer.util;

import java.util.Base64;

/**
 * Class {@code EncryptionUserPassword} designed for encoding
 * and decoding user password
 *
 * @author Pavel Varaksa
 */
public class EncryptionUserPassword {
    /**
     * Encoding password with ASCII algorithm
     *
     * @param value {@code String} password encryption
     * @return {@code String} encrypted password
     */
    public static String encodePassword(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(value.getBytes());
    }

    /**
     * Decoding password with ASCII algorithm
     *
     * @param encrypted {@code String} encrypted password
     * @return {@code String} decrypted password
     */
    public static String decodePassword(String encrypted) {
        byte[] bytes = Base64.getDecoder().decode(encrypted);
        return new String(bytes);
    }
}
