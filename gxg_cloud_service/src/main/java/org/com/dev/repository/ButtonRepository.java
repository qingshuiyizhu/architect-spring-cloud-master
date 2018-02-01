package org.com.dev.repository;

import java.util.List;

import org.com.dev.entity.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ButtonRepository extends JpaRepository<Button, Integer> {
	
	Button getByName(String name);
	
	@Query("select new Button(b.id,b.name,b.tid,bt.name)from Button b, ButtonType bt where b.tid = bt.id")
	List<Button> findAll_1();
}
