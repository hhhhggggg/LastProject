package org.project.controller;

import org.project.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/page/*")
@AllArgsConstructor
public class PageController {
	
	
	@GetMapping("/calendar")
	public void calendar() {
		log.info("calendar Get");
	}
}
