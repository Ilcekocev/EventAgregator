package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.InvitedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitedPersonRepository extends JpaRepository<InvitedPerson, String> {

}
