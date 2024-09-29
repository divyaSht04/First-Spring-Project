package com.icely.myfirstproject.controller;

import com.icely.myfirstproject.model.Teacher;
import com.icely.myfirstproject.service.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping("/login")
    public String login() {

        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("teacher") Teacher teacher,  Model model) {
        boolean hasErrors = false;

        if (teacher.getFirstName() == null || teacher.getFirstName().isEmpty()) {
            model.addAttribute("firstNameError", "First Name is required.");
            hasErrors = true;
        }

        if (teacher.getLastName() == null || teacher.getLastName().isEmpty()) {
            model.addAttribute("lastNameError", "Last Name is required.");
            hasErrors = true;

        }

        if (teacher.getEmail() == null || teacher.getEmail().isEmpty()) {
            model.addAttribute("emailError", "Email is required.");
            hasErrors = true;

        }

        if (teacher.getPhoneNumber() == null || teacher.getPhoneNumber().isEmpty()) {
            model.addAttribute("phoneNumberError", "Phone Number is required.");
            hasErrors = true;

        }

        if (teacher.getPassword() == null || teacher.getPassword().isEmpty()) {
            model.addAttribute("passwordError", "Password is required.");
            hasErrors = true;

        } else if (teacher.getPassword().length() < 6) {
            model.addAttribute("passwordError", "Password must be at least 6 characters.");
            hasErrors = true;

        }

        if (teacher.getConfirmPassword() == null || teacher.getConfirmPassword().isEmpty()) {
            model.addAttribute("confirmPasswordError", "Confirm Password is required.");
            hasErrors = true;

        } else if (!teacher.getPassword().equals(teacher.getConfirmPassword())) {
            model.addAttribute("confirmPasswordError", "Passwords do not match.");
            hasErrors = true;

        }
        Teacher checkExistingTeacher = teacherServices.findTeacherByEmail(teacher.getEmail());
        if (checkExistingTeacher != null) {
            model.addAttribute("emailError", "This email already exists.");
            hasErrors = true;
        }

        Teacher checkExistingNumber = teacherServices.findTeacherByEmail(teacher.getPhoneNumber());
        if (checkExistingNumber != null) {
            model.addAttribute("phoneNumberError", "This phoneNumber already exists.");
            hasErrors = true;
        }

        if (hasErrors) {
            return "register";
        }


        teacher.setRole("Normal");
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherServices.saveTeacher(teacher);

        return "redirect:/login";
    }
}
