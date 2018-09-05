package com.example.rahul.zylaassignment.Model;


public class SongDataModel  {

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    String Name;
    String Artist;
    String Album;

    @Override
    public String toString() {
        return "SongDataModel{" +
                "Name='" + Name + '\'' +
                ", Artist='" + Artist + '\'' +
                ", Album='" + Album + '\'' +
                '}';
    }
}
