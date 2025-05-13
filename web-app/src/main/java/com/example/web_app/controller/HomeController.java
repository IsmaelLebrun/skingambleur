package com.example.web_app.controller;

import com.example.web_app.service.CaisseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final CaisseService caisseService;

    public HomeController(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @GetMapping("/home")
    public ModelAndView homePage() {

        return new ModelAndView("home", "caisses", caisseService.getAllCaisses());
    }

    @GetMapping("/create-caisse")
    public String createCaissePage(){
        return "create-caisse";
    }

    @GetMapping("/caisse/{caisseName}")
    public ModelAndView caissePage(@PathVariable String caisseName, Model model){
        model.addAttribute("skins", caisseService.getSkinsByCaisseName(caisseName));
        return new ModelAndView("caisse", "caisse", caisseService.getCaisseByName(caisseName));
    }
}
