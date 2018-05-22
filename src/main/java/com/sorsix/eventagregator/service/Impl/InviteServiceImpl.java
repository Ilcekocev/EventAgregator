package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.DTO.InvitationDTO;
import com.sorsix.eventagregator.model.Invitation;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.repository.InvitationRepository;
import com.sorsix.eventagregator.repository.UserRepository;
import com.sorsix.eventagregator.service.InviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InviteServiceImpl implements InviteService {
    private static final Logger logger = LoggerFactory.getLogger(InviteServiceImpl.class);

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;

    public InviteServiceImpl(EventRepository eventRepository, UserRepository userRepository, InvitationRepository invitationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
    }


    @Override
    public Optional<Invitation> invite(InvitationDTO invitationDTO) {
        return userRepository.findById(invitationDTO.personEmail)
                .flatMap(user -> eventRepository.findById(invitationDTO.eventId)
                        .map(event -> invitationRepository.save(Invitation.createInvitationForRegisteredUser(user, event))));
    }
}
