package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.MachineType;
import org.com.dev.repository.MachineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MachineTypeService {
    @Autowired
	private MachineTypeRepository machineTypeRepository;
	
    @Transactional
    public void delete(Integer id){
    	machineTypeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public MachineType get(Integer id){
		return machineTypeRepository.findOne(id);
	}
	
	@Transactional
	public void save(MachineType machineType){
		 
		machineTypeRepository.saveAndFlush(machineType);
	}
 
	@Transactional(readOnly=true)
	public Page<MachineType> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return machineTypeRepository.findAll(pageable);
	}    
   
	@Transactional(readOnly=true)
	public List<MachineType> findAll(){
		return machineTypeRepository.findAll() ;
 	}

	@Transactional(readOnly=true)
	public List<MachineType> getAll(){
		return machineTypeRepository.findAll();
	}
	@Transactional(readOnly=true)
	public MachineType getByDesc(String desc) {
	 	return machineTypeRepository.getByDesc(desc);
	}
 }
