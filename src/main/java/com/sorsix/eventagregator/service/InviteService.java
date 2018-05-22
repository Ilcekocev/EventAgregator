package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.DTO.InvitationDTO;
import com.sorsix.eventagregator.model.Invitation;

import java.util.Optional;

public interface InviteService {

    Optional<Invitation> invite(InvitationDTO invitationDTO);

}
