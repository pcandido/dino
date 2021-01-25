package com.paulocandido.dino.model;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private int generation;
    List<Dino> dinos;

    public Population(int size) {
        generation = 1;
        dinos = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dinos.add(new Dino());
        }
    }

    public List<Dino> snapshot() {
        return new ArrayList<>(dinos);
    }

    public List<Dino> getDinos() {
        return dinos;
    }

    public void setDinos(List<Dino> dinos) {
        this.dinos = dinos;
    }

    public void incGeneration() {
        this.generation++;
    }

    public int getGeneration() {
        return generation;
    }
}
