package com.paulocandido.dino.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {

    private static Sprite instance;

    public static Sprite getInstance() {
        if (instance == null) {
            try {
                instance = new Sprite();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return instance;
    }

    BufferedImage image;

    public Sprite() throws IOException {
        image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("sprite.png")));
    }

    public void draw(Graphics2D g, Sprites sprite, int x, int y, int width, int height) {
        g.drawImage(image, x, y, x + width, y + height, sprite.x, sprite.y, sprite.x + sprite.width, sprite.y + sprite.height, null);
    }

    public enum Sprites {

        ground(3, 54, 1200, 12),
        dino(846, 3, 47, 44),
        dino_step_left(937, 3, 44, 47),
        dino_step_right(981, 3, 44, 47),
        dino_crawl_step_left(1111, 20, 59, 30),
        dino_crawl_step_right(1170, 20, 59, 30),
        cactus1(333, 3, 25, 50),
        cactus2(432, 3, 51, 50),
        cactus3(408, 3, 75, 50),
        flier_up(181, 3, 46, 40),
        flier_down(135, 3, 46, 40);

        public final int x, y, width, height;

        Sprites(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
}
