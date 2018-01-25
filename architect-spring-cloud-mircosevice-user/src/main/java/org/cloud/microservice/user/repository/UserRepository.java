package org.cloud.microservice.user.repository;

import org.cloud.microservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
 
}
