package com.paulocandido.dino.ga;

import com.paulocandido.dino.Rand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Roulette {

    public static <T> List<T> draw(List<T> items, GetFitness<T> getFitness, int qty, boolean reversed) {
        var mapped = items.stream().map(a -> new Map<>(a, getFitness.apply(a))).collect(Collectors.toCollection(ArrayList::new));
        var drown = new ArrayList<T>();

        for (int i = 0; i < qty; i++) {
            drown.add(draw(mapped, reversed));
        }

        return drown;
    }


    public static <T> T draw(List<T> items, GetFitness<T> getFitness, boolean reversed) {
        var mapped = items.stream().map(a -> new Map<>(a, getFitness.apply(a))).collect(Collectors.toList());
        return draw(mapped, reversed);
    }

    private static <T> T draw(List<Map<T>> items, boolean reversed) {

        var min = items.stream().mapToDouble(a -> a.fitness).min().getAsDouble();
        var max = items.stream().mapToDouble(a -> a.fitness).max().getAsDouble();

        if (min == max) return items.get(Rand.getInstance().nextInt(items.size())).obj;

        items.forEach(a -> {
            a.normalizedFitness = (a.fitness - min) / (max - min);
            if (reversed)
                a.normalizedFitness = 1 - a.normalizedFitness;
        });

        var sum = items.stream().mapToDouble(a -> a.normalizedFitness).sum();
        var drown = Rand.getInstance().nextDouble() * sum;
        var accumulated = 0d;

        for (var x : items) {
            if (drown >= accumulated && drown < x.normalizedFitness + accumulated)
                return x.obj;
            accumulated += x.normalizedFitness;
        }

        return null;
    }


    public interface GetFitness<T> {
        double apply(T obj);
    }

    private static class Map<T> {

        private final T obj;
        private final double fitness;
        private Double normalizedFitness;

        Map(T obj, double fitness) {
            this.obj = obj;
            this.fitness = fitness;
        }

    }

}
