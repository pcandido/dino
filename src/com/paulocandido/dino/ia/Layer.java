package com.paulocandido.dino.ia;

import com.paulocandido.dino.Rand;
import com.paulocandido.dino.ga.mutation.Mutation;
import com.paulocandido.dino.ia.activation.ActivationFunction;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Layer {

    private final Perceptron[] perceptrons;

    public Layer(int qtyPerceptrons, int sizePreviousLayer, ActivationFunction activationFunction) {
        perceptrons = new Perceptron[qtyPerceptrons];

        for (int i = 0; i < qtyPerceptrons; i++) {
            perceptrons[i] = new Perceptron(sizePreviousLayer, activationFunction);
        }
    }

    public Layer(Perceptron[] perceptrons) {
        this.perceptrons = perceptrons;
    }

    public double[] calculate(double[] values) {
        double[] saida = new double[perceptrons.length];

        for (int i = 0; i < perceptrons.length; i++) {
            saida[i] = perceptrons[i].calculate(values);
        }

        return saida;
    }

    public void mutate(Mutation mutation) {
        perceptrons[Rand.getInstance().nextInt(perceptrons.length)].mutate(mutation);
    }

    public Layer clone() {
        Perceptron[] cloned = new Perceptron[perceptrons.length];
        for (int i = 0; i < perceptrons.length; i++) {
            cloned[i] = perceptrons[i].clone();
        }

        return new Layer(cloned);
    }

    @Override
    public String toString() {
        return Arrays.stream(perceptrons).map(a -> "( " + a.toString() + " )").collect(Collectors.joining(" "));
    }
}
