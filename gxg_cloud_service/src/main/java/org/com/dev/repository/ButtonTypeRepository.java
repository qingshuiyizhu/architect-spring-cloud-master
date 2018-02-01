package org.com.dev.repository;

import org.com.dev.entity.ButtonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ButtonTypeRepository extends JpaRepository<ButtonType, Integer> {

	ButtonType getByName(String name);

	 

}
