package ru.aston.util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.util.PasswordGeneratorUtils;

public class PasswordGeneratorUtilsTest {

    @Test
    public void testGeneratePassword() {
        String generatePassword = PasswordGeneratorUtils.generatePassword();
        Assertions.assertNotNull(generatePassword);
        Assertions.assertTrue(generatePassword.length() >= 6 && generatePassword.length() <= 20);
        Assertions.assertTrue(generatePassword.matches(".*[a-z].*"));
        Assertions.assertTrue(generatePassword.matches(".*[A-Z].*"));
        Assertions.assertTrue(generatePassword.matches(".*\\d.*"));
        Assertions.assertTrue(generatePassword.matches(".*[!#$%&'()*+,-./:;<=>?@\\[\\]^_`\\{|}~].*"));
    }
}
