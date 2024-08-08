package org.harmonicatabs.init;

import org.harmonicatabs.model.entity.Role;
import org.harmonicatabs.model.enums.RoleEnum;
import org.harmonicatabs.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RolesInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.roleRepository.count() == 0){
            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);
            Role user = new Role();
            user.setName(RoleEnum.USER);
            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(user);
        }
    }
}
