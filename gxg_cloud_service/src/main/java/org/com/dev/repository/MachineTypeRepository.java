package org.com.dev.repository;

import org.com.dev.entity.MachineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineTypeRepository extends JpaRepository<MachineType, Integer> {

	MachineType getByDesc(String desc);

	 

}
