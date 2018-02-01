package org.com.dev.repository;

import org.com.dev.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	UserRole getByName(String name);
	
	 

}
