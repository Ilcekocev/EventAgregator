package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.service.EmailService;
import com.sorsix.eventagregator.utils.EmailType;
import com.sorsix.eventagregator.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(Event event, EmailType emailType) {
        SimpleMailMessage simpleMailMessage = createEmail(event, emailType);
        logger.info("Trying to send email for event {}", event);
        javaMailSender.send(simpleMailMessage);
        logger.info("Email has been sent");
    }

    @Override
    public SimpleMailMessage createEmail(Event event, EmailType emailType) {
        String subject = StringUtils.createEmailSubject(event, emailType);
        String emailBody = StringUtils.createEmailBody(event, emailType);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(StringUtils.SENDER_EMAIL);
        String receivedEmail = event.getUser().getId();
        email.setTo(receivedEmail);
        email.setSubject(subject);
        email.setText(emailBody);
        return email;
    }

}
