package org.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.project.domain.MemberVO;
import org.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/join/*")
public class MemberController {
	@Autowired
	private MemberService service;

	@GetMapping("/register")
	public void register() {
		log.info("register Get");
	}
	//업로드로 가는 메소드
	@GetMapping("/upload")
	public void form() {}
	
	@PostMapping("/upload")
	public String upload(MemberVO membervo,@RequestParam("file") MultipartFile file,RedirectAttributes rttr) {
		String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
		long size = file.getSize(); //파일 사이즈
		
		System.out.println("파일명 : "  + fileRealName);
		System.out.println("용량크기(byte) : " + size);
		//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
		String uploadFolder = "D:\\fileUpload";
		
		// 폴더가 없는 경우 폴더를 생성합니다.
	    File folder = new File(uploadFolder);
	    if (!folder.exists()) {
	        folder.mkdirs(); // 폴더 및 하위 폴더까지 모두 생성
	    }
		
	    //컴퓨터는 동일한 파일 저장은 못해서 uuid를 통해서 랜덤으로 받아야 한다고 함
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자열" + uniqueName);
		System.out.println("확장자명" + fileExtension);
		
		
		
		// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
		
		File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
		try {
			file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//여기부터 사업자 회원가입 하는 기능 사실 밑에랑 똑같아서 뭔가 함수로 만들면 좋지 않을까 싶긴한데 어케할지 모르겟음
		log.info("사업자 register ->" + membervo);
		boolean IDChk;
		boolean EChk;
		IDChk = service.registerIdCheck(membervo.getId());
		EChk = service.registerEmailCheck(membervo.getEmail());
		if (IDChk == false) {
			rttr.addFlashAttribute("result", "중복된 ID");
			return "redirect:/join/register";
		}
		if (EChk == false) {
			rttr.addFlashAttribute("result", "중복된 EMAIL");
			return "redirect:/join/register";
		}

		service.register2(membervo);
		///////////////////////////////여기서는 회원가입을 눌러도 관리자 승인하에 회원가입이 되는 거라 그냥 메인으로 보냄
		rttr.addFlashAttribute("result", "관리자의 승인을 기다리세요");
		return "redirect:/join/main";
	}
	
	@PostMapping("/register")
	public String register(MemberVO membervo, RedirectAttributes rttr) {
		log.info("register ->" + membervo);
		boolean IDChk;
		boolean EChk;
		IDChk = service.registerIdCheck(membervo.getId());
		EChk = service.registerEmailCheck(membervo.getEmail());
		if (IDChk == false) {
			rttr.addFlashAttribute("result", "중복된 ID");
			return "redirect:/join/register";
		}
		else if (EChk == false) {
			rttr.addFlashAttribute("result", "중복된 EMAIL");
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
		if (id == null || id.equals("승인이 되지 않은 사용자 입니다.")|| id.equals("없는 아이디 입니다.") || id.equals("패스워드가 다릅니다.") || id.equals("유저 타입이 다릅니다.")) {// 로그인 x
		    session.invalidate();
			return "/join/login";
		}
		return "redirect:/join/main";// 로그인 o
	}

	@PostMapping("/login")
	public String login(MemberVO membervo, HttpSession session, RedirectAttributes rttr) {
		log.info("login post, Id -> " + membervo.getId());
		String checkId = service.login(membervo.getId(), membervo.getPw(), membervo.getChecked());
		if (checkId != null && checkId.equals(membervo.getId())) {
		    session.setAttribute("id", checkId);
		    log.info(checkId + "->main");
		    return "redirect:/join/main";
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
	    return "/join/main";
	}

	@GetMapping("/main")
	public void main(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		log.info("main Get");
		if (id != null) {
			MemberVO membervo = service.getUserInfo(id);
			model.addAttribute("user", membervo);
		}
	}
	@GetMapping("/id_find")
	public void findId() {
		log.info("id_find Get");
	}
	
	@PostMapping("/id_find")
	public String findId(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("checked") int checked,
			@RequestParam("phone") String phone, Model model) {
		// MemberService를 호출하여 아이디 찾기 로직 수행
        String foundId = service.findId(name, email, phone, checked);

        if (foundId != null) {
            model.addAttribute("message", "아이디는 " + foundId + " 입니다.");
            model.addAttribute("foundId", foundId);
        } else {
            model.addAttribute("message", "일치하는 아이디를 찾을 수 없습니다.");
        }

        return "/join/id_find_result"; // 결과를 표시할 JSP 파일의 이름 반환
    }
	
	@GetMapping("/pw_find")
	public void findPw() {
		log.info("pw_find Get");
	}

	
	@PostMapping("/pw_find")
	public String findPw(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("checked") int checked,
			@RequestParam("phone") String phone, Model model) {
		// MemberService를 호출하여 아이디 찾기 로직 수행
        String foundPw = service.findPw(id, name, email, phone, checked);

        if (foundPw != null) {
            model.addAttribute("message", "비밀번호는 " + foundPw + " 입니다.");
            model.addAttribute("foundPw", foundPw);
        } else {
            model.addAttribute("message", "일치하는 비밀번호를 찾을 수 없습니다.");
        }

        return "/join/pw_find_result"; // 결과를 표시할 JSP 파일의 이름 반환
    }
//	@GetMapping("/pw_find_result")
//	public void findPw_result() {
//		log.info("pw_find_result Get");
//	}

}