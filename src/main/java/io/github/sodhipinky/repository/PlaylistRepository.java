package io.github.sodhipinky.repository;

import io.github.sodhipinky.model.Playlist;
import io.github.sodhipinky.model.Song;
import io.github.sodhipinky.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepository {
    Connection connection;

    public PlaylistRepository() {
        try {
            connection = new DatabaseService().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Playlist createPlaylist(String playlistName) throws SQLException {
        Playlist playlist = new Playlist();
        String insertQuery = "insert into `music_player`.`playlist` (`playlist_name`) values (?);";
        PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, playlistName);
        int result = statement.executeUpdate();
        if(result > 0){
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()){
                playlist.setPlaylistId(keys.getInt(1));
                playlist.setPlaylistName(playlistName);
            }
        }
        return playlist;
    }

    public boolean addSongsToPlaylist(int playlistId, String songIds) throws SQLException {
        String updateQuery = "update `music_player`.`playlist` set `song_ids` = ? where `playlist_id` = ?;";
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, songIds);
        statement.setInt(2, playlistId);
        int result = statement.executeUpdate();
        return result > 0;
    }
    public List<Song> getSongsFromPlaylist(int playlistId) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String query = "select * from `music_player`.`playlist` where `playlist_id` = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, playlistId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String songIds = resultSet.getString("song_ids");
            String[] songIdArray = songIds.split(",");
            for (String songId : songIdArray) {
                Song song = new SongRepository().getSong(Integer.parseInt(songId));
                songs.add(song);
            }
        }
        return songs;
    }
}
