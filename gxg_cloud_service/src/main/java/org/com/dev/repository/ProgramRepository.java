package org.com.dev.repository;

import org.com.dev.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
	Program getByName(String name);

	 

}
