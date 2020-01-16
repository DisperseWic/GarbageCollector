package com.magister.garbagecollector.entities.jpa;

import java.util.List;

import com.magister.garbagecollector.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByUsername(String username);
}
