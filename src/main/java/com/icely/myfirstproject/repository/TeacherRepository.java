package com.icely.myfirstproject.repository;

import com.icely.myfirstproject.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

   public Teacher findByEmail(String email);
}
