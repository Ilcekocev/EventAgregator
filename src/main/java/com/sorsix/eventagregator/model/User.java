package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Event> privateEvents;

    @OneToOne
    UserDetails userDetails;

    public User(){}

}
