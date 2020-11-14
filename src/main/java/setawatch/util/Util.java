package setawatch.util;

import java.util.Date;
import java.util.Random;

public class Util {
    private static final Random GEN = new Random(new Date().getTime());

    public static int randomInt(int max){
        return GEN.nextInt(max);
    }

    public static int randomInt(int min, int max){
        return min + GEN.nextInt(max - min);
    }

    private Util(){}
}
