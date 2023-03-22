package com.apppassarourbano.AppPassaroUrbano.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter @Setter
public class CustomUser extends User {
    private Long id;

    private com.apppassarourbano.AppPassaroUrbano.model.entity.User user;
    public CustomUser(com.apppassarourbano.AppPassaroUrbano.model.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.id = user.getId();
        this.user = user;
    }
}
