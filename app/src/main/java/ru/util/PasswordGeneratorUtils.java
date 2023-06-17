package ru.util;

import lombok.experimental.UtilityClass;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Random;

@UtilityClass
public class PasswordGeneratorUtils {

    public static String generatePassword() {
        CharacterRule lowerCaseChars = new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule upperCaseChars = new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule digitChars = new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule characterData = new CharacterRule(new CharacterData() {
            @Override
            public String getErrorCode() {
                return null;
            }

            @Override
            public String getCharacters() {
                return "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
            }
        });
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generatePassword(new Random().nextInt(15) + 6,
                lowerCaseChars, upperCaseChars, digitChars, characterData);
    }
}
