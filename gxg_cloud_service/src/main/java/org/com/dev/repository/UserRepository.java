package org.com.dev.repository;

import java.util.List;
import java.util.Set;

import org.com.dev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

	User getByName(String name);
	 
	@Query("select p.name from User u,UserRole r,Permission p where u.id=r.id and p.role=r.id and u.name=:userName")
	Set<String> getPermissions(@Param("userName") String userName);
	
 	@Query("select new User(u.id, u.name, u.password, u.create_time, u.alter_time, u.rid,ur.name) from User u,UserRole ur where u.rid = ur.id")
	List<User> getRows();
	
	
	
}
