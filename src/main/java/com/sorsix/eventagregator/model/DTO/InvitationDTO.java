package com.sorsix.eventagregator.model.DTO;

import com.google.common.base.Objects;
import lombok.Getter;

@Getter
public class InvitationDTO {

    public Long eventId;
    public String personEmail;

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("eventId", eventId)
                .add("personEmail", personEmail)
                .toString();
    }
}
