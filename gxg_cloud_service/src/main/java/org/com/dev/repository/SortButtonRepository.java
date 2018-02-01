package org.com.dev.repository;

import org.com.dev.entity.SortButton;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortButtonRepository extends JpaRepository<SortButton, Integer> {

	SortButton getByName(String name);
	 
}
