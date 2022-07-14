package com.namanok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	/**
	 * Index Page
	 * 
	 * @return
	 */
	@GetMapping(value="/index")
	public String index() {
		return "/index";
	}
	
}
