package com.paulocandido.dino.ui.drawer;

import com.paulocandido.dino.model.Dino;
import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

import java.awt.*;

public class DinoDrawer {

    private final int canvasHeight;

    public DinoDrawer(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public void draw(Graphics2D canvas, World world, Dino dino) {

        if (!dino.isAlive()) return;

        int step = world.getOffset() / 20;

        Sprite.Sprites sprite = Sprite.Sprites.dino;
        if (dino.isOnTheGround()) {
            if (step % 2 == 0)
                sprite = dino.isCrawled() ? Sprite.Sprites.dino_crawl_step_left : Sprite.Sprites.dino_step_left;
            else
                sprite = dino.isCrawled() ? Sprite.Sprites.dino_crawl_step_right : Sprite.Sprites.dino_step_right;
        }

        Sprite.getInstance().draw(
                canvas,
                sprite,
                (int) dino.getX(),
                canvasHeight - (int) dino.getHeight() - (int) dino.getY() - (int) World.FLOOR,
                (int) dino.getWidth(),
                (int) dino.getHeight()
        );
    }

}
