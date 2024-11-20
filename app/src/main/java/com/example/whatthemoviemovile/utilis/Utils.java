package com.example.whatthemoviemovile.utilis;

import java.util.Random;

public class Utils {
    public static int rand(){
        Random random=new Random();
        return random.nextInt(3);
    }
}
