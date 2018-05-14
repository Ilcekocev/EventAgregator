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
    String name;
    String avatar;

    @JsonIgnore
    @OneToOne(mappedBy = "userDetails")
    User user;

    public UserDetails() {
    }

    public UserDetails(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("avatar", avatar)
                .toString();
    }
}
