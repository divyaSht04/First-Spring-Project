package com.icely.myfirstproject.repository;

import com.icely.myfirstproject.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

   public Teacher findByEmail(String email);

   @Query("SELECT t FROM Teacher t WHERE t.phoneNumber = :phone")
   public Teacher findByPhoneNumber(@Param("phone") String number);

}
