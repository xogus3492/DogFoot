package global.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SessionKeyEncryption {
	private static final String SALT = "Z5a51Io0W9kuyTGjpkfK04==";

    // 세션키를 SHA-256 알고리즘으로 해싱하는 메소드
    public static String hashKey(String key) {
        String generatedKey = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes());
            byte[] bytes = md.digest(key.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02x", bytes[i]));
            }
            generatedKey = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedKey;
    }

    // 비밀번호를 검증하는 메소드
    public static boolean checkKey(String key, String hashedKey) {
        String newHash = hashKey(key);
        return newHash.equals(hashedKey);
    }

    public static void main(String[] args) {
        String key = "mysecretKey";

        // 비밀번호 해싱
        String hashedKey = hashKey(key);
        System.out.println("Hashed key: " + hashedKey);

        // 비밀번호 검증
        boolean isKeyMatch = checkKey(key, hashedKey);
        System.out.println("key match: " + isKeyMatch);
    }
}

