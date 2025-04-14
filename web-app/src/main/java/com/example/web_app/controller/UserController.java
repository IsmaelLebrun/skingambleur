package com.example.web_app.controller;

import com.example.web_app.model.User;
import com.example.web_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Assurez-vous d'avoir une page `login.html` dans `src/main/resources/templates`
    }

    @GetMapping("signup")
    public ModelAndView signupPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("signup", "error", error);
    }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("user") User user, @RequestBody String confirmPassword) {
        if (!user.getPassword().equals(confirmPassword)) {
            user.setCreatedAt(new Date());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            String response = userService.createUser(user);
            return switch (response) {
                case "success" -> "redirect:/login";
                case "pseudoAlreadyTaken" -> "redirect:/signup?error=pseudoAlreadyTaken";
                case "mailAlreadyExist" -> "redirect:/signup?error=mailAlreadyExist";
                case "errorCreatingUserM" -> "redirect:/signup?error=errorCreatingUserM";
                default -> "redirect:/login";
            };
        }else{
            return "redirect:/signup?error=passwordNotMatch";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
