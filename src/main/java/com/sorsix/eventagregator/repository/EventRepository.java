package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByUser(User user);

    List<Event> findAllByUserIdAndStartTimeMonthValue(Long id, Integer month);

}
