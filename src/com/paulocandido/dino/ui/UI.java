package com.paulocandido.dino.ui;

import com.paulocandido.dino.model.Dino;
import com.paulocandido.dino.model.Population;
import com.paulocandido.dino.model.World;
import com.paulocandido.dino.ui.drawer.DinoDrawer;
import com.paulocandido.dino.ui.drawer.ObstacleDrawer;
import com.paulocandido.dino.ui.drawer.WorldDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI implements Runnable {

    private final int width;
    private final int height;
    private final Canvas canvas;

    private final World world;
    private final Population population;

    private final WorldDrawer worldDrawer;
    private final DinoDrawer dinoDrawer;
    private final ObstacleDrawer obstacleDrawer;

    public UI(World world, Population population, int width, int height) {
        this.width = width;
        this.height = height;
        this.world = world;
        this.population = population;

        JFrame frame = new JFrame("Dino");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(canvas);
        frame.pack();

        this.worldDrawer = new WorldDrawer(width, height);
        this.dinoDrawer = new DinoDrawer(height);
        this.obstacleDrawer = new ObstacleDrawer(width, height);

        new Thread(this).start();

        frame.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) image.getGraphics();

            g.setColor(Color.white);
            g.fillRect(0, 0, this.width, this.height);

            worldDrawer.draw(g, world);
            obstacleDrawer.draw(g, world);
            population.snapshot().stream()
                    .filter(Dino::isAlive)
                    .limit(100)
                    .forEach(a -> dinoDrawer.draw(g, world, a));

            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(world.getOffset()), this.width - 150, 30);
            g.drawString("Gen: " + population.getGeneration(), this.width - 150, 50);
            g.drawString("Alive: " + population.getDinos().stream().filter(Dino::isAlive).count(), this.width - 150, 70);

            canvas.setImage(image);

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
