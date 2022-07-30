package com.api.alvaro.lembrina.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    public static boolean nome(String nome) {
        final String regex = "^((\\b[A-zÀ-ú\\']{2,40}\\b)\\s*){2,}$";
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(nome);
        
        return matcher.find();
    }
    
}
