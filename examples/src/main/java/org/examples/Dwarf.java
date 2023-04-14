package org.examples;

import java.util.ArrayList;
import java.util.Random;

public class Dwarf {
    private String name;
    private double size;
    private double weight;
    private boolean hasBeard;
    private boolean hasHangover;
    private ArrayList<String> knownSongs = new ArrayList<>();

    public Dwarf (String name, double size, double weight, boolean hasBeard) {
        this.name        = name;
        this.size        = size;
        this.weight      = weight;
        this.hasBeard    = hasBeard;
        this.hasHangover = false;
    }

    public void learnSong (String newSong) {
        this.knownSongs.add(newSong);
    }

    public void sleep () {
        this.hasHangover = false;
    }

    private String drink () {
        Random rand = new Random();
        int i = rand.nextInt(this.knownSongs.size()+1);
        this.hasHangover = true;
        return this.knownSongs.remove(i);
    }

    public boolean isBearded () {
        return this.hasBeard;
    }

    private void sing (String song) {
        System.out.println(song);
    }

    public void goesToTavern () {
        sing(this.drink());
    }

    public boolean isKnown (String song) {
        return this.knownSongs.contains(song);
    }

    public ArrayList<String> get () {
        return this.knownSongs;
    }
}
