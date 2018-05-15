package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Future;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendEmail(Event event) {
       SimpleMailMessage simpleMailMessage = createEmail(event);
       logger.info("Trying to send email for event {}", event);
       javaMailSender.send(simpleMailMessage);
       logger.info("Email has been sent");
    }

    @Override
    public SimpleMailMessage createEmail(Event event) {
        String subject = String.format("Reminder about your event %s", event.getTitle());
        Long numberOfMinutes = calculateDifference(event.getStartTime());
        String emailText = String.format("We would like to remind you that your event %s, with description %s start in %d minutes",
                                                                                                                    event.getTitle(),
                                                                                                                    event.getDescription(),
                                                                                                                    numberOfMinutes);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("sorsixeventaggregator@gmail.com");
        email.setTo(event.getUser().getId());
        email.setSubject(subject);
        email.setText(emailText);
        return email;
    }

    private Long calculateDifference(LocalDateTime then) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), then);
    }
}
