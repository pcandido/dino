package com.paulocandido.dino.ia;

import com.paulocandido.dino.Rand;
import com.paulocandido.dino.ga.mutation.Mutation;
import com.paulocandido.dino.ia.activation.ActivationFunction;

public class Perceptron {

    private double[] weights;
    private double bias;
    private final ActivationFunction activationFunction;

    public Perceptron(int size, ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.bias = Rand.getInstance().randGeneValue();

        this.weights = new double[size];
        for (int i = 0; i < size; i++) {
            this.weights[i] = Rand.getInstance().randGeneValue();
        }
    }

    public Perceptron(double[] weights, double bias, ActivationFunction activationFunction) {
        this.weights = weights;
        this.bias = bias;
        this.activationFunction = activationFunction;
    }

    public double calculate(double[] values) {
        double sum = bias;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * values[i];
        }

        return activationFunction.calculate(sum);
    }

    public void mutate(Mutation mutation) {
        int gene = Rand.getInstance().nextInt(weights.length + 1);
        if (gene == weights.length)
            bias = mutation.mutate(bias);
        else
            weights[gene] = mutation.mutate(weights[gene]);
    }

    public Perceptron clone() {
        double[] clonedPesos = new double[weights.length];
        System.arraycopy(weights, 0, clonedPesos, 0, weights.length);
        return new Perceptron(clonedPesos, bias, activationFunction);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double peso : weights) {
            sb.append(String.format("%.4f", peso));
            sb.append(" ");
        }

        sb.append(": ");
        sb.append(String.format("%.4f", bias));

        return sb.toString();
    }
}
