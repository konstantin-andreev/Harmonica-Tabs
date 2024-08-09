package org.harmonicatabs.init;

import org.harmonicatabs.model.entity.Harmonica;
import org.harmonicatabs.model.entity.UserEntity;
import org.harmonicatabs.model.enums.HarmonicaManufacturer;
import org.harmonicatabs.model.enums.HarmonicaType;
import org.harmonicatabs.repository.HarmonicaRepository;
import org.harmonicatabs.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class TestHarmonicasInit implements CommandLineRunner {

    private final HarmonicaRepository harmonicaRepository;
    private final UserRepository userRepository;

    public TestHarmonicasInit(HarmonicaRepository harmonicaRepository, UserRepository userRepository) {
        this.harmonicaRepository = harmonicaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.harmonicaRepository.count() == 0){
            UserEntity owner = this.userRepository.findByUsername("konstantin.pl").orElse(null);

            Harmonica h1 = new Harmonica();
            h1.setName("Marine Band");
            h1.setHarp_key("C");
            h1.setManufacturer(HarmonicaManufacturer.HOHNER);
            h1.setType(HarmonicaType.DIATONIC);
            h1.setAge(1);
            h1.setOwner(owner);

            Harmonica h2 = new Harmonica();
            h2.setName("Marine Band");
            h2.setHarp_key("A");
            h2.setManufacturer(HarmonicaManufacturer.HOHNER);
            h2.setType(HarmonicaType.DIATONIC);
            h2.setAge(1);
            h2.setOwner(owner);

            this.harmonicaRepository.saveAndFlush(h1);
            this.harmonicaRepository.saveAndFlush(h2);
        }

    }
}
