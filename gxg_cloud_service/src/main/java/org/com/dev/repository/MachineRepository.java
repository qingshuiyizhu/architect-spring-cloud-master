package org.com.dev.repository;

import java.util.List;

import org.com.dev.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MachineRepository extends JpaRepository<Machine, Integer> {

	Machine getByName(String name); 
	Machine getByMac(String mac);
	
	List<Machine> getByMgid(int mgid);
	
	@Query("FROM Machine m WHERE state=1")
 	List<Machine> getIsPass();
	
	@Modifying
	@Query("update Machine m set m.mgid=0 where m.mgid=:mgid")
    void editMachine(@Param("mgid") int mgid);
	
	@Query("select new Machine(m.id,m.name,mg.id,mg.name) from Machine m, MachineGroup mg where m.mgid = mg.id")
 	List<Machine> getRows1();
		
	@Query("select new Machine(m.id,m.name) from Machine m where m.mgid = :mgid")
	List<Machine> getRows2(@Param("mgid") int mgid); 
	
	@Query("select new Machine(m.id,m.name) from Machine m")
	List<Machine> getRows3(); 
}
