package com.essgineer.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.essgineer.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
