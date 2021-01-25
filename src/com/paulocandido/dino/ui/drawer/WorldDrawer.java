package com.paulocandido.dino.ui.drawer;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.Sprite;

import java.awt.*;

public class WorldDrawer {

    private final int canvasWidth;
    private final int canvasHeight;

    public WorldDrawer(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void draw(Graphics2D canvas, World world) {
        int groundWidth = Sprite.Sprites.ground.width;
        int groundHeight = Sprite.Sprites.ground.height;
        int curr = -world.getOffset() % groundWidth;

        do {
            Sprite.getInstance().draw(
                    canvas,
                    Sprite.Sprites.ground,
                    curr,
                    canvasHeight - 10 - (int) World.FLOOR,
                    groundWidth,
                    groundHeight
            );

            curr += groundWidth;
        } while (curr < canvasWidth);
    }


}
