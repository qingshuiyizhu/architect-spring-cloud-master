package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.ButtonType;
import org.com.dev.repository.ButtonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ButtonTypeService {
    @Autowired
	private ButtonTypeRepository buttonTypeRepository;
	
    
    public void delete(Integer id){
    	buttonTypeRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public ButtonType get(Integer id){
		return buttonTypeRepository.findOne(id);
	}
	
	@Transactional
	public void save(ButtonType buttonType){
		 
		buttonTypeRepository.saveAndFlush(buttonType);
	}
 
	@Transactional(readOnly=true)
	public Page<ButtonType> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return buttonTypeRepository.findAll(pageable);
	}    
   
	@Transactional(readOnly=true)
	public List<ButtonType> findAll(){
		return buttonTypeRepository.findAll() ;
 	}

	@Transactional(readOnly=true)
	public List<ButtonType> getAll(){
		return buttonTypeRepository.findAll();
	}
	@Transactional(readOnly=true)
	public ButtonType getByName(String name) {
	 	return buttonTypeRepository.getByName(name);
	}
 }
