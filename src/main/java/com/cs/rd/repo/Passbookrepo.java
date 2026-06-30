package com.cs.rd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cs.rd.entity.Rdpassbook;
import com.cs.rd.pdto.PassbookDTO;

public interface Passbookrepo extends JpaRepository<Rdpassbook, Integer>{
	
	//Custom Query
	@Query(value = "SELECT COUNT(*) FROM rdpassbook", nativeQuery = true)
	Long getTotalEntry();
	
	@Query(value = "SELECT SUM(rdamt) FROM rdpassbook", nativeQuery = true)
	Long getTotal();
	
	
/////////////////////////////  JOIN Query  //////////////////////////////////////////////////////////////////
	
	@Query(value = "SELECT pid,name, acno,rdpassbook.rdamt, rdpassbook.rddate FROM rduser INNER JOIN rdpassbook on rduser.rid=\r\n"
		+"rdpassbook.rid"	,nativeQuery = true)
	List<PassbookDTO> getUserPassbookDetails();
//	@Query(value = "SELECT name, acno,rdpassbook.rdamt, rdpassbook.rddate FROM rduser INNER JOIN rdpassbook on rduser.rid=\r\n"
//			+"rdpassbook.rid WHERE rdpassbook.rid = :rid"	,nativeQuery = true)
//		List<PassbookDTO> getUserPassbookDetailsById(@Param("rid") int rid);
	
	@Query(value = """
			SELECT
			p.pid,
			p.rid,
			u.name,
			p.rddate,
			p.rdamt,
			p.lday,
			p.famt
			FROM rdpassbook p
			INNER JOIN rduser u
			ON p.rid = u.rid
			WHERE p.rid = :rid
			ORDER BY p.rddate DESC
			""", nativeQuery = true)
		List<PassbookDTO> getUserPassbookDetailsById(@Param("rid") int rid);
	
}
