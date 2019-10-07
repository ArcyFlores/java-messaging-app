package edu.northeastern.ccs.im.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Password utils test.
 */
class PasswordUtilsTest {

    /**
     * Tests the Gets salt method.
     */
    @Test
    void getSalt() {
        String salt = PasswordUtils.getSalt(10);
        assertNotNull(salt);
        assertEquals(10, salt.length());
        salt = PasswordUtils.getSalt(20);
        assertNotNull(salt);
        assertEquals(20, salt.length());
        salt = PasswordUtils.getSalt(0);
        assertEquals("", salt);
        try {
            salt = PasswordUtils.getSalt(-5);
        } catch (NegativeArraySizeException e) {
            e.getStackTrace();
        }
    }

    /**
     * Generate secure password.
     */
    @Test
    void generateSecurePassword() {
        String pw = PasswordUtils.generateSecurePassword("password", "ADho6AjXCoJwm5B1GZI1");
        assertEquals("12Livrc1NrPtIpLUCMZUiD31zbRMk8F8+QW7iSQgPnA=", pw);
        pw = PasswordUtils.generateSecurePassword("password", "ADho6AjXCoJwm5B1GZI1");
        assertEquals("12Livrc1NrPtIpLUCMZUiD31zbRMk8F8+QW7iSQgPnA=", pw);
        pw = PasswordUtils.generateSecurePassword("-1341", "nkjca8292");
        assertEquals("vgAOdwAr0PwqqUSMjuJ4cdxdFo6Bs0YIjOzMslDbm3Q=", pw);

        pw = PasswordUtils.generateSecurePassword("", "1");
        assertEquals("GarcGmkW/12VIwxBrKIN4cwP+NLu0aWsmI6Sb7pwRwg=", pw);

        try {
            pw = PasswordUtils.generateSecurePassword("", "");
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        try {
            pw = PasswordUtils.generateSecurePassword(null, "1");
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    /**
     * Verify user password.
     */
    @Test
    void verifyUserPassword() {
        String salt = PasswordUtils.getSalt(5);
        String pw = PasswordUtils.generateSecurePassword("password", salt);
        assertTrue(PasswordUtils.verifyUserPassword("password", pw, salt));

        salt = PasswordUtils.getSalt(1);
        pw = PasswordUtils.generateSecurePassword("", salt);
        assertTrue(PasswordUtils.verifyUserPassword("password", pw, salt));
    }

    @Test
    void passwordMatchTest(){
        PasswordUtils.isPasswordMatch("testPassword","password");
    }
}
