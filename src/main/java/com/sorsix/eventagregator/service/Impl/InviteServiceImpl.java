package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.DTO.InvitationDTO;
import com.sorsix.eventagregator.model.Invitation;
import com.sorsix.eventagregator.repository.EventRepository;
import com.sorsix.eventagregator.repository.InvitationRepository;
import com.sorsix.eventagregator.repository.UserRepository;
import com.sorsix.eventagregator.service.InviteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InviteServiceImpl implements InviteService {
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
        return eventRepository.findById(invitationDTO.eventId)
                .map(event -> userRepository.findById(invitationDTO.personEmail)
                        .map(user -> {
                            Invitation invitation = new Invitation(user.getId(), event, user);
                            return invitationRepository.save(invitation);
                        })
                        .orElseGet(() -> {
                            Invitation invitation = new Invitation(invitationDTO.personEmail, event);
                            return invitationRepository.save(invitation);
                        }));
    }
}
