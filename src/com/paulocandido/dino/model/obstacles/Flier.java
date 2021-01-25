package com.paulocandido.dino.model.obstacles;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

public class Flier extends Obstacle {

    private final double y;

    public Flier(double x, FlyLevel level) {
        super(x);
        y = switch (level) {
            case high -> 40;
            case low -> 20;
        };
    }

    @Override
    public double getWidth() {
        return 46;
    }

    @Override
    public double getHeight() {
        return 40;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Sprite.Sprites getSprite(World world) {
        return (world.getOffset() / 40) % 2 == 0 ? Sprite.Sprites.flier_up : Sprite.Sprites.flier_down;
    }

    public enum FlyLevel {
        low, high
    }
}
