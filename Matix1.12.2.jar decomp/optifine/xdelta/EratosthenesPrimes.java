// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

public class EratosthenesPrimes
{
    static BitArray sieve;
    static int lastInit;
    
    public static synchronized void reset() {
        EratosthenesPrimes.sieve = null;
        EratosthenesPrimes.lastInit = -1;
    }
    
    public static synchronized void init(int maxNumber) {
        if (maxNumber > EratosthenesPrimes.lastInit) {
            int i = (int)Math.ceil(Math.sqrt(maxNumber));
            EratosthenesPrimes.lastInit = maxNumber;
            maxNumber >>= 1;
            ++maxNumber;
            i >>= 1;
            ++i;
            (EratosthenesPrimes.sieve = new BitArray(maxNumber + 1)).set(0, true);
            for (int j = 1; j <= i; ++j) {
                if (!EratosthenesPrimes.sieve.get(j)) {
                    for (int k = (j << 1) + 1, l = j * ((j << 1) + 2); l <= maxNumber; l += k) {
                        EratosthenesPrimes.sieve.set(l, true);
                    }
                }
            }
        }
    }
    
    public static synchronized int[] getPrimes(final int maxNumber) {
        final int i = primesCount(maxNumber);
        if (i <= 0) {
            return new int[0];
        }
        if (maxNumber == 2) {
            return new int[] { 2 };
        }
        init(maxNumber);
        final int[] aint = new int[i];
        final int j = maxNumber - 1 >> 1;
        int k = 0;
        aint[k++] = 2;
        for (int l = 1; l <= j; ++l) {
            if (!EratosthenesPrimes.sieve.get(l)) {
                aint[k++] = (l << 1) + 1;
            }
        }
        return aint;
    }
    
    public static synchronized int primesCount(final int number) {
        if (number < 2) {
            return 0;
        }
        init(number);
        final int i = number - 1 >> 1;
        int j = 1;
        for (int k = 1; k <= i; ++k) {
            if (!EratosthenesPrimes.sieve.get(k)) {
                ++j;
            }
        }
        return j;
    }
    
    public static synchronized int belowOrEqual(final int number) {
        if (number < 2) {
            return -1;
        }
        if (number == 2) {
            return 2;
        }
        init(number);
        int j;
        for (int i = j = number - 1 >> 1; j > 0; --j) {
            if (!EratosthenesPrimes.sieve.get(j)) {
                return (j << 1) + 1;
            }
        }
        return -1;
    }
    
    public static int below(final int number) {
        return belowOrEqual(number - 1);
    }
    
    static {
        EratosthenesPrimes.lastInit = -1;
    }
}
