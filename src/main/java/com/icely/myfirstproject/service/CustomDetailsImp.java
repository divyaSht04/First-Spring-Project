package com.icely.myfirstproject.service;

import com.icely.myfirstproject.config.CustomDetails;
import com.icely.myfirstproject.model.Teacher;
import com.icely.myfirstproject.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsImp implements UserDetailsService
{
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByEmail(email);
        if (teacher == null) {
            throw new UsernameNotFoundException(email + " not found");
        }
        return new CustomDetails(teacher);
    }
}
