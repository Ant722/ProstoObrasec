package ru.aston.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String UUID_PATTERN = "[\\da-fA-F]{8}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{4}-[\\da-fA-F]{12}";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])(?=\\S+$)[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]{6,20}$";
    public static final String EMAIL_PATTERN = "^(?=.{1,50}$)[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
}
