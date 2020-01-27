package test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import test.model.Member;
import test.model.Message;
import test.service.ChattingService;
import test.service.LoginService;


@Controller
public class ChatAppController {

	@RequestMapping(value="main", method=RequestMethod.GET)
	public String main(Model model,HttpSession session) {	
		String configLocation = "classpath:applicationContext.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(
				configLocation);
		ChattingService chattingService = ctx.getBean("chattingService", ChattingService.class );
		List<String> users = chattingService.userList((String)session.getAttribute("id"));
		ctx.close();
		model.addAttribute("user", users);
		
		return "main";
	}
	
	//통과
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
			return "login";
	}
			
	//통과
	@RequestMapping(value="login", method=RequestMethod.POST )
	public String login2(Member member, Model model ,HttpSession session) {
		String configLocation = "classpath:applicationContext.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(
				configLocation);
		LoginService loginCheckService = ctx.getBean("loginService", LoginService.class );
		if(loginCheckService.loginCheck(member)) {
			ctx.close();
			session.setAttribute("id", member.getId());
		
			return  "redirect:/main";
		}else {
			ctx.close();
			model.addAttribute("ex", "wrong");
			return "login";
			}
		}
			
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
			return "redirect:/login";
	}			
	
	
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String singUp(Member mem) {	
		return "signup";
	}
	
	//통과
	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String signUp2(Member mem,  Model model, RedirectAttributes redirectAttributes) {
		
		String configLocation = "classpath:applicationContext.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(
				configLocation);
		LoginService loginCheckService = ctx.getBean("loginService", LoginService.class );
		String status = loginCheckService.signUp(mem);
		
		ctx.close();
		if( status.equals("false") ) {
			model.addAttribute("ex", "exception");
			return "signup";
		}else {
			redirectAttributes.addAttribute("ex","success" );
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="chatroom", method=RequestMethod.GET)
	public String chatRoom(Model model, HttpServletRequest request,HttpSession session) {
		
		String configLocation = "classpath:applicationContext.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(
				configLocation);
		ChattingService chattingService = ctx.getBean("chattingService", ChattingService.class );
		
		String sender = (String)session.getAttribute("id");
		String receiver = request.getParameter("user");
		
		// 방이 없으면 0 있으면 방 번호 리턴
		int chatroom_id = chattingService.checkRoom(sender, receiver);
		
		if(chatroom_id==0) {//대화방이 없는경우
			//대화방 만들고 방번호 리턴
			int roomNum = chattingService.makeRoom(sender, receiver);
			model.addAttribute("chatroom_id",roomNum);
			
		}else {//대화방이 있는 경우
			List<Message> mesList = chattingService.takeMessage(chatroom_id);
			model.addAttribute("mesList",mesList);
			model.addAttribute("chatroom_id",chatroom_id);
			
			// 대화내용 가져오기
		}
		ctx.close();
		
		model.addAttribute("sender",sender);
		model.addAttribute("receiver",receiver);
		return "chat-ws";
	}
	
	
}
