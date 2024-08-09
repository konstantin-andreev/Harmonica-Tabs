package org.harmonicatabs.service.impl;

import org.harmonicatabs.model.dtos.AddSongDTO;
import org.harmonicatabs.model.entity.Song;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.repository.SongRepository;
import org.harmonicatabs.service.SongService;
import org.harmonicatabs.service.session.UserHelperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final UserHelperService userHelperService;
    private final ModelMapper modelMapper;

    public SongServiceImpl(SongRepository songRepository, UserHelperService userHelperService, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.userHelperService = userHelperService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteSongWithId(long id) {
        Optional<Song> song = this.songRepository.findById(id);

        if(song.isEmpty()) return;

        UserEntity uploader = song.get().getUploader();

        if(this.userHelperService.getUser().getUsername().equals(uploader.getUsername())){
            uploader.getSongs().remove(song.get());
            this.songRepository.delete(song.get());
        }
    }

    @Override
    public void add(AddSongDTO addSongDTO) {
        UserEntity uploader = this.userHelperService.getUser();
        Song song = this.modelMapper.map(addSongDTO, Song.class);
        song.setUploader(uploader);
        uploader.getSongs().add(song);
        this.songRepository.saveAndFlush(song);
    }

    @Override
    public Pair<Boolean, Song> getSong(long id) {
        Optional<Song> song = this.songRepository.findById(id);
        return song.map(value -> Pair.of(true, value)).orElseGet(() -> Pair.of(false, null));
    }

    @Override
    public List<Song> getAllSongs() {
        return this.songRepository.findAll();
    }
}
