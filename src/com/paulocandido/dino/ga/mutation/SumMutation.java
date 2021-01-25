package com.paulocandido.dino.ga.mutation;

import com.paulocandido.dino.Rand;

public class SumMutation implements Mutation {

    @Override
    public double mutate(double value) {
        return value + Rand.getInstance().randGeneValue() / 100.0;
    }
}
