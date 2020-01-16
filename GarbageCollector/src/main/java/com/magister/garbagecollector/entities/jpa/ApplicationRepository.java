package com.magister.garbagecollector.entities.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.magister.garbagecollector.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Optional<Application> findById(Long id);
}
