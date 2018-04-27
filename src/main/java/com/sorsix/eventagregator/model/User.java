package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue
    protected Long id;
    String email;
    String firstName;
    String lastName;
    @JsonIgnore
    LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    List<EventPrivate> privateEvents;

    public User(){}




}
