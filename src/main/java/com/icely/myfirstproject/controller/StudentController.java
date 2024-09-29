package com.icely.myfirstproject.controller;

import com.icely.myfirstproject.model.Student;
import com.icely.myfirstproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    public StudentService studentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/students")
    public String students(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "studentsPage";
    }

    @GetMapping("/admin/student/create")
    public String addStudentPage(Model model) {
        model.addAttribute("student", new Student());
        return "addStudentPage";
    }

    @PostMapping("/admin/student/create")
    public String addStudent(@ModelAttribute("student") Student student, Model model) {
        try{
            studentService.addStudent(student);
            return "redirect:/admin/students";
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error",e.getMessage());
            return "addStudentPage";
        }
    }

    @GetMapping("/admin/student/edit/{id}")
    public String editStudentPage(Model model, @PathVariable int id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "editStudentPage";
    }

    //editing students
    @PostMapping("/admin/student/edit/{id}")
    public String editStudent(@ModelAttribute("student") Student student, @PathVariable int id, Model model) {
        try {
            Student student1 = studentService.getStudentById(id);
            if (student1 != null) {
                student1.setName(student.getName());
                student1.setAddress(student.getAddress());
                student1.setEmail(student.getEmail());
                student1.setNumber(student.getNumber());

                studentService.editStudent(student1);
                return "redirect:/admin/students";
            }
            model.addAttribute("error", "Student not found");
            return "editStudentPage";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "editStudentPage";
        }
    }

    @GetMapping("/admin/student/delete/{id}")
    public String deleteStudentPage(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "redirect:/admin/students";
    }

    @GetMapping("/StudentPage")
    public String viewStudentPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "viewStudentPage";
    }

}
