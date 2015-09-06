package nef.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import nef.model.Address;
import nef.model.Invoice;
import nef.model.InvoiceType;

/**
 * User's representation of inoice entity
 *
 */
@JsonInclude(Include.NON_NULL)
public class InvoiceDTO {

    private Long customerId;
    private Long addressId;
    private Long invoiceId;
    private String invoiceType;
    private Date invoiceDate;
    private Date paymentDueDate;
    private int invoiceNumber;
    private Date startDate;
    private Date endDate;
    private String periodDescription;
    private BigDecimal amount;
    private BigDecimal vatAmount;
    private BigDecimal totalAmount;
    
    public static class InvoiceDTOBuilder{
        private Long customerId;
        private Long invoiceId;
        private Long addressId;
        private BigDecimal amount;
        private BigDecimal vatAmount;
        private BigDecimal totalAmount;
        private Date startDate;
        private Date endDate;
        private Date dueDate;
        private Date invoiceDate;
        private String invoiceType;
        private String periodDescription;
        private int number;
        
        public InvoiceDTOBuilder() {
            
        }
        
        public InvoiceDTOBuilder(Invoice invoice) {
            this.customerId = invoice.getCustomer().getId();
            this.invoiceId = invoice.getId();
            this.addressId = invoice.getAddress().getId();
            this.amount = invoice.getAmount();
            this.vatAmount = invoice.getVat();
            this.totalAmount = invoice.getTotal();
            this.dueDate = invoice.getDueDate();
            this.invoiceDate = invoice.getCreationDate();
            this.invoiceType = invoice.getInvoiceType().getName();
            this.number = invoice.getNumber();
        }
        
        public InvoiceDTOBuilder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }
        
        public InvoiceDTOBuilder withInvoiceId(Long invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }
        
        public InvoiceDTOBuilder withAddress(Address address) {
            this.addressId = address.getId();
            return this;
        }
        
        public InvoiceDTOBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }
        
        public InvoiceDTOBuilder withVatAmount(BigDecimal vatAmount) {
            this.vatAmount = vatAmount;
            return this;
        }
        
        public InvoiceDTOBuilder withTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        
        public InvoiceDTOBuilder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public InvoiceDTOBuilder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public InvoiceDTOBuilder withInvoiceDate(Date invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }
        
        public InvoiceDTOBuilder withDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }
        
        public InvoiceDTOBuilder withPeriodDescription(String periodDescription) {
            this.periodDescription = periodDescription;
            return this;
        }
        
        public InvoiceDTOBuilder withInvoiceType(InvoiceType invoiceType) {
            this.invoiceType = invoiceType.getName();
            return this;
        }
        
        public InvoiceDTO build() {
            InvoiceDTO idto = new InvoiceDTO();
            idto.setAddressId(this.addressId);
            idto.setAmount(this.amount);
            idto.setCustomerId(this.customerId);
            idto.setEndDate(this.endDate);
            idto.setInvoiceDate(this.invoiceDate);
            idto.setInvoiceId(this.invoiceId);
            idto.setInvoiceNumber(this.number);
            idto.setInvoiceType(this.invoiceType);
            idto.setPaymentDueDate(this.dueDate);
            idto.setPeriodDescription(this.periodDescription);
            idto.setStartDate(this.startDate);
            idto.setTotalAmount(this.totalAmount);
            idto.setVatAmount(this.vatAmount);
            return idto;
        }
        
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int i) {
        this.invoiceNumber = i;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + invoiceNumber;
        result = prime * result + ((invoiceType == null) ? 0 : invoiceType.hashCode());
        result = prime * result + ((paymentDueDate == null) ? 0 : paymentDueDate.hashCode());
        result = prime * result + ((periodDescription == null) ? 0 : periodDescription.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
        result = prime * result + ((vatAmount == null) ? 0 : vatAmount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoiceDTO other = (InvoiceDTO) obj;
        if (addressId == null) {
            if (other.addressId != null)
                return false;
        } else if (!addressId.equals(other.addressId))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (invoiceDate == null) {
            if (other.invoiceDate != null)
                return false;
        } else if (!invoiceDate.equals(other.invoiceDate))
            return false;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        if (invoiceNumber != other.invoiceNumber)
            return false;
        if (invoiceType == null) {
            if (other.invoiceType != null)
                return false;
        } else if (!invoiceType.equals(other.invoiceType))
            return false;
        if (paymentDueDate == null) {
            if (other.paymentDueDate != null)
                return false;
        } else if (!paymentDueDate.equals(other.paymentDueDate))
            return false;
        if (periodDescription == null) {
            if (other.periodDescription != null)
                return false;
        } else if (!periodDescription.equals(other.periodDescription))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        } else if (!totalAmount.equals(other.totalAmount))
            return false;
        if (vatAmount == null) {
            if (other.vatAmount != null)
                return false;
        } else if (!vatAmount.equals(other.vatAmount))
            return false;
        return true;
    }
    
    
}
