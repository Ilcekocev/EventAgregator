package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    String email;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Event> privateEvents;

    @OneToOne
    UserDetails userDetails;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("email", email)
                .add("userDetails", userDetails)
                .toString();
    }
}
