package com.nimi.api.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String retrieveSubStringFromRegex(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group();
        }else {
            return null;
        }
    }
}
