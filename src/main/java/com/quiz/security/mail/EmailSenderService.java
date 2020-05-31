package com.quiz.security.mail;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.quiz.models.Email;

import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

	private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(
    		SpringTemplateEngine templateEngine,
    		JavaMailSender javaMailSender
    		) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(Email email) throws MessagingException {
    	MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getProps());
        
        String html = templateEngine.process("mail", context);

        helper.setTo(email.getEmail());
        helper.setText(html, true);
        helper.setSubject(email.getSubject());

        javaMailSender.send(message);
    }

}