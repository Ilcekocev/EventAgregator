package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.service.EmailService;
import com.sorsix.eventagregator.utils.EmailType;
import com.sorsix.eventagregator.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    private final EventRepository eventRepository;

    public EmailServiceImpl(JavaMailSender javaMailSender, EventRepository eventRepository) {
        this.javaMailSender = javaMailSender;
        this.eventRepository = eventRepository;
    }

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        logger.info("Trying to send email with message {}", mailMessage);
        javaMailSender.send(mailMessage);
        logger.info("Email has been sent");
    }

    @Override
    @Async
    public CompletableFuture<SimpleMailMessage> createEmail(Event event, EmailType emailType) {
        String subject = StringUtils.createEmailSubject(event, emailType);
        String body = StringUtils.createEmailBody(event, emailType);
        String to = event.getUser().getId();
        updateEvent(event);
        return CompletableFuture.completedFuture(setupEmailProperties(to, subject, body));
    }

    private SimpleMailMessage setupEmailProperties(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(StringUtils.SENDER_EMAIL);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        return simpleMailMessage;
    }

    private void updateEvent(Event event) {
        event.setNotified(true);
        eventRepository.save(event);
    }

}
