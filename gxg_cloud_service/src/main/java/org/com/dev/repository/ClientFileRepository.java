package org.com.dev.repository;

import java.util.List;

import org.com.dev.entity.ClientFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientFileRepository extends JpaRepository<ClientFile, Integer> {

	@Modifying
	@Query("update ClientFile c set c.isdel=1 where c.id=:id")
	void editRow(@Param("id") Integer id);

	@Query("select c from ClientFile c where c.cid =:id")
	List<ClientFile> findByCid(@Param("id") Integer id);

	@Modifying
	@Query("delete from ClientFile cf where cf.cid = :cid and cf.path=:path")
	void delFile(@Param("cid") Integer cid, @Param("path") String path);
	
	@Modifying
	@Query("update ClientFile c set c.schedule=:finish where c.cid=:cid and c.path=:path and c.name=:fileName")
	void reFile(@Param("cid")Integer cid,@Param("path") String path,@Param("fileName") String fileName, @Param("finish")Integer finish);
	@Query("select c from ClientFile c where c.cid =:id and c.schedule <100 or isdel!=0")
	List<ClientFile> findByCidDown(@Param("id")Integer id);

}