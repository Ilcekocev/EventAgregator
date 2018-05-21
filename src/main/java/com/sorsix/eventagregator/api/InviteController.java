package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.configuration.OnEventInvite;
import com.sorsix.eventagregator.model.DTO.InvitationDTO;
import com.sorsix.eventagregator.model.Invitation;
import com.sorsix.eventagregator.service.InviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/invite")
public class InviteController {

    private static final Logger logger = LoggerFactory.getLogger(InviteController.class);

    private final InviteService inviteService;
    private final ApplicationEventPublisher eventPublisher;

    public InviteController(InviteService inviteService, ApplicationEventPublisher eventPublisher) {
        this.inviteService = inviteService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<Invitation> invite(@RequestBody InvitationDTO invitationDTO) {
        logger.info("Received invitationDTO {}", invitationDTO);

        return inviteService.invite(invitationDTO)
                .map(invitation -> {
                    logger.info("publishing event");
                    eventPublisher.publishEvent(new OnEventInvite(invitation.getEvent()));
                    logger.info("finished publishing event");
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(invitation);
                })
                .orElse(ResponseEntity.badRequest().build());
    }
}
