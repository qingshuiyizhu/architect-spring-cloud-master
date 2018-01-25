package org.cloud.microservice.user.service;

import java.util.List;

import org.cloud.microservice.user.entity.User;
import org.cloud.microservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
	@Autowired
	private UserRepository repository;
 	@Transactional
	public void delete(Integer id) {
		repository.delete(id);
	}
 	@Transactional(readOnly = true)
	public User get(Integer id) {
		return repository.findOne(id);
	}
 
	@Transactional
	public void save(User entity) {
		/*if(entity.getId() == null){
			entity.setTime(new Date());
		}*/
	 	repository.saveAndFlush(entity);
	}

	@Transactional(readOnly = true)
	public Page<User> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return repository.findAll(pageable);
	}
 	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}
}
