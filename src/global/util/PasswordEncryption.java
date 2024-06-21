package global.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryption {
	private static final String SALT = "ZUa59Io0WHkutTGkpkyK0Q==";

    // 비밀번호를 SHA-256 알고리즘으로 해싱하는 메소드
    public static String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02x", bytes[i]));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    // 비밀번호를 검증하는 메소드
    public static boolean checkPassword(String password, String hashedPassword) {
        String newHash = hashPassword(password);
        return newHash.equals(hashedPassword);
    }

    public static void main(String[] args) {
        String password = "mysecretpassword";

        // 비밀번호 해싱
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // 비밀번호 검증
        boolean isPasswordMatch = checkPassword(password, hashedPassword);
        System.out.println("Password match: " + isPasswordMatch);
    }
}

