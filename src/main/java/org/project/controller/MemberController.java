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

	private MemberService service;

	@GetMapping("/register")
	public void register() {
		log.info("회원가입 페이지 Get");
	}

	@PostMapping("/register")
	public String register(MemberVO membervo, RedirectAttributes rttr) {
		log.info("register 신청" + membervo);
		boolean rgChk;
		rgChk = service.registerIdCheck(membervo.getId());
		if (rgChk == false) {
			rttr.addFlashAttribute("result", "중복된 ID");
			log.info("중복된 ID");
			return "redirect:/join/register";
		}
		service.register(membervo);
		rttr.addFlashAttribute("result", "회원가입 완료");
		// redirect는 get방식으로 전달
		return "redirect:/join/login";
	}

	@GetMapping("/login")
	public String login(HttpSession session) {
		log.info("로그인 Get");
		String id = (String) session.getAttribute("id");
		if (id != null) {// 로그인 O
			return "redirect:/join/index";
		}
		return "/join/login";// 로그인 x
	}

	@PostMapping("/login")
	public String login(MemberVO membervo, HttpSession session) {
		log.info("로그인. 입력한 비밀번호는 -> " + membervo.getPw());
		String checkId = service.login(membervo.getId(), membervo.getPw(), membervo.getChecked());
		if (checkId==null) {
			return "redirect:/join/login";
		}
		session.setAttribute("id", checkId);
		return "redirect:/join/index";
	}

	@GetMapping("/logout")
	public String logout() {
		log.info("logout Get");
		return "/join/index";
	}
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		log.info("logout Post");
		return "/join/index";
	}

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