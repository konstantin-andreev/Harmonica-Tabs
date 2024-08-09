package org.harmonicatabs.service;

import org.modelmapper.internal.Pair;
import org.harmonicatabs.model.dtos.AddSongDTO;
import org.harmonicatabs.model.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SongService {
    void deleteSongWithId(long id);

    void add(AddSongDTO addSongDTO);

    Pair<Boolean, Song> getSong(long id);

    List<Song> getAllSongs();
}
