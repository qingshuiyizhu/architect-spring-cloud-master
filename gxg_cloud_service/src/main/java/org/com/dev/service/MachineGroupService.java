package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.MachineGroup;
import org.com.dev.repository.MachineGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MachineGroupService {

	@Autowired
	private MachineGroupRepository machineGroupRepository;

	@Transactional
	public void delete(Integer id) {
		machineGroupRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public MachineGroup get(Integer id) {
		return machineGroupRepository.findOne(id);
	}

	@Transactional
	public void save(MachineGroup machineGroup) {
		machineGroupRepository.saveAndFlush(machineGroup);
	}

	@Transactional(readOnly = true)
	public Page<MachineGroup> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return machineGroupRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<MachineGroup> findAll() {
		return machineGroupRepository.findAll();
	}

	@Transactional(readOnly=true)
	public List<MachineGroup> getAll(){
		return machineGroupRepository.findAll();
	}

	public MachineGroup getByName(String name) {
		return machineGroupRepository.getByName(name);
	}
}
