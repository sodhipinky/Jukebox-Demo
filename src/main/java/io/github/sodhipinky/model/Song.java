package io.github.sodhipinky.model;

import java.util.Objects;

public class Song {
    private int songId;
    private String songTitle;

    public Song() {
    }

    public Song(int songId, String songTitle) {
        this.songId = songId;
        this.songTitle = songTitle;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getSongId() == song.getSongId() && Objects.equals(getSongTitle(), song.getSongTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSongId(), getSongTitle());
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                '}';
    }
}
