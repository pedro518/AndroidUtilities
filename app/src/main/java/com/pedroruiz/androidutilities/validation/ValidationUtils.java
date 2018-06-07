package com.pedroruiz.androidutilities.validation;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static final String DNI_PATTERN = "\\d{8}[aA-hHjJ-nNpP-tTvV-zZ]";

    public static boolean validateEmail(String email){
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email);

        return matcher.matches();
    }

    public static boolean validatePhone(String phone){
        Matcher matcher = Patterns.PHONE.matcher(phone);

        return matcher.matches();
    }

    public static boolean validateURL(String url){
        Matcher matcher = Patterns.WEB_URL.matcher(url);

        return matcher.matches();
    }

    public static boolean validateIPAddress(String ip){
        Matcher matcher = Patterns.IP_ADDRESS.matcher(ip);

        return matcher.matches();
    }

    public static boolean validateDNI(String dni){
        Pattern pattern = Pattern.compile(DNI_PATTERN);
        Matcher matcher = pattern.matcher(dni);

        return matcher.matches();
    }


}
