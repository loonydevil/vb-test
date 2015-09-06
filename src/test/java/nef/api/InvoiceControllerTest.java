package nef.api;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import nef.AppEntryPoint;
import nef.dto.InvoiceDTO;
import nef.dto.InvoiceDTO.InvoiceDTOBuilder;
import nef.service.InvoiceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppEntryPoint.class)
@WebAppConfiguration
public class InvoiceControllerTest {

  @Autowired
  protected WebApplicationContext wac;
  
    private static  String URL = InvoiceController.PATH;
    private static Long ID = Long.valueOf(1);
    private static BigDecimal AMOUNT = BigDecimal.TEN;
    private static String DATE = "1-1-2015";
    private MockMvc mockMvc;
    
    @Autowired
    private InvoiceController invoiceController;
    
    @Autowired
    private ObjectMapper mapper;
    
    private InvoiceService invoiceService;

    private InvoiceDTO invoiceDTO;
    
    @Before
    public void init() throws ParseException {
        this.mockMvc = webAppContextSetup(this.wac).build();
        
        InvoiceDTOBuilder builder = new InvoiceDTOBuilder();
        this.invoiceDTO = builder.withInvoiceId(ID).build();
        this.invoiceService = Mockito.mock(InvoiceService.class);
        SimpleDateFormat format = new SimpleDateFormat(InvoiceController.DATE_FORMAT);
        Mockito.when(invoiceService.createInvoice(ID, ID, ID, format.parse(DATE), AMOUNT, AMOUNT, AMOUNT)).thenReturn(invoiceDTO);
        invoiceController.setInvoiceService(invoiceService);
    }
    
    @Test
    public void testFindInvoicesForCustomer() throws Exception {
        Mockito.when(invoiceService.getInvoicesByCustomer(ID)).thenReturn(Collections.singletonList(invoiceDTO));
        RequestBuilder builder = MockMvcRequestBuilders.get(URL).param("customerId", ID.toString());
        ResultActions result = mockMvc.perform(builder);
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(Collections.singletonList(invoiceDTO))));
    }
    
    
    @Test
    public void testCreateInvoice() throws Exception {
        Mockito.when(invoiceService.getInvoicesByCustomer(ID)).thenReturn(Collections.singletonList(invoiceDTO));
        RequestBuilder builder = MockMvcRequestBuilders.post(URL)
                .param("customerId", ID.toString())
                .param("addressId", ID.toString())
                .param("invoiceTypeId", ID.toString())
                .param("dueDate", DATE)
                .param("amount", AMOUNT.toString())
                .param("totalAmount", AMOUNT.toString())
                .param("vatAmount", AMOUNT.toString());
        
        ResultActions result = mockMvc.perform(builder);
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(invoiceDTO)));
    }
    
    
}
        
