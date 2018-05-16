package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // List<Event> findAllByUser(User user);
    List<Event> findAllByUserIdAndStartTimeBetween(String id, LocalDateTime start, LocalDateTime end);

    List<Event> findEventsByUserIdAndType(String userId, Type type);

    // List<Event> findAllByUserIdAndStartTimeMonthValue(Long id, Integer month);
    List<Event> findAllByEmailNotificationIsTrueAndNotifiedIsFalse();
    List<Event> findAllByEmailNotificationIsTrueAndNotifiedIsFalseAndStartTimeIsBetween(LocalDateTime before, LocalDateTime then);
}

