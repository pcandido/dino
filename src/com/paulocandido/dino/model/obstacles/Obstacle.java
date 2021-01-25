package com.paulocandido.dino.model.obstacles;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

public abstract class Obstacle {

    private double x;

    public Obstacle(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void move(double velocity) {
        x -= velocity;
    }

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract double getY();

    public abstract Sprite.Sprites getSprite(World world);

}
