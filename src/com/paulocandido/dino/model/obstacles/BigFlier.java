package com.paulocandido.dino.model.obstacles;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

public class BigFlier extends Obstacle {

    public BigFlier(double x) {
        super(x);
    }

    @Override
    public double getWidth() {
        return 138;
    }

    @Override
    public double getHeight() {
        return 120;
    }

    @Override
    public double getY() {
        return 40;
    }

    @Override
    public Sprite.Sprites getSprite(World world) {
        return (world.getOffset() / 100) % 2 == 0 ? Sprite.Sprites.flier_up : Sprite.Sprites.flier_down;
    }

}
