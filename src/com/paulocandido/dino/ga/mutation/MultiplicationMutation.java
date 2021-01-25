package com.paulocandido.dino.ga.mutation;

import com.paulocandido.dino.Rand;

public class MultiplicationMutation implements Mutation {

    @Override
    public double mutate(double value) {
        return value * (Rand.getInstance().nextDouble() * 4 - 2);
    }
}
