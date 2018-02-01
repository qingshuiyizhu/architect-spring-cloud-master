package org.com.dev.service;

import java.util.List;

import org.com.dev.entity.ClientFile;
import org.com.dev.repository.ClientFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ClientFileService {
	@Autowired
	private ClientFileRepository clientFileRepository;

	@Transactional
	public void save(ClientFile clientFile) {
		clientFileRepository.saveAndFlush(clientFile);

	}
	
	@Transactional(readOnly=true)
	public List<ClientFile> findAll() {  
		return clientFileRepository.findAll();
		 
	}
	@Transactional
	public void delete(Integer id) {
		clientFileRepository.delete(id);
 	}
	@Transactional
	public void editRow(Integer id) {
		clientFileRepository.editRow(id);
	}
	@Transactional(readOnly=true)
	public List<ClientFile> findByCid(Integer id) {
		 
		return clientFileRepository.findByCid(id);
	}
	@Transactional
	public void delFile(Integer id, String path) {
		clientFileRepository.delFile(id,path);
		
	}
	@Transactional
	public void reFile(Integer id, String path, String fileName,Integer finish) {
		clientFileRepository.reFile(id,path,fileName,finish);
		
	}
	@Transactional(readOnly=true)
	public List<ClientFile> findByCidDown(Integer id) {
		return clientFileRepository.findByCidDown(id);
 	}

}
