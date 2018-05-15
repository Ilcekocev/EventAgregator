package com.sorsix.eventagregator.configuration;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class EmailSendingTask {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendingTask.class);
    private static final DateTimeFormatter dft = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final EventRepository eventRepository;
    private final EmailService emailService;

    public EmailSendingTask(EventRepository eventRepository, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 * * ? * *")
    public void reportCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        dft.format(localDateTime);
        logger.info("The time is {}", localDateTime.toString());
    }

    //using it every minute for testing purposes
    @Scheduled(cron = "0 * * ? * *")
    public void sendEmail() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextHour = now.plusHours(1);
        logger.info("Now: {}\nOne hour later: {}", now, nextHour);
        eventRepository.findAllByEmailNotificationIsTrueAndNotifiedIsFalse()
                                                            .parallelStream()
                                                            .forEach(each -> {
                                                                emailService.sendEmail(each);
                                                                each.setNotified(true);
                                                                eventRepository.save(each);
                                                            });
    }



}
