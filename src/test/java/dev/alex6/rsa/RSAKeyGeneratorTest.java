package dev.alex6.rsa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RSAKeyGeneratorTest {
    private static RSAKeyGenerator keyGenerator;

    @BeforeAll
    static void init() {
        keyGenerator = new RSAKeyGenerator();
    }

    @Test
    void powerMod() {
        assertEquals(RSAKeyGenerator.powerMod(31, 333, 63), 55);
    }

    @Test
    void isNumberPrime() {
        assertTrue(keyGenerator.isNumberPrime(11));
    }
}