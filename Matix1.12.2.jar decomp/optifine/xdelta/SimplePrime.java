// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

public class SimplePrime
{
    public static long belowOrEqual(long number) {
        if (number < 2L) {
            return 0L;
        }
        if (number == 2L) {
            return 2L;
        }
        if ((number & 0x1L) == 0x0L) {
            --number;
        }
        while (!testPrime(number)) {
            number -= 2L;
            if (number <= 2L) {
                return 2L;
            }
        }
        return number;
    }
    
    public static long aboveOrEqual(long number) {
        if (number <= 2L) {
            return 2L;
        }
        if ((number & 0x1L) == 0x0L) {
            ++number;
        }
        while (!testPrime(number)) {
            number += 2L;
            if (number < 0L) {
                return 0L;
            }
        }
        return number;
    }
    
    public static boolean testPrime(final long number) {
        if (number == 2L) {
            return true;
        }
        if (number < 2L) {
            return false;
        }
        if ((number & 0x1L) == 0x0L) {
            return false;
        }
        for (long i = (long)Math.floor(Math.sqrt((double)number)), j = 3L; j <= i; j += 2L) {
            if (number % j == 0L) {
                return false;
            }
        }
        return true;
    }
}
