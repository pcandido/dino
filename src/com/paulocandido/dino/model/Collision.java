package com.paulocandido.dino.model;

public class Collision {

    public static boolean collided(Dino dino, World world) {
        return world.getObstacles().stream().anyMatch(obstacle ->
                obstacle.getX() < dino.getX() + dino.getWidth() &&
                        obstacle.getX() + obstacle.getWidth() > dino.getX() &&
                        obstacle.getY() < dino.getY() + dino.getHeight() &&
                        obstacle.getY() + obstacle.getHeight() > dino.getY()
        );
    }

}
