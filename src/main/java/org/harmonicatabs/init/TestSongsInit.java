package org.harmonicatabs.init;

import org.harmonicatabs.model.entity.Song;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.model.enums.MusicGenre;
import org.harmonicatabs.repository.SongRepository;
import org.harmonicatabs.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestSongsInit implements CommandLineRunner {

    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public TestSongsInit(SongRepository songRepository, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(this.songRepository.count() == 0){
            UserEntity user = this.userRepository.findByUsername("konstantin.pl").orElse(null);

            Song s1 = new Song();
            s1.setArtistName("Koce");
            s1.setGenre(MusicGenre.BLUES);
            s1.setName("Cucududulu");
            s1.setCreationDate(LocalDate.now());
            s1.setTab("3 3 -3 4 4 -5");
            s1.setUploader(user);

            this.songRepository.saveAndFlush(s1);
        }
    }
}
