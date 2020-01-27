package test.webconfig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import test.model.Message;
import test.service.ChattingService;

@Repository
@RestController
public class GreetingController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/message")
    public void handleSubscribe(Message message,@Header("name") String name,
    		@Headers Map<String, Object> headers,
    		@Header("simpSessionId") String sessionId) {
    	System.out.println(headers);
    	System.out.println(message.getMessage_content());
    	System.out.println(message.getMessage_sender());
    	System.out.println(message.getMessage_receiver());
    	
    	
    	  String configLocation = "classpath:applicationContext.xml";
  		 AbstractApplicationContext ctx = new GenericXmlApplicationContext(
  				configLocation);
  		 ChattingService chattingService = ctx.getBean("chattingService", ChattingService.class );
  		chattingService.insertMessage(message);
          
          ctx.close();
    	
    	 this.simpMessagingTemplate.convertAndSend("/queue/message-"+name,message);
    }

}
