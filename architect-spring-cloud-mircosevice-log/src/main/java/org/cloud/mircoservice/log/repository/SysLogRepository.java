package org.cloud.mircoservice.log.repository;

import org.cloud.mircoservice.log.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogRepository extends JpaRepository<SysLog, Integer> {
	
 
}
