package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.UserRole;
import org.com.dev.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Transactional
	public void delete(Integer id) {
		userRoleRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public UserRole get(Integer id) {
		return userRoleRepository.findOne(id);
	}

	@Transactional
	public void save(UserRole userRole) {
		userRoleRepository.saveAndFlush(userRole);
	}

	@Transactional(readOnly = true)
	public Page<UserRole> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return userRoleRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<UserRole> findAll() {
		return userRoleRepository.findAll();
	}

	public UserRole getByName(String name) {
		return userRoleRepository.getByName(name);
	}

	@Transactional(readOnly=true)
	public List<UserRole> getAll(){
		return userRoleRepository.findAll();
	}
}
