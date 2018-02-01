package org.com.dev.service;

import java.util.Date;
import java.util.List;

import org.com.dev.entity.Machine;
import org.com.dev.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MachineService {
    @Autowired
	private MachineRepository machineRepository;
	
	@Transactional
	public void delete(Integer id){
		machineRepository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public Machine get(Integer id){
		return machineRepository.findOne(id);
	}
	
	@Transactional
	public void save(Machine machine){
		if(machine.getId() == null){
			machine.setCreateTime(new Date());
		}
		machineRepository.saveAndFlush(machine);
	}
 
	@Transactional(readOnly=true)
	public Page<Machine> getPage(int pageNo, int pageSize){
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return machineRepository.findAll(pageable);
	}    
	
	@Transactional(readOnly=true)
	public List<Machine> getAll(){
		return machineRepository.findAll();
 	}
	@Transactional(readOnly=true)
	public Machine getByName(String name) {
	 
		return machineRepository.getByName(name);
	}
	 
	@Transactional(readOnly=true)
	public Machine getByMAC(String mAC) {
	 	return machineRepository.getByMac(mAC);
	}
	@Transactional(readOnly=true)
	public List<Machine> getByMgid(int mgid) {
	 
		return machineRepository.getByMgid(mgid);
	}
	@Transactional(readOnly=true)
	public List<Machine> getIsPass() {
		 
		return machineRepository.getIsPass();
	}
	
	@Transactional(readOnly=true)
	public List<Machine> getRows1() {	 
		return  machineRepository.getRows1();
	}
	@Transactional
	public void editMachine(int mgid) {
		machineRepository.editMachine(mgid);
	}

	public List<Machine> getRows2(int mgid) {
		return machineRepository.getRows2(mgid);
	
	}

	public List<Machine> getRows3() {
		return machineRepository.getRows3();
	} 
}
