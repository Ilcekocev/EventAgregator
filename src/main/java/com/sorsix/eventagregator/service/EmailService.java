package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.Event;
import org.springframework.mail.SimpleMailMessage;

import java.util.concurrent.Future;

public interface EmailService {

    void sendEmail(Event event);

    SimpleMailMessage createEmail(Event event);
}
