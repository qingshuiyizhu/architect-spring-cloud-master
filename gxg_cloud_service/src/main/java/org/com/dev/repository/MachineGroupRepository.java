package org.com.dev.repository;

import org.com.dev.entity.MachineGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineGroupRepository extends JpaRepository<MachineGroup, Integer> {

	MachineGroup getByName(String name);

	 
 

}
