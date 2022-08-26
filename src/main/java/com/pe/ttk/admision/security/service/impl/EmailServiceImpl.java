package com.pe.ttk.admision.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.pe.ttk.admision.security.dto.EmailValuesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Value("${mail.urlFront}")
	private String urlFront;

	
	
	public void sendMailTemplate(EmailValuesDto emailValuesDto) {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			Context context = new Context();
			Map<String,Object> model = new HashMap<>();
			model.put("userName", emailValuesDto.getUserName());
			model.put("url", urlFront + emailValuesDto.getToken() );
			context.setVariables(model);
			String htmlText = templateEngine.process("email-template", context);
			helper.setFrom(emailValuesDto.getMailFrom());
			helper.setTo(emailValuesDto.getMailTo());
			helper.setSubject(emailValuesDto.getSubject());
			helper.setText(htmlText,true);
			javaMailSender.send(message);
			
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	

}
