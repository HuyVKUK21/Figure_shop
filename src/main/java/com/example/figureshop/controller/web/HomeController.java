package com.example.figureshop.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class HomeController {
	
	@GetMapping("/home")
	public String homePage() {
		
        return "web/home";  
    }
	
   
}
