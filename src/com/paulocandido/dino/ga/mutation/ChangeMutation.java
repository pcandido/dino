package com.paulocandido.dino.ga.mutation;

import com.paulocandido.dino.Rand;

public class ChangeMutation implements Mutation {

    @Override
    public double mutate(double value) {
        return Rand.getInstance().randGeneValue();
    }
}
