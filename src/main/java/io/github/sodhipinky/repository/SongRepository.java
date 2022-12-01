package io.github.sodhipinky.repository;

import io.github.sodhipinky.model.Song;
import io.github.sodhipinky.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    Connection connection;
    List<Song> songs;

    public SongRepository() {
        songs = new ArrayList<>();
        try {
            connection = new DatabaseService().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Song> getSongs() throws SQLException {
        String selectQuery = "select * from `music_player`.`song`;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while (resultSet.next()) {
            Song song = new Song();
            song.setSongId(resultSet.getInt("song_id"));
            song.setSongTitle(resultSet.getString("song_title"));
            songs.add(song);
        }
        return songs;
    }
    public Song getSong(int songId) throws SQLException {
        Song song = new Song();
        String selectQuery = "select * from `music_player`.`song` where `song_id` = ?;";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setInt(1, songId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            song.setSongId(resultSet.getInt("song_id"));
            song.setSongTitle(resultSet.getString("song_title"));
        }
        return song;
    }
}
