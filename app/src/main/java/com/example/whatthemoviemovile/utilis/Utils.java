package com.example.whatthemoviemovile.utilis;

import java.util.Random;

public class Utils {
    public static int rand(){
        Random random=new Random();
        return random.nextInt(3);
    }
    public static int[] getArrMix(){
        int[] array = {0, 1, 2};
        shuffleArray(array);
        return array;
    }

    public static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
