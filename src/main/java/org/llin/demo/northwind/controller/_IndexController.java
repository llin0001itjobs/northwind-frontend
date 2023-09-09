package org.llin.demo.northwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class _IndexController {

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("home/main"); 
    }
    
}