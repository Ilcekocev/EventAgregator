package com.sorsix.eventagregator.utils;

import com.sorsix.eventagregator.model.Event;

import static com.sorsix.eventagregator.utils.EmailType.INVITE;

public class EmailStringUtils {

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
        } else if (emailType == INVITE) {
            return createOnInviteBody(event);
        } else {
            return createInvitedRemindBody(event);
        }
    }

    public static String createEmailSubject(Event event, EmailType emailType) {
        if (emailType == EmailType.REMIND) {
            return createReminderSubject(event);
        } else if (emailType == INVITE) {
            return createOnInviteSubject(event);
        } else {
            return createdInviteRemindSubject(event);
        }
    }

    private static String createReminderSubject(Event event) {
        return REMINDER_SUBJECT + event.getTitle();
    }

    private static String createReminderBody(Event event) {
        Long numberOfMinutes = DateTimeUtils.calculateDifference(event.getStartTime());
        return String.format("%s%s%s%d minutes", REMINDER_EVENT, event.getTitle(), REMINDER_TIME, numberOfMinutes);
    }

    private static String createOnInviteSubject(Event event) {
        String creatorName = creatorName(event);
        String eventTitle = eventTitle(event);
        return String.format("%s%s's event: %s", INVITE_SUBJECT, creatorName, eventTitle);
    }

    private static String createOnInviteBody(Event event) {
        String time = event.getStartTime().toString();
        return String.format("%s%s%s", INVITE_TIME, time, INVITE_CLOSING);
    }

    private static String createInvitedRemindBody(Event event) {
        String creatorName = creatorName(event);
        String eventTitle = eventTitle(event);
        Long numberOfMinutes = DateTimeUtils.calculateDifference(event.getStartTime());
        return String.format("%s's event: %s, starts in %d", creatorName, eventTitle, numberOfMinutes);
    }


    private static String createdInviteRemindSubject(Event event) {
        return String.format("Reminder about your invitation for the event %s", eventTitle(event));
    }

    private static String creatorName(Event event) {
        return event.getUser().getUserDetails().getName();
    }

    private static String eventTitle(Event event) {
        return event.getTitle();
    }

}
