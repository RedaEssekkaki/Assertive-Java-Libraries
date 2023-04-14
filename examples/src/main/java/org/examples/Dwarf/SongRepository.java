package org.examples.Dwarf;

import java.util.ArrayList;
public class SongRepository {

    private ArrayList<String> songs = new ArrayList<>();

    void add (String song) {
        this.songs.add(song);
    }

    public boolean contains (String song) {
        return this.songs.contains(song);
    }

    String remove (int i) {
        return songs.remove(i);
    }

    public int size () {
        return songs.size();
    }

    public boolean isEmpty(){return songs.isEmpty();}
}
