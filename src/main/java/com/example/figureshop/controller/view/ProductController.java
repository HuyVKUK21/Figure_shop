package com.example.figureshop.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

	@GetMapping("/detail-product")
	public String productPage(@RequestParam Long productId) {		
        return "web/detail-product";  
    }
}
