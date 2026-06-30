package com.cs.rd.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cs.rd.entity.Rduser;

public interface Rdrepo extends JpaRepository<Rduser, Integer>{
	
	Optional<Rduser> findByAdharno(String adharno);
	
	
//	@Query(value = "SELECT * FROM rduser WHERE adharno = :adharno", nativeQuery = true)
//	List<Rduser> logins(@Param("adharno") String adharno);
	
	@Query(value = "SELECT COUNT(*) FROM rduser", nativeQuery = true)
	Long getTotalUser();
	
//	@Query(value = "select name,rid,rdamt from rduser  where adharno = :adharno", nativeQuery = true)
//	List<Rduser> logins(@Param("adharno") String adharno);
	
}
