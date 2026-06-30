package com.cs.rd.cntrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.cs.rd.entity.Rduser;
import com.cs.rd.repo.Rdrepo;


@RestController
public class Rdcntrl {
	@Autowired
	private Rdrepo repo;

	@GetMapping("/rduser")
	public List<Rduser> getRd(){
		List<Rduser> user = repo.findAll();
		return user;	
	}
	//CUSTOM QUERY
		@GetMapping("/cnt")
		public Map<String , Object> getSummary(){
			Long total = repo.getTotalUser();
			Map<String , Object> result = new HashMap<>();
			result.put("total", total);
			return result;
	}
		//Login Query 
		@GetMapping("/login/{adharno}")
		public ResponseEntity<?> getLogin(@PathVariable("adharno") String adharno){
			Optional<Rduser> user = repo.findByAdharno(adharno);
			if(user.isPresent()) {
				return ResponseEntity.ok(user.get());
			}
			else {

			    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			                         .body("Invalid Aadhar Number");

			}
		}
		
		
	@PostMapping("/save")
	public Rduser saveps(@RequestBody Rduser p){
		return repo.save(p);
	}
	@PutMapping("/updt")
	public Rduser updt(@RequestBody Rduser s) {
		return repo.save(s);
	}
	@DeleteMapping("/del/{id}")
	public String Delete(@PathVariable("id") int id) {
		repo.deleteById(id);
		return "RECORD DELETED";
	}
	
}
