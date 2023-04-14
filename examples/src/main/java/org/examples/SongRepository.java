package org.examples;

import java.util.ArrayList;
public class SongRepository {

    private ArrayList<String> songs = new ArrayList<>();

    public void add (String song) {
        this.songs.add(song);
    }

    public boolean contains (String song) {
        return this.songs.contains(song);
    }

    public String remove (int i) {
        return songs.remove(i);
    }

    public int size () {
        return songs.size();
    }
}
