package org.harmonicatabs.service;

import org.harmonicatabs.model.dtos.AddSongDTO;
import org.springframework.stereotype.Service;

public interface SongService {
    void deleteSongWithId(long id);

    void add(AddSongDTO addSongDTO);
}
