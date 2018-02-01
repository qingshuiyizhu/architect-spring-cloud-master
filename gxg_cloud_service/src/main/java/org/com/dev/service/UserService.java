package org.com.dev.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.com.dev.entity.User;
import org.com.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void delete(Integer id) {
		userRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public User get(Integer id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public void save(User user) {
		if(user.getId()==null){
			user.setCreate_time(new Date());
		}
		user.setAlter_time(new Date());
		userRepository.saveAndFlush(user);
	}

	@Transactional(readOnly = true)
	public Page<User> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User getByName(String name) {
		return userRepository.getByName(name);
	}

	@Transactional(readOnly=true)
	public List<User> getAll(){
		return userRepository.findAll();
	}
	/*@Transactional(readOnly=true)
	public Set<String> getRoles(String userName) {
		 Set<String> roles= new HashSet<String>();
		 roles.add(userRepository.getByName(userName).getRole().getName());
		return roles;
	}*/
	@Transactional(readOnly=true)
	public Set<String> getPermissions(String userName) {
		 
	 	return userRepository.getPermissions(userName);
	}
	@Transactional(readOnly=true)
	public User getByUserName(String userName) {
		 
		return userRepository.getByName(userName);
	}
}
