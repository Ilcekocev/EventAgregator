package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserDetails {
    @Id
    Long id;
    String email;
    String firstName;
    String lastName;
    @JsonIgnore
    LocalDate birthDate;
    @OneToOne(mappedBy = "userDetails")
    User user;

    public UserDetails() {
    }

    public UserDetails(Long id, String email, String firstName, String lastName, LocalDate birthDate, User user) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.user = user;
    }
}
