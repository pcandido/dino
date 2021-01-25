package com.paulocandido.dino;

import java.util.Random;

public class Rand {

    private static Rand rand;
    private final Random random;

    public static Rand getInstance() {
        if (rand == null) {
            rand = new Rand();
        }
        return rand;
    }

    private Rand() {
        this.random = new Random(Config.RANDOM_SEED);
    }

    public double randGeneValue() {
        return (random.nextInt(20001) / 10.0) - 1000.0;
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

}
