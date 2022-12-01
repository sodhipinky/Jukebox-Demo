package io.github.sodhipinky.model;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private List<Song> songs;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist playlist)) return false;
        return getPlaylistId() == playlist.getPlaylistId() && Objects.equals(getPlaylistName(), playlist.getPlaylistName()) && Objects.equals(getSongs(), playlist.getSongs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaylistId(), getPlaylistName(), getSongs());
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistName='" + playlistName + '\'' +
                ", songs=" + songs +
                '}';
    }
}
