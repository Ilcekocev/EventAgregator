package com.sorsix.eventagregator.model;

import com.sorsix.eventagregator.model.Type;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Where(clause = "type = 1")
@DiscriminatorValue(value="false")
public class EventPrivate extends Event{

}
