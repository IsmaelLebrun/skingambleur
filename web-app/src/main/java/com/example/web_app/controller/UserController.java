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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    private final UserService userService;

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

        // TODO : laisser commenter le temps de la phase de test
//        if(user.getPseudo().isEmpty() || user.getPseudo().length() <= 3){
//            return "redirect:/signup?error=pseudoNotValid";
//        }
//        if(!emailValidation(user.getEmail())){
//            return "redirect:/signup?error=emailNotValid";
//        }
//        if(!passwordValidation(user.getPassword())){
//            return "redirect:/signup?error=passwordNotValid";
//        }

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

    private boolean passwordValidation(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean emailValidation(String email) {
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
