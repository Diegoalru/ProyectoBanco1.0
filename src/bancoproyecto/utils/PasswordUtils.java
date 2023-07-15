package bancoproyecto.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    /**
     * Hashes the password using SHA-512
     *
     * @param password Password to hash
     * @return Hashed password
     */
    public static String HashPassword(String password) {
        try {
            var digest = MessageDigest.getInstance("SHA-512");
            var hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            var hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return "0x" + hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
