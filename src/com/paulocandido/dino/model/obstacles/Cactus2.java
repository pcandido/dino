package com.paulocandido.dino.model.obstacles;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

public class Cactus2 extends Obstacle {

    public Cactus2(double x) {
        super(x);
    }

    @Override
    public double getWidth() {
        return 51;
    }

    @Override
    public double getHeight() {
        return 50;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public Sprite.Sprites getSprite(World world) {
        return Sprite.Sprites.cactus2;
    }
}
