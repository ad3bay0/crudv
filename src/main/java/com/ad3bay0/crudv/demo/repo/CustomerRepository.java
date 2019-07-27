package com.ad3bay0.crudv.demo.repo;

import java.util.List;

import com.ad3bay0.crudv.demo.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * CustomerRepository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}