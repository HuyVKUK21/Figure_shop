package com.example.figureshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.figureshop.service.IMailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService implements IMailService {

	@Autowired
	private JavaMailSender mailSender;


	@Override
	public void sendResetPasswordMail(String toEmail, String resetLink) {
		 try {
	            MimeMessage message = mailSender.createMimeMessage();

	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	            helper.setTo(toEmail);
	            helper.setSubject("Yêu cầu đặt lại mật khẩu");	 

	            String htmlContent = "<p>Xin chào,</p>"
	                    + "<p>Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấn vào liên kết bên dưới:</p>"
	                    + "<p><a href=\"" + resetLink + "\">Đặt lại mật khẩu</a></p>"
	                    + "<br>"
	                    + "<p>Nếu bạn không yêu cầu, hãy bỏ qua email này.</p>";

	            helper.setText(htmlContent, true); 
	            helper.setFrom(toEmail);

	            mailSender.send(message);
	        } catch (MessagingException e) {
	            throw new RuntimeException("Lỗi khi gửi email đặt lại mật khẩu", e);
	        }
	    }
	}


