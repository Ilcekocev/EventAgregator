package com.sorsix.eventagregator.model.DTO;

public class InvitationDTO {

    public final String eventId;
    public final String personEmail;

    public InvitationDTO(String eventId, String personEmail) {
        this.eventId = eventId;
        this.personEmail = personEmail;
    }
}
