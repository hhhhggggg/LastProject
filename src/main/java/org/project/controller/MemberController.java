package org.project.controller;

import javax.servlet.http.HttpSession;

import org.project.domain.MemberVO;
import org.project.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/join/*")
@AllArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/register")
    public void register() {
        log.info("회원가입 페이지 Get");
    }

    @PostMapping("/register")
    public String register(MemberVO member, RedirectAttributes rttr) {
        log.info("register 신청" + member);
        boolean rgChk;
        rgChk = service.registerIdCheck(member.getId());
        if (rgChk==false) {
            rttr.addFlashAttribute("result","중복된 ID");
        }else {
        	service.register(member);
        	rttr.addFlashAttribute("result","회원가입 완료");
        }
        //redirect는 get방식으로 전달
        return "redirect:/join/index";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        log.info("로그인 요청 받음");
        String id = (String)session.getAttribute("id");
        if(id != null) {//로그인 O
        	return "redirect:/join/index";
        }
        return "/join/login";//로그인 x
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
        log.info("로그인. 입력한 비밀번호는 -> " + pw);
        String checkId = service.login(id, pw);
        if (checkId == null) {
        	return "redirect:/join/login";
        }
        session.setAttribute("id", checkId);
		return "redirect:/join/index";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	log.info("logout");
    	return "redirect:/join/index";
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
    	String id = (String) session.getAttribute("id");
    	if(id!=null) {
    		MemberVO membervo = service.getUser(id);
    		model.addAttribute("user",membervo);
    		return "/join/index";
    	}
    	return "redirect:/join/login";
    }
}