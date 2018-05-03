package com.sorsix.eventagregator.repository;

import com.sorsix.eventagregator.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
}
