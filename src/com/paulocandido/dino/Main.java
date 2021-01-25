package com.paulocandido.dino;

import com.paulocandido.dino.ga.RandomMutations;
import com.paulocandido.dino.ga.Tournament;
import com.paulocandido.dino.model.Dino;
import com.paulocandido.dino.model.Population;
import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.UI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        World world = new World(Config.GRAVITY);
        Population population = new Population(Config.POPULATION_SIZE);

        new UI(world, population, 1000, 400);

        new Thread(() -> {
            while (true) {
                world.reset();

                while (population.getDinos().stream().anyMatch(Dino::isAlive)) {
                    world.update();
                    population.getDinos().forEach(a -> a.update(world));

                    try {
                        Thread.sleep(Config.SIMULATION_VELOCITY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }

                Dino best = population.getDinos().stream().max(Comparator.comparing(Dino::getFitness)).orElseThrow();
                System.out.printf("%d: %.1f\n", population.getGeneration(), best.getFitness());

                List<Dino> newGen = Tournament
                        .draw(population.getDinos(), Dino::getFitness, Config.POPULATION_SIZE / 2 - 1, false)
                        .stream()
                        .flatMap(a -> Arrays.stream(new Dino[]{a.clone(), a.clone()}))
                        .collect(Collectors.toList());

                newGen.add(best.clone());
                RandomMutations.mutate(newGen);
                newGen.add(best.clone());

                population.setDinos(newGen);
                population.incGeneration();
            }
        }).start();
    }

}
