package nef.dao;

import java.util.Date;
import java.util.List;

import nef.model.Address;
import nef.model.Customer;
import nef.model.Invoice;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    
    List<Invoice> findByAddress(Address address);

    List<Invoice> findByCustomer(Customer customer);
    
    List<Invoice> findByCustomerAndAddressAndCreationDateBetween(Customer customer, Address address, Date start, Date end);

}
