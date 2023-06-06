package com.nimi.api.utility;

import java.util.Random;

public class NumberUtil {

    static Random random = new Random();

    public static int generateRandomNo(){
        int randomNo = random.nextInt(10000);
        return randomNo;
    }
}
