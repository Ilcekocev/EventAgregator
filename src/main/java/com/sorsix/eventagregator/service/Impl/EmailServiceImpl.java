package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.configuration.OnEventInvite;
import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.repository.InvitationRepository;
import com.sorsix.eventagregator.service.EmailService;
import com.sorsix.eventagregator.utils.EmailStringUtils;
import com.sorsix.eventagregator.utils.EmailType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
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
    private final InvitationRepository invitationRepository;

    public EmailServiceImpl(JavaMailSender javaMailSender, EventRepository eventRepository, InvitationRepository invitationRepository) {
        this.javaMailSender = javaMailSender;
        this.eventRepository = eventRepository;
        this.invitationRepository = invitationRepository;
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
        String subject = EmailStringUtils.createEmailSubject(event, emailType);
        String body = EmailStringUtils.createEmailBody(event, emailType);
        String to = event.getUser().getId();
        if (emailType == EmailType.REMIND) {
            updateEvent(event);
        } else if (emailType == EmailType.REMIND_INVITED) {
            updateInvitation(event);
        }
        return CompletableFuture.completedFuture(setupEmailProperties(to, subject, body));
    }

    private SimpleMailMessage setupEmailProperties(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(EmailStringUtils.SENDER_EMAIL);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        return simpleMailMessage;
    }


    @EventListener
    public void onInviteApplicationEvent(OnEventInvite applicationEvent) {
        logger.info("Sending email for invitation");
        Event event = applicationEvent.getEvent();
        createEmail(event, EmailType.INVITE)
                .whenComplete((result, throwable) -> {
                    sendEmail(result);
                    logger.info("Finished sending email for invitation");
                });
    }

    private void updateEvent(Event event) {
        event.setNotified(true);
        eventRepository.save(event);
    }

    private void updateInvitation(Event event) {
        invitationRepository.findByEvent(event)
                .map(invitation -> {
                    invitation.setNotified(true);
                    return invitationRepository.save(invitation);
                });
    }

}
