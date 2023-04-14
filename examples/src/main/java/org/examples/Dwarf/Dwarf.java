package org.examples.Dwarf;

import java.util.Random;

public class Dwarf {
    private String name;
    private double size;
    private double weight;
    private boolean hasBeard;
    private boolean hasHangover;

    public String getName() {
        return name;
    }


    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void shave() {
        this.hasBeard = false;
    }

    public void growBeard() {
        this.hasBeard = true;
    }

    public boolean isHungover() {
        return hasHangover;
    }

    private SongRepository knownSongs = new SongRepository();

    public Dwarf(String name, double size, double weight, boolean hasBeard) {
        this.name = name;
        this.size = size;
        this.weight = weight;
        this.hasBeard = hasBeard;
        this.hasHangover = false;
    }

    public void learnSong(String newSong) {
        this.knownSongs.add(newSong);
    }

    public void sleep() {
        this.hasHangover = false;
    }

    private String drink() {
        Random rand = new Random();
        int i = rand.nextInt(this.knownSongs.size() );
        this.hasHangover = true;
        return this.knownSongs.remove(i);
    }

    private void drinkWithoutKnownSongs() {
        this.hasHangover = true;
    }

    public boolean isBearded() {
        return this.hasBeard;
    }

    private void sing(String song) {
        System.out.println(song);
    }

    public void goesToTavern() {
        if(knownSongs.isEmpty())
        {
            drinkWithoutKnownSongs();
        }else //if is not empty
        {
            sing(this.drink());
        }

    }

    public boolean isKnown(String song) {
        return this.knownSongs.contains(song);
    }

    public SongRepository getLearnedSongs() {
        return this.knownSongs;
    }
}
