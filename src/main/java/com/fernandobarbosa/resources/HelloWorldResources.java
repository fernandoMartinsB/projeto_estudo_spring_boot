package com.fernandobarbosa.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/helloworld")
public class HelloWorldResources {
	
	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}

}
