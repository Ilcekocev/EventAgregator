package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.utils.EmailType;
import org.springframework.mail.SimpleMailMessage;

import java.util.concurrent.Future;

public interface EmailService {

    void sendEmail(Event event, EmailType emailType);

    SimpleMailMessage createEmail(Event event, EmailType emailType);
}
