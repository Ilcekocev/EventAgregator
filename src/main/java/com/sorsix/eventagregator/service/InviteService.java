package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.DTO.InvitationDTO;
import com.sorsix.eventagregator.model.InvitedPerson;

public interface InviteService {

    InvitedPerson invite(InvitationDTO invitationDTO);

}
