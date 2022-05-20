package dev.alex6.rsa;

import java.security.SecureRandom;

public class RSAKeyGenerator {
    private static final int MILLER_RABIN_TRIES = 25;
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Get a random prime number
     * @return the random primary integer
     */
    public int getRandomPrimeInteger() {
        int x = 0;
        while (x == 0 || !isNumberPrime(x)) {
            // First we retrieve, a random number between -(2^31) and 2^31
            x = secureRandom.nextInt();
            // if it's negative we take his absolute value
            x = (x < 0 ? Math.abs(x) : x);
        }
        return x;
    }

    public int getRandomIntegerPrimeWith(int y) {
        int x = 0;
        while (x == 0 || !isNumberPrime(x)) {
            // First we retrieve, a random number between -(2^31) and 2^31
            x = secureRandom.nextInt();
            // if it's negative we take his absolute value
            x = (x < 0 ? Math.abs(x) : x);
            // Then we test if he is prime with y
        }
        return x;
    }



    /**
     * Compute x^y mod n
     * @param x the base number
     * @param y the exponent
     * @param n the module
     * @return x^y mod n
     */
    public static int powerMod(int x, int y, int n) {
        int result = 1;
        x %= n;
        while (y > 0) {
            if ((y & 1) != 0)
                result = (result * x) % n;
            y >>= 1;
            x = x * x % n;
        }
        return result < 0 ? result + n : result;
    }

    public static int getPGCD(int a, int b){
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * Miller Rabin test
     * @param x the number to test
     * @return true is it's a prime number, false otherwise
     */
    public boolean isNumberPrime(int x) {
        // We know that 2 and 3 are prime numbers,
        // to avoid test errors we explicitly return true without running the test
        if (x == 2 || x == 3) return true;
        if (x % 2 == 0) return false;
        int r = 0;
        int s = x - 1;
        while (s % 2 == 0) {
            r += 1;
            s = Math.floorDiv(s, 2);
        }
        for (int i = 0; i < MILLER_RABIN_TRIES; i++) {
            int a = secureRandom.nextInt(2, x - 1);
            int n = powerMod(a, s, x);
            if (n == 1 || n == x - 1) continue;
            indicator: {
                for (int j = 0; j < r - 1; j++) {
                    n = powerMod(n, 2, x);
                    if (n == x - 1) break indicator;
                }
                return false;
            }
        }
        return true;
    }
}
