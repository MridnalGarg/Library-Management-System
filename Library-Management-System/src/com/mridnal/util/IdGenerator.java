package com.mridnal.util;

import java.util.UUID;

public class IdGenerator {
    public static int loanIdCounter = 0;

    public static String generateShortUUID() {
        return UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    public static int nextLoadId(){
        return loanIdCounter++;
    }

}
