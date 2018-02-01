package org.com.dev.service;

import java.util.Date;
import java.util.List;

import org.com.dev.entity.Request;
import org.com.dev.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;

	@Transactional
	public void delete(Integer id) {
		requestRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public Request get(Integer id) {
		return requestRepository.findOne(id);
	}

	@Transactional
	public void save(Request request) {
		if(request.getId() == null){
			request.setCreateTime(new Date());
		}
		requestRepository.saveAndFlush(request);
	}

	@Transactional(readOnly = true)
	public Page<Request> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return requestRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<Request> findAll() {
		return requestRepository.findAll();
	}
}
