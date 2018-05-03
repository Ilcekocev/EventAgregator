package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.sorsix.eventagregator.model.enums.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserDetails {
    @Id
    String authId;
    String firstName;
    String lastName;
    Provider provider;

    @Transient
    @OneToOne(mappedBy = "userDetails")
    User user;

    public UserDetails() {
    }

    public UserDetails(String authId, String firstName, String lastName, User user, Provider provider) {
        this.authId = authId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.provider = provider;
    }

    public static UserDetails createFromMapValues(String authId, String name, User user, Provider provider) {
        String [] firstAndLastName = name.split("\\s+");
        String firstName = firstAndLastName[0];
        String lastName = firstAndLastName[1];
        return new UserDetails(authId,firstName, lastName, user, provider);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("authId", authId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("provider", provider)
                .add("user", user)
                .toString();
    }
}
