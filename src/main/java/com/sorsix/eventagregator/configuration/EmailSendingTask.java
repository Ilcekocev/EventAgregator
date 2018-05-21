package com.sorsix.eventagregator.configuration;

import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.repository.InvitationRepository;
import com.sorsix.eventagregator.service.EmailService;
import com.sorsix.eventagregator.utils.EmailType;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class EmailSendingTask {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendingTask.class);

    private final InvitationRepository invitationRepository;
    private final EventRepository eventRepository;
    private final EmailService emailService;

    public EmailSendingTask(InvitationRepository invitationRepository, EventRepository eventRepository, EmailService emailService) {
        this.invitationRepository = invitationRepository;
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    //using it every minute for testing purposes
    @Scheduled(cron = "0 * * ? * *")
    public void sendEmails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextHour = now.plusHours(1);
        logger.info("Now: {}, one hour later: {}", now, nextHour);
        //replace with findAllByEmailNotificationIsTrueAndNotifiedIsFalseAndStartTimeIsBetween(LocalDateTime before, LocalDateTime then);
        logger.info("Reminding every user about their upcoming event/s");
        eventRepository.findAllByEmailNotificationIsTrueAndNotifiedIsFalse()
                .parallelStream()
                .filter(event -> EmailValidator.getInstance().isValid(event.getUser().getId()))
                .map(event -> emailService.createEmail(event, EmailType.REMIND))
                .map(CompletableFuture::join)
                .forEach(emailService::sendEmail);
        logger.info("Reminding every invited user to to their new event");
        invitationRepository.findAllByNotifiedIsFalse()
                .parallelStream()
                .map(invitation -> emailService.createEmail(invitation.getEvent(), EmailType.REMIND_INVITED))
                .map(CompletableFuture::join)
                .forEach(emailService::sendEmail);
    }

}
