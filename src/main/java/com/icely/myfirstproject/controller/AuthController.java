package com.icely.myfirstproject.controller;

import com.icely.myfirstproject.model.Teacher;
import com.icely.myfirstproject.service.TeacherServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {

        String loginError = (String) request.getAttribute("loginError");
        if (loginError!= null) {
            model.addAttribute("loginError", "Invalid username or password.");
        }

        String logoutMessage = (String) request.getAttribute("logoutSuccess");
        if (logoutMessage!= null) {
            model.addAttribute("logoutSuccess", "You have been logged out.");
        }

        return "login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("teacher") Teacher teacher, Model model, BindingResult result) {

        if(result.hasErrors()) {
            return "register";
        }

        if (teacherServices.findTeacherByEmail(teacher.getEmail()) != null) {
            model.addAttribute("emailError", "Email already exists.");
            return "register";
        };

        if (teacherServices.findTeacherByNumber(teacher.getPhoneNumber()) != null) {
            model.addAttribute("phoneNumberError", "Number already exists.");
            return "register";
        };

        teacher.setRole("NORMAL");
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherServices.saveTeacher(teacher);

        return "redirect:/login";
    }
}
