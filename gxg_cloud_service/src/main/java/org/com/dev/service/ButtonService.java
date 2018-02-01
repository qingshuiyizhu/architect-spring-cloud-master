package org.com.dev.service;


import java.util.List;

import org.com.dev.entity.Button;
import org.com.dev.entity.ButtonType;
import org.com.dev.repository.ButtonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ButtonService {
	@Autowired
	private ButtonRepository buttonRepository;

	@Transactional
	public void delete(Integer id) {
		buttonRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public Button get(Integer id) {
		return buttonRepository.findOne(id);
	}

	@Transactional
	public void save(Button button) {
		buttonRepository.saveAndFlush(button);
	}

	@Transactional(readOnly = true)
	public Page<Button> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return buttonRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<Button> findAll() {
		return buttonRepository.findAll();
	}
	@Transactional(readOnly = true)
	public List<Button> findAll_1() {
		return buttonRepository.findAll_1();
	}
	 
	public Button getByName(String name) {
		return buttonRepository.getByName(name);
	}

	 
	@Transactional(readOnly = true)
	public List<Button> getByBtype(ButtonType buttonType) {
		return null;
	 /*	return buttonRepository.getByBtype(buttonType);*/
	}

}
