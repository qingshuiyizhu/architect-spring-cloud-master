package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.SortButton;
import org.com.dev.repository.SortButtonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SortButtonService {
	@Autowired
	private SortButtonRepository sortButtonRepository;

	@Transactional
	public void delete(Integer id) {
		sortButtonRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public SortButton get(Integer id) {
		return sortButtonRepository.findOne(id);
	}

	@Transactional
	public void save(SortButton sortButton) {
		sortButtonRepository.saveAndFlush(sortButton);
	}

	@Transactional(readOnly = true)
	public Page<SortButton> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return sortButtonRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<SortButton> findAll() {
		return sortButtonRepository.findAll();
	}

	public SortButton getByName(String name) {
		return sortButtonRepository.getByName(name);
	}

	@Transactional(readOnly=true)
	public List<SortButton> getAll(){
		return sortButtonRepository.findAll();
	}
}
