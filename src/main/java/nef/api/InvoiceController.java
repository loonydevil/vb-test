package nef.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nef.dto.InvoiceDTO;
import nef.model.Invoice;
import nef.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController extends BaseController {
    
    public final static String PATH = "/some_name/v1.0/invoices/";
    private final static String RESULT_KEY = "result";
    public static String DATE_FORMAT = "dd-MM-yyyy";

    InvoiceService invoiceService;
    
    @RequestMapping(value = PATH, method = RequestMethod.GET, produces = "application/json")
    public List<InvoiceDTO> getInvoicesByAddressAndDate(
            @RequestParam(value = "addressId", required = false) Long addressId,
            @RequestParam("customerId") Long customerId,
            @RequestParam(value = "month", required = false) Integer month) {

        if (addressId == null && month == null) {
            return invoiceService.getInvoicesByCustomer(customerId);
        }
        return invoiceService.getInvoicesWithinDate(addressId, customerId, month);
    }
    
    @RequestMapping(value = PATH, method = RequestMethod.POST, produces = "application/json")
    public InvoiceDTO createInvoice(
            @RequestParam("customerId") Long customerId,
            @RequestParam("addressId") Long addressId,
            @RequestParam("invoiceTypeId") Long invoiceType,
            @RequestParam("dueDate") String dueDate,
            @RequestParam("amount") BigDecimal amount,
            @RequestParam("vatAmount") BigDecimal vatAmount,
            @RequestParam("totalAmount") BigDecimal totalAmount
            ) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(dueDate);
        return invoiceService.createInvoice(customerId, addressId, invoiceType, date, amount, vatAmount, totalAmount);
    }

    @RequestMapping(value = PATH, method = RequestMethod.DELETE)
    public Map<String, String> deleteInvoice(@RequestParam Long invoiceId) {
        Invoice invoice = invoiceService.findById(invoiceId);
        Map<String, String> result = new HashMap<String, String>();
        if (invoice != null) {
            invoiceService.deleteInvoice(invoice);
            result.put(RESULT_KEY, "success");
        } else {
            result.put(RESULT_KEY, "There's no invoice with such id");
        }
        return result;
    }

    
    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
