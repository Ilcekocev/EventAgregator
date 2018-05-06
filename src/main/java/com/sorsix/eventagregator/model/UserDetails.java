package com.sorsix.eventagregator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserDetails {
    @Id
    String authId;
    String firstName;
    String lastName;

    @JsonIgnore
    @OneToOne(mappedBy = "userDetails")
    User user;

    public UserDetails() {
    }

    public UserDetails(String authId, String firstName, String lastName) {
        this.authId = authId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserDetails createFromMapValues(String authId, String name) {
        String [] firstAndLastName = name.split("\\s+");
        String firstName = firstAndLastName[0];
        String lastName = firstAndLastName[1];
        return new UserDetails(authId, firstName, lastName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("authId", authId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
