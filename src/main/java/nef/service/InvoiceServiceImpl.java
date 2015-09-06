package nef.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nef.dao.AddressRepository;
import nef.dao.CustomerRepository;
import nef.dao.InvoiceRepository;
import nef.dao.InvoiceTypeRepository;
import nef.dto.InvoiceDTO;
import nef.dto.InvoiceDTO.InvoiceDTOBuilder;
import nef.model.Address;
import nef.model.Customer;
import nef.model.Invoice;
import nef.model.InvoiceType;
import nef.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Default implementation of {@code InvoiceService}
 */
@Service 
public class InvoiceServiceImpl implements InvoiceService {
    
    public static final String NO_OBJECT_MESSAGE_TEMPLATE = "There's no such %s in database, check your input";
    
    InvoiceRepository invoiceRepository;
    
    AddressRepository addressRepository;
    
    CustomerRepository customerRepository;
    
    InvoiceTypeRepository invoiceTypeRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public InvoiceDTO convertInvoiceToDTO(Invoice invoice,
            String periodDescription,
            Date startDate,
            Date endDate) {
        InvoiceDTOBuilder builder = new InvoiceDTOBuilder(invoice);
        
        return builder.withPeriodDescription(periodDescription)
        .withStartDate(startDate)
        .withEndDate(endDate)
        .build();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<InvoiceDTO> getInvoicesByCustomer(Long customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomer(customerRepository.findOne(customerId));
        List<InvoiceDTO> result = new ArrayList<InvoiceDTO>();
        for (Invoice invoice: invoices) {
            result.add(convertInvoiceToDTO(invoice, null, null, null));
        }
        
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<InvoiceDTO> getInvoicesByAddress(Long addressId) {
        List<Invoice> invoices = invoiceRepository.findByAddress(addressRepository.findOne(addressId));
        List<InvoiceDTO> result = new ArrayList<InvoiceDTO>();
        for (Invoice invoice: invoices) {
            result.add(convertInvoiceToDTO(invoice, null, null, null));
        }
        
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<InvoiceDTO> getInvoicesWithinDate(Long addressId, Long customerId, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), month-1, 1, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.roll(Calendar.MONTH, 1);
        Date end = calendar.getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM YYYY");
        String period = dateFormat.format(start);
        List<Invoice> invoices = invoiceRepository.findByCustomerAndAddressAndCreationDateBetween(
                customerRepository.findOne(customerId), 
                addressRepository.findOne(addressId), start, end);
        List<InvoiceDTO> result = new ArrayList<InvoiceDTO>();
        
        for (Invoice invoice: invoices) {
            result.add(convertInvoiceToDTO(invoice, period, start, end));
        }

        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public InvoiceDTO createInvoice(Long customerId, Long addressId, Long invoiceTypeId, Date dueDate, BigDecimal amount,
            BigDecimal vatAmount, BigDecimal totalAmount) {
        Invoice invoice = new Invoice();
        
        Address address = addressRepository.findOne(addressId);
        Utils.checkIfNull(address, String.format(NO_OBJECT_MESSAGE_TEMPLATE, "address"));
        Customer customer = customerRepository.findOne(customerId);
        Utils.checkIfNull(customer, String.format(NO_OBJECT_MESSAGE_TEMPLATE, "customer"));
        InvoiceType invoiceType = invoiceTypeRepository.findOne(invoiceTypeId);
        Utils.checkIfNull(invoiceType, String.format(NO_OBJECT_MESSAGE_TEMPLATE, "invoiceType"));
        
        invoice.setAddress(address);
        invoice.setAmount(amount);
        invoice.setCreationDate(new Date());
        invoice.setDueDate(dueDate);
        invoice.setCustomer(customer);
        invoice.setTotal(totalAmount);
        invoice.setVat(vatAmount);
        invoice.setNumber((int)(Math.random()*1e9));
        invoice.setInvoiceType(invoiceType);
        invoiceRepository.save(invoice);
        
        return convertInvoiceToDTO(invoice, null, null, null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteInvoice(Invoice invoice) {
            invoiceRepository.delete(invoice);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Invoice findById(Long invoiceId) {
        return invoiceRepository.findOne(invoiceId);
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setInvoiceTypeRepository(InvoiceTypeRepository invoiceTypeRepository) {
        this.invoiceTypeRepository = invoiceTypeRepository;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


}
