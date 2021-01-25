package com.paulocandido.dino.ga;

import com.paulocandido.dino.Config;
import com.paulocandido.dino.Rand;
import com.paulocandido.dino.ga.mutation.ChangeMutation;
import com.paulocandido.dino.ga.mutation.MultiplicationMutation;
import com.paulocandido.dino.ga.mutation.Mutation;
import com.paulocandido.dino.ga.mutation.SumMutation;
import com.paulocandido.dino.model.Dino;

import java.util.List;

public class RandomMutations {

    public static void mutate(List<Dino> dinos) {

        dinos.forEach(a -> {
            int mutations = Rand.getInstance().nextInt(Config.MAX_NUMBER_MUTATIONS + 1);
            for (int i = 0; i < mutations; i++) {
                Mutation mutation = switch (Rand.getInstance().nextInt(3)) {
                    case 1 -> new MultiplicationMutation();
                    case 2 -> new SumMutation();
                    default -> new ChangeMutation();
                };

                a.getNeuralNetwork().mutate(mutation);
            }
        });

    }

}
