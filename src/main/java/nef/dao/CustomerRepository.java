package nef.dao;

import nef.model.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
