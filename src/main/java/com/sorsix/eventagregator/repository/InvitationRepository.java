package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, String> {

}
