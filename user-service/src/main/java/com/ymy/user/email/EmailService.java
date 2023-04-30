package com.ymy.user.email;

import com.ymy.user.utility.Constant;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Async
    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, Constant.ENCODING_UTF8);
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("eruyar123@gmail.com");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

}
