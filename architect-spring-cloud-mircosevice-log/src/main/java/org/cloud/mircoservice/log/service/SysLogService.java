package org.cloud.mircoservice.log.service;

import java.util.Date;
import java.util.List;

import org.cloud.mircoservice.log.entity.SysLog;
import org.cloud.mircoservice.log.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SysLogService {
	@Autowired
	private SysLogRepository repository;
 	@Transactional
	public void delete(Integer id) {
		repository.delete(id);
	}
 	@Transactional(readOnly = true)
	public SysLog get(Integer id) {
		return repository.findOne(id);
	}
 
	@Transactional
	public void save(SysLog entity) {
		/*if(entity.getId() == null){
			entity.setTime(new Date());
		}*/
	 	repository.saveAndFlush(entity);
	}

	@Transactional(readOnly = true)
	public Page<SysLog> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return repository.findAll(pageable);
	}
 	@Transactional(readOnly = true)
	public List<SysLog> findAll() {
		return repository.findAll();
	}
}
