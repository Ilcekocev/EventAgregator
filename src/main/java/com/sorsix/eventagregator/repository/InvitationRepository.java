package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.Invitation;
import com.sorsix.eventagregator.model.InvitationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, InvitationId> {

    List<Invitation> findAllByNotifiedIsFalse();

    Optional<Invitation> findByEvent(Event event);

}
