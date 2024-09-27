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
public class TeacherServices {

    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher findTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public Teacher findTeacherByNumber(String number) {
        return teacherRepository.findByPhoneNumber(number);
    }
}
