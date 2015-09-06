package nef.service;

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
import nef.service.InvoiceServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

/**
 * Tests for InvoiceServiceImpl
 */
public class InvoiceServiceTest {
    
    private static final Long INVOICE_ID = Long.valueOf(1);
    private static final Long NON_EXISTING_ID = Long.valueOf(42);
    private static final Long CUSTOMER_ID = Long.valueOf(2);
    private static final Long ADDRESS_ID = Long.valueOf(3);
    private static final Long TYPE_ID = Long.valueOf(4);
    private InvoiceServiceImpl invoiceServiceImpl = new InvoiceServiceImpl();
    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;
    private InvoiceRepository invoiceRepository;
    private InvoiceTypeRepository invoiceTypeRepository;
    private Invoice invoice;
    
    @Before
    public void init() {
        this.invoice = new Invoice();
        invoice.setId(INVOICE_ID);
        Address address = new Address();
        address.setId(ADDRESS_ID);
        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setId(TYPE_ID);
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        invoice.setAddress(address);
        invoice.setInvoiceType(invoiceType);
        invoice.setCustomer(customer);
        
        customerRepository = Mockito.mock(CustomerRepository.class);
        Mockito.when(customerRepository.findOne(CUSTOMER_ID)).thenReturn(customer);

        invoiceRepository = Mockito.mock(InvoiceRepository.class);
        Mockito.when(invoiceRepository.findOne(INVOICE_ID)).thenReturn(this.invoice);
        
        addressRepository = Mockito.mock(AddressRepository.class);
        Mockito.when(addressRepository.findOne(ADDRESS_ID)).thenReturn(address);
        
        invoiceTypeRepository = Mockito.mock(InvoiceTypeRepository.class);
        Mockito.when(invoiceTypeRepository.findOne(TYPE_ID)).thenReturn(invoiceType);
        
        invoiceServiceImpl.setAddressRepository(addressRepository);
        invoiceServiceImpl.setCustomerRepository(customerRepository);
        invoiceServiceImpl.setInvoiceRepository(invoiceRepository);
        invoiceServiceImpl.setInvoiceTypeRepository(invoiceTypeRepository);
    }
	
	@Test
	public void testFindInvoiceById() throws Exception {
	    Invoice invoice = invoiceServiceImpl.findById(INVOICE_ID);
	    assertEquals(INVOICE_ID, invoice.getId());
	}
	
    @Test
    public void testFindInvoiceByNonExistingId() throws Exception {
        Invoice invoice = invoiceServiceImpl.findById(NON_EXISTING_ID);
        assertEquals(null, invoice);
    }
    
    @Test
    public void testConvertToDto() {
        InvoiceDTOBuilder builder = new InvoiceDTOBuilder();
        InvoiceDTO dto = builder.withInvoiceId(INVOICE_ID)
                .withAddress(invoice.getAddress())
                .withCustomerId(CUSTOMER_ID)
                .withInvoiceType(invoice.getInvoiceType())
                .build();
        assertEquals(dto, invoiceServiceImpl.convertInvoiceToDTO(invoice, null, null, null));
    }

    @Test
    public void testCreateInvoiceReturnsInvoiceDTO() {
        InvoiceDTO dto = invoiceServiceImpl.createInvoice(CUSTOMER_ID, ADDRESS_ID, TYPE_ID, null, null, null, null);
        assertTrue(dto != null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateInvoiceWithWrongParamsThrowsException() {
        invoiceServiceImpl.createInvoice(NON_EXISTING_ID, NON_EXISTING_ID, NON_EXISTING_ID, null, null, null, null);
    }
	

}
