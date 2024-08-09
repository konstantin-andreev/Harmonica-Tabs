package org.harmonicatabs.schedule;

import org.harmonicatabs.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IncrementMemberDays {
    private final UserRepository userRepository;

    public IncrementMemberDays(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //@Scheduled(fixedRate = 1000)
    @Scheduled(cron = "0 0 0 * * ?")
    public void increment(){
        this.userRepository.findAll().forEach(user -> {
            user.setMemberDays(user.getMemberDays()+1);
            this.userRepository.saveAndFlush(user);
        });
    }
}
