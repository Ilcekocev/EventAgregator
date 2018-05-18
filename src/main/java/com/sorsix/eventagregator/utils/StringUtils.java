package com.sorsix.eventagregator.utils;

import com.sorsix.eventagregator.model.Event;

public class StringUtils {

    private static final String REMINDER_SUBJECT = "Reminder about your event ";
    private static final String REMINDER_EVENT = "We would like to remind you that your event ";
    private static final String REMINDER_TIME = ", will start in ";
    private static final String INVITE_SUBJECT = "You have been invited on ";
    private static final String INVITE_TIME = "This event will start at ";
    private static final String INVITE_CLOSING = ", you will receive an email prior the starting time";

    // should be extracted from application.properties
    public static final String SENDER_EMAIL = "sorsixeventaggregator@gmail.com";

    public static String createEmailBody(Event event, EmailType emailType) {
        if (emailType == EmailType.REMIND) {
            return createReminderBody(event);
        }
        return createOnInviteBody(event);
    }

    public static String createEmailSubject(Event event, EmailType emailType) {
        if (emailType == EmailType.INVITE) {
            return createReminderSubject(event);
        }
        return creteOnInviteSubject(event);
    }

    private static String createReminderSubject(Event event) {
        return REMINDER_SUBJECT + event.getTitle();
    }

    private static String createReminderBody(Event event) {
        Long numberOfMinutes = DateTimeUtils.calculateDifference(event.getStartTime());
        return String.format("%s%s%s%d", REMINDER_EVENT, event.getTitle(), REMINDER_TIME, numberOfMinutes);
    }

    private static String creteOnInviteSubject(Event event) {
        String creatorName = event.getUser().getUserDetails().getName();
        String eventTitle = event.getTitle();
        return String.format("%s%s's event: %s", INVITE_SUBJECT, creatorName, eventTitle);
    }

    private static String createOnInviteBody(Event event) {
        String time = event.getStartTime().toString();
        return String.format("%s%s%s", INVITE_TIME, time, INVITE_CLOSING);
    }

}
