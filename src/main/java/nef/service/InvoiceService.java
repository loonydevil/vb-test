package nef.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import nef.dto.InvoiceDTO;
import nef.model.Invoice;

/**
 * Contains all logic related to invoices interaction.
 * 
 * @since v1
 *
 */
public interface InvoiceService {

    /**
     * Converts invoices into useful representation
     * @param invoice the object to convert
     * @param periodDescription string describing the period in human-readable way, can be null
     * @param startDate start of the requested interval, can be null
     * @param endDate end of the requested interval, can be null
     * @return
     */
    InvoiceDTO convertInvoiceToDTO(Invoice invoice, String periodDescription, Date startDate, Date endDate);

    /**
     * Returns all invoices for the given customer
     * @param customerId id of the customer
     * @return list of customer's invoices
     */
    List<InvoiceDTO> getInvoicesByCustomer(Long customerId);

    /**
     * Returns all invoices for given address
     * @param addressId the address to search invoices
     * @return all invoices found for the address
     */
    List<InvoiceDTO> getInvoicesByAddress(Long addressId);

    /**
     * Returns all the invoices which were created within the given month
     * @param addressId the address to look for
     * @param customerId the owner of invoices
     * @param month 
     * @return found invioces
     */
    List<InvoiceDTO> getInvoicesWithinDate(Long addressId, Long customerId, int month);

    /**
     * Creates invoice from given data
     * @param customerId
     * @param addressId
     * @param invoiceType
     * @param dueDate
     * @param amount
     * @param vatAmount
     * @param totalAmount
     * @return invoiceDTO representing created invoice
     */
    InvoiceDTO createInvoice(Long customerId, Long addressId, Long invoiceType, Date dueDate, BigDecimal amount,
            BigDecimal vatAmount, BigDecimal totalAmount);

    /**
     * Deletes the invoice
     * @param invoice
     */
    void deleteInvoice(Invoice invoice);

    /**
     * Returns an invoice with specified id, null if the invoice doesn't exist
     * @param invoiceId
     * @return
     */
    Invoice findById(Long invoiceId);

}