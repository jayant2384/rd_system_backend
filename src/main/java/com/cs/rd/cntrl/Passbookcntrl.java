package com.cs.rd.cntrl;

import com.cs.rd.repo.Rdrepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.rd.entity.Rdpassbook;
import com.cs.rd.pdto.PassbookDTO;
import com.cs.rd.repo.Passbookrepo;
import com.cs.rd.service.Passbookservice;

@RestController
public class Passbookcntrl {
	
	private final Rdrepo rdrepo;

	@Autowired
	private Passbookrepo prepo;
	
	@Autowired
	private Passbookservice service;
	
	
	Passbookcntrl(Rdrepo rdrepo) {
		this.rdrepo = rdrepo;
	}
	@GetMapping("/detail")
	public List<PassbookDTO> getDetail(){
		return service.getDetail();
	}
	
	
	//CUSTOM QUERY
			@GetMapping("/pcnt")
			public Map<String , Object> getSummary(){
				Long total = prepo.getTotalEntry();
				Map<String , Object> result = new HashMap<>();
				result.put("total", total);
				return result;
	}
			
			@GetMapping("/ttl")
			public Map<String , Object> getTtl(){
				Long total = prepo.getTotal();
				Map<String , Object> result = new HashMap<>();
				result.put("total", total);
				return result;
	}
			
			@PostMapping("/psave")
			public Rdpassbook savepsk(@RequestBody Rdpassbook p){
				return prepo.save(p);
			}
			
			@PutMapping("/pupdt")
			public Rdpassbook updtpsk(@RequestBody Rdpassbook p){
				return prepo.save(p);
			}
			
			@GetMapping("/puser/{rid}")
			public List<PassbookDTO> getUserPassbookDetailsById(@PathVariable("rid") int rid){
				return service.getDetailById(rid);
			}
			
			@DeleteMapping("/pdel/{pid}")
			public String deleteNullPassBook(@PathVariable("pid") int pid) {
				prepo.deleteById(pid);
				return "Record Deleted";
			}
			
			
}
