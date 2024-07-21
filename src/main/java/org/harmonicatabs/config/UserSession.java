package org.harmonicatabs.config;

import org.harmonicatabs.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private long id;
    private String username;

    private boolean isAdmin;

    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.isAdmin();
    }

    public boolean isLoggedIn() {return this.id != 0;}
    public boolean isAdmin() {return this.isAdmin;}

    public String username() {return this.username;}
    public void logout(){
        this.id = 0;
        this.username = "";
    }

    public long getId() {
        return this.id;
    }

}
