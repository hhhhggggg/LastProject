package org.project.controller;

import org.project.define.define;
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
        log.info("추가" + member);
        boolean rgChk;
        rgChk = service.registerIdCheck(member.getId());
        if (rgChk==false) {
            rttr.addFlashAttribute("msg", define.DUPLICATE_ID_MESSAGE);
            // 중복 아이디인 경우 회원가입 페이지로 다시 이동
            return "redirect:/join/register";
        }
        service.register(member);
        rttr.addFlashAttribute("msg", define.REGISTER_SUCCESS);
        //redirect는 get방식으로 전달
        return "redirect:/join/login";
    }

    @GetMapping("/login")
    public void login() {
        log.info("로그인 요청 받음");
    }

    @PostMapping("/login")
    public void login(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
        log.info("로그인. 입력한 비밀번호는 -> " + pw);

        String check_pw = service.login(id);

        log.info("DB에 있는 비밀번호 -> " + check_pw);
        model.addAttribute("id", id);
    }

    @GetMapping("/index")
    public void index() {
    }
}