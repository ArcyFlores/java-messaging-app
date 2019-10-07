package edu.northeastern.ccs.im.Utils;

import edu.northeastern.ccs.im.CRUD;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * The type Password utils.
 */
public class PasswordUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    /**
     * Gets salt.
     *
     * @param length the length
     * @return the salt
     */
    static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    /**
     * Hash byte [ ].
     *
     * @param password the password
     * @param salt     the salt
     * @return the byte [ ]
     */
    private static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = null;
        try {
            spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            Arrays.fill(password, Character.MIN_VALUE);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (Exception e) {
            return null;
        } finally {
            if (spec != null) spec.clearPassword();
        }
    }

    /**
     * Generate secure password string.
     *
     * @param identityCode the password
     * @param salt         the salt
     * @return the string
     */
    static String generateSecurePassword(String identityCode, String salt) {
        if (identityCode.equals("")) {
            identityCode = "password";
        }
        try {
            String returnValue = null;
            byte[] securePassword = hash(identityCode.toCharArray(), salt.getBytes());

            returnValue = Base64.getEncoder().encodeToString(securePassword);

            return returnValue;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verify user password boolean.
     *
     * @param providedPassword the provided password
     * @param securedPassword  the secured password
     * @param salt             the salt
     * @return true if values are equal, false otherwise
     */
    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean returnValue = false;
        try {
            // Generate New secure password with the same salt
            String newSecurePassword = generateSecurePassword(providedPassword, salt);

            // Check if two passwords are equal
            returnValue = newSecurePassword == null ? false : newSecurePassword.equalsIgnoreCase(securedPassword);

            return returnValue;
        } catch (Exception e) {
            return false;
        }

    }

  /**
   * Encrypt a user's password.
   *
   * @param userName the user name
   * @param userID   the user id
   * @param password the password for the user to input.
   */
  public static void setPassword(String userName, String userID, String password) {
    CRUD crud = new CRUD();

    String salt = PasswordUtils.getSalt(10);
    String securePW = PasswordUtils.generateSecurePassword(password, salt);
    crud.createNewMemberProfile(userName, userID, securePW, salt);
  }

  /**
   * Is password match boolean.
   *
   * @param name     the name
   * @param password the password
   * @return the boolean
   */
  public static boolean isPasswordMatch(String name, String password) {
    CRUD crud = new CRUD();
    return PasswordUtils.verifyUserPassword(password, crud.readPasswordFromUser(name),
            crud.readSaltFromUser(name));
  }

  /**
   * Helper function reads database for instance of 'this' userName. Return true if userName does
   * not exist in database.
   *
   * @param userName the name of the file to read
   * @return boolean Indicating whether or not username is available to be added, otherwise return
   * false.
   */
  public static boolean userNameAvailable(String userName) {
    CRUD crud = new CRUD();
    return crud.memberProfileExists(userName);
  }
}