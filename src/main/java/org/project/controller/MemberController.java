package org.project.controller;

import javax.servlet.http.HttpSession;

import org.project.domain.MemberVO;
import org.project.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/join/*")
@AllArgsConstructor
public class MemberController {
	//이석남
	private MemberService service;

	@GetMapping("/register")
	public void register() {
		log.info("register Get");
	}

	@PostMapping("/register")
	public String register(MemberVO membervo, RedirectAttributes rttr) {
		log.info("register ->" + membervo);
		boolean rgChk;
		rgChk = service.registerIdCheck(membervo.getId());
		if (rgChk == false) {
			rttr.addFlashAttribute("result", "중복된 ID");
			return "redirect:/join/register";
		}
		service.register(membervo);
		rttr.addFlashAttribute("result", "회원가입 완료");
		// redirect login
		return "redirect:/join/login";
	}

	@GetMapping("/login")
	public String login(HttpSession session) {
		log.info("login Get");
		String id = (String) session.getAttribute("id");
		if (id == null || id.equals("없는 아이디 입니다.") || id.equals("패스워드가 다릅니다.") || id.equals("유저 타입이 다릅니다.")) {// 로그인 x
		    session.invalidate();
			return "/join/login";
		}
		return "redirect:/join/index";// 로그인 o
	}

	@PostMapping("/login")
	public String login(MemberVO membervo, HttpSession session, RedirectAttributes rttr) {
		log.info("login post, pw -> " + membervo.getPw());
		String checkId = service.login(membervo.getId(), membervo.getPw(), membervo.getChecked());
	
		if (checkId != null && checkId.equals(membervo.getId())) {
		    session.setAttribute("id", checkId);
		    log.info(checkId + "->index");
		    return "redirect:/join/index";
		}
		rttr.addFlashAttribute("result", checkId);
		log.info(checkId+"->login");
		return "redirect:/join/login";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		log.info("logout");
	    session.invalidate();
	    log.info("logout");
	    return "/join/index";
	}

	// 헤헤헤헿하핳하하하하
	@GetMapping("/index")
	public void index(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		log.info("index Get");
		if (id != null) {
			MemberVO membervo = service.getUserInfo(id);
			model.addAttribute("user", membervo);
		}
	}
}