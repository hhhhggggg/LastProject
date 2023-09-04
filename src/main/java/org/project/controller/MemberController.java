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
//만약에 이걸 하게된다면
	private MemberService service;

	@GetMapping("/register")
	public void register() {
		log.info("�쉶�썝媛��엯 �럹�씠吏� Get");
	}

	@PostMapping("/register")
	public String register(MemberVO membervo, RedirectAttributes rttr) {
		log.info("register �떊泥�" + membervo);
		boolean rgChk;
		rgChk = service.registerIdCheck(membervo.getId());
		if (rgChk == false) {
			rttr.addFlashAttribute("result", "以묐났�맂 ID");
			log.info("以묐났�맂 ID");
			return "redirect:/join/register";
		}
		service.register(membervo);
		rttr.addFlashAttribute("result", "�쉶�썝媛��엯 �셿猷�");
		// redirect�뒗 get諛⑹떇�쑝濡� �쟾�떖
		return "redirect:/join/login";
	}

	@GetMapping("/login")
	public String login(HttpSession session) {
		log.info("濡쒓렇�씤 Get");
		String id = (String) session.getAttribute("id");
		if (id == null || id.equals("�뾾�뒗 �븘�씠�뵒 �엯�땲�떎.") || id.equals("�뙣�뒪�썙�뱶媛� �떎由낅땲�떎.") || id.equals("�쑀�� ���엯�씠 �떎由낅땲�떎.")) {// 濡쒓렇�씤 x
		    session.invalidate();
			return "/join/login";
		}
		return "redirect:/join/index";// 濡쒓렇�씤 o
	}

	@PostMapping("/login")
	public String login(MemberVO membervo, HttpSession session, RedirectAttributes rttr) {
		log.info("濡쒓렇�씤. �엯�젰�븳 鍮꾨�踰덊샇�뒗 -> " + membervo.getPw());
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