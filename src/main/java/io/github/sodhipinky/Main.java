package io.github.sodhipinky;

import io.github.sodhipinky.model.Playlist;
import io.github.sodhipinky.model.Song;
import io.github.sodhipinky.repository.PlaylistRepository;
import io.github.sodhipinky.repository.SongRepository;
import io.github.sodhipinky.service.DatabaseService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseService databaseService = new DatabaseService();
        SongRepository songRepository = new SongRepository();
        PlaylistRepository playlistRepository = new PlaylistRepository();
        try {
            songRepository.getSongs().forEach(System.out::println);
            System.out.println("enter the name of the playlist to be created: ");
            String playlistName = scanner.nextLine();
            Playlist playlist = playlistRepository.createPlaylist(playlistName);
            System.out.println("Your playlist has been created with id: " + playlist.getPlaylistId());
            System.out.println("Enter the playlist id to add songs to: ");
            int playlistId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the song ids to add to the playlist separated by comma: ");
            String songIds = scanner.nextLine();
            boolean songsAdded = playlistRepository.addSongsToPlaylist(playlistId, songIds);
            if(songsAdded) {
                System.out.println("Songs added to the playlist");
            }
            else{
                System.out.println("Something went wrong");
            }
            System.out.println("Enter the playlist id to get songs from: ");
            int playlistIdToGetSongsFrom = scanner.nextInt();
            List<Song> songsFromPlaylist = playlistRepository.getSongsFromPlaylist(playlistIdToGetSongsFrom);
            for (Song song : songsFromPlaylist) {
                System.out.println(song);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}