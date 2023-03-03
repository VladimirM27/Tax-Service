package com.motrechko.taxservice.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 The PasswordUtils class provides utility methods for working with passwords.
 */
public final class PasswordUtils {
    // Prevents instantiation of the class
    private PasswordUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }
    /**
     Hashes a password using the SHA-512 algorithm.
     @param password the password to be hashed
     @return a hexadecimal string representing the hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update((password + "dd").getBytes());
            byte[] hash = digest.digest();
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
