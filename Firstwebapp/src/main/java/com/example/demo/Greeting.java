package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Greeting {
    @GetMapping("/greet")
    @ResponseBody
	public String goat() {
		return "Hello world";
	}
    @GetMapping("/well")
    @ResponseBody
    public String meeting() {
    	return "Hi buddy";
    }
}
