package com.paulocandido.dino.model;

import com.paulocandido.dino.Rand;
import com.paulocandido.dino.ia.NeuralNetwork;

public class Dino {

    private final double x;
    private double y;
    private double width;
    private double height;
    private double yVelocity;
    private double fitness;
    private boolean crawled;
    private boolean alive;
    private final NeuralNetwork neuralNetwork;

    public Dino() {
        this(new NeuralNetwork(5, 2, 6));
    }

    public Dino(NeuralNetwork neuralNetwork) {
        this.x = Rand.getInstance().nextInt(200) + 50;
        this.reset();
        this.neuralNetwork = neuralNetwork;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFitness() {
        return fitness;
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public boolean isOnTheGround() {
        return y == 0;
    }

    public boolean isCrawled() {
        return crawled;
    }

    public void setCrawled(boolean crawled) {
        this.crawled = crawled;

        if (crawled) {
            this.width = 59;
            this.height = 30;
        } else {
            this.width = 44;
            this.height = 47;
        }
    }

    public void jump() {
        if (isOnTheGround()) {
            yVelocity = 6d;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void update(World world) {
        if (!alive) return;

        var firstObstacle = 0;
        while (world.getObstacles().get(firstObstacle).getX() < this.getX()) {
            firstObstacle++;
        }

        var obstacle1 = world.getObstacles().get(firstObstacle);

        double[] saida = neuralNetwork.calculate(new double[]{
                world.getVelocity(),
                obstacle1.getX() - getX(),
                obstacle1.getWidth(),
                obstacle1.getY(),
                obstacle1.getHeight()
        });

        if (saida[0] > 0) jump();
        setCrawled(saida[1] > 0);

        if (!isOnTheGround() && isCrawled()) {
            setCrawled(false);
        }

        y = Math.max(0d, y + yVelocity);
        if (y == 0) {
            yVelocity = 0;
        } else {
            yVelocity = yVelocity - world.getGravity();
        }

        if (Collision.collided(this, world)) {
            alive = false;
        } else {
            fitness = world.getOffset() + getX();
        }
    }

    public void reset() {
        this.y = 0;
        this.yVelocity = 0;
        this.fitness = 0;
        this.crawled = false;
        this.alive = true;
    }

    public Dino clone() {
        return new Dino(this.neuralNetwork.clone());
    }

}
