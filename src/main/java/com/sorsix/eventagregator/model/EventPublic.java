package com.sorsix.eventagregator.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Where(clause = "type = 0")
@DiscriminatorValue(value="true")
public class EventPublic extends Event{

}
