package org.com.dev.service;

import java.util.List;

import javax.annotation.Resource;

import org.com.dev.entity.Machine;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class TimerService {
	 	@Resource
		private MachineService machineService;
	 	
	 	@Scheduled(cron = "0 * * * * ?")
      	public void job1() {
	 		System.out.println("执行定时计划");
			List<Machine> machines = machineService.getAll();
			Machine machine;
			for (Machine m : machines) {
				machine = m;
				machine.setHeartbeat(0);
				machineService.save(machine);
			}

		}
	 
}
