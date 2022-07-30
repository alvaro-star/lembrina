package com.api.alvaro.lembrina.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {
	public static boolean nome(String nome)
    {
        final String regex = "^((\\b[A-zÀ-ú\\']{2,40}\\b)\\s*){2,}$";
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(nome);
        
        return matcher.find();
    }

    public static boolean Email(String email)
    {
        final String regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);
        
        return matcher.find();
    }

    public static boolean Senha(String senha)
    {
        final String re = "^(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{6,}$";
        final Pattern pattern = Pattern.compile(re);
        final Matcher matcher = pattern.matcher(senha);
        
        return matcher.find();
    }

    public static boolean NumberFloat(Integer number)
    {
        final String re = "^\\d{0,6}+(\\.\\d{1,2})?$";
        final Pattern pattern = Pattern.compile(re);
        final Matcher matcher = pattern.matcher(number.toString());
        
        return matcher.find();
    }

    public static boolean NumberInt(Integer number, Integer tamanho)
    {
    	final String re = "^[0-9]{0,"+tamanho+"}+$";
    	final Pattern pattern = Pattern.compile(re);
        final Matcher matcher = pattern.matcher(number.toString());
        
        return matcher.find();
    }

    public static boolean Placa(String placa)
    {
        final String regex = "[A-Z]{3}[0-9]{4}";
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(placa);
        
        return matcher.find();
    }

    public static boolean Text(String text, Integer tamanho)
    {
    	final String regex = "^[aA-zZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ]{0,"+tamanho+"}+$";
    	final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(text);
        
        return matcher.find();
    }
    
}
