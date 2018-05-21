package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.configuration.OnEventInvite;
import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.utils.EmailType;
import org.springframework.mail.SimpleMailMessage;

import java.util.concurrent.CompletableFuture;

public interface EmailService {

    void sendEmail(SimpleMailMessage mailMessage);

    CompletableFuture<SimpleMailMessage> createEmail(Event event, EmailType emailType);

    void onInviteApplicationEvent(OnEventInvite applicationEvent);
}
