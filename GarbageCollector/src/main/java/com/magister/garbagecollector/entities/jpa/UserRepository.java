package com.magister.garbagecollector.entities.jpa;

import java.util.Optional;

import com.magister.garbagecollector.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
}
