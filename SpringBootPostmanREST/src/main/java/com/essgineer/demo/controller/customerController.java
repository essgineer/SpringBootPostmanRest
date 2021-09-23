package com.essgineer.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.essgineer.demo.dao.CustomerRepository;
import com.essgineer.demo.model.Customer;

@RestController
public class customerController {
	
	@Autowired
	CustomerRepository repo;
	
	@PostMapping(path="/customer")
//	public Customer addCustomer( @RequestBody Customer customer) { for RAW DATA format
	public Customer addCustomer( Customer customer) {    //   json format
		if(repo.existsById(customer.getId())) {
			System.out.println("existing profile");
			return null;
		}
		System.out.println("Posting: "+customer);
		repo.save(customer);
		return customer;
	} 
	
	@PutMapping(path="customer")
	public Customer saveOrUpdateCustomer(Customer customer) {    
		System.out.println("Updating: "+customer);
		repo.save(customer);
		return customer;
	} 
	
	@GetMapping(path="/customers", produces= {"application/json"})
	public List<Customer> getCustomers() {
		System.out.println("Getting all customers");
		return repo.findAll();
	} 
	@GetMapping(path="/customer/{id}", produces= {"application/json"})
	public Optional<Customer> getCustomer(@PathVariable("id") int id) {
		System.out.println("Getting customer id: "+id);
		return repo.findById(id);
	} 
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable int id) {
		if(!repo.existsById(id)) {
			System.out.println("no profile");
			return null;
		}
		System.out.print("Deleting customer id: "+id);
		repo.deleteById(id);
		return "deleted";
	}
	
}
