package com.icely.myfirstproject.config;

import com.icely.myfirstproject.model.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


public class CustomDetails implements UserDetails {

    private final Teacher teacher;

    public CustomDetails(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        WHEN SINGLE BASED USER !
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(teacher.getRole().toUpperCase());
        System.out.println(teacher.getRole().toUpperCase());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getEmail();
    }
}
