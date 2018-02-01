package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.Program;
import org.com.dev.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgramService {
    @Autowired
	private ProgramRepository programRepository;
    
    @Transactional
    public void delete(Integer id){
    	programRepository.delete(id);
	}	
	@Transactional(readOnly=true)
	public Program get(Integer id){
		return programRepository.findOne(id);
	}
	@Transactional
	public void save(Program program){
	 	programRepository.saveAndFlush(program);
	}
 
	@Transactional(readOnly=true)
	public Page<Program> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return programRepository.findAll(pageable);
	}    
   
	@Transactional(readOnly=true)
	public List<Program> findAll(){
		return programRepository.findAll() ;
 	}
 	@Transactional(readOnly=true)
	public Program getByName(String name) {
	 	return programRepository.getByName(name);
	}
 }
