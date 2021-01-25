package com.paulocandido.dino.ui.drawer;

import com.paulocandido.dino.model.World;
import com.paulocandido.dino.model.obstacles.Obstacle;
import com.paulocandido.dino.ui.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObstacleDrawer {

    private final int canvasWidth;
    private final int canvasHeight;

    public ObstacleDrawer(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void draw(Graphics2D canvas, World world) {
        List<Obstacle> obstacles = new ArrayList<>(world.getObstacles());
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() > canvasWidth)
                break;

            Sprite.getInstance().draw(
                    canvas,
                    obstacle.getSprite(world),
                    (int) obstacle.getX(),
                    canvasHeight - (int) obstacle.getHeight() - (int) obstacle.getY() - (int) World.FLOOR,
                    (int) obstacle.getWidth(),
                    (int) obstacle.getHeight()
            );
        }
    }


}
