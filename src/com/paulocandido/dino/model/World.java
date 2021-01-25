package com.paulocandido.dino.model;

import com.paulocandido.dino.Rand;
import com.paulocandido.dino.model.obstacles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class World {

    public static final double FLOOR = 50;

    private final double gravity;
    private final long seed;
    private int offset;
    private Random random;
    private List<Obstacle> obstacles;

    public World(double gravity) {
        this.gravity = gravity;
        this.seed = (long) (Rand.getInstance().nextDouble() * 100000);
        reset();
    }

    public void reset() {
        this.random = new Random(seed);
        this.offset = 0;
        this.obstacles = new ArrayList<>();
        this.obstacles.add(new Cactus1(500));
    }

    public double getGravity() {
        return gravity;
    }

    public int getOffset() {
        return offset;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public double getVelocity() {
        return offset / 10000.0 + 3;
    }

    public void update() {
        double velocity = getVelocity();
        offset += velocity;

        obstacles = obstacles.stream().peek(a -> a.move(velocity)).filter(a -> a.getX() + a.getWidth() > 0).collect(Collectors.toList());
        double lastObstacle = obstacles.stream().mapToDouble(Obstacle::getX).max().orElse(0);
        while (lastObstacle < 6000) {
            double nextObstacle = lastObstacle + this.random.nextGaussian() * 80 + 400;

            var obstacle = switch (random.nextInt(5)) {
                case 1 -> new Cactus2(nextObstacle);
                case 2 -> new Cactus3(nextObstacle);
                case 3 -> new Flier(nextObstacle, random.nextBoolean() ? Flier.FlyLevel.high : Flier.FlyLevel.low);
                case 4 -> new BigFlier(nextObstacle);
                default -> new Cactus1(nextObstacle);
            };

            obstacles.add(obstacle);
            lastObstacle = nextObstacle;
        }

    }
}
