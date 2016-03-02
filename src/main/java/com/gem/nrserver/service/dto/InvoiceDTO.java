package com.gem.nrserver.service.dto;

import com.gem.nrserver.persistent.model.Invoice;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Invoice.Status status;
    private long storeId;
    private String customerId;
    private Date createdDate;
    private Date updatedDate;
    private List<InvoiceDetailDTO> invoiceDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice.Status getStatus() {
        return status;
    }

    public void setStatus(Invoice.Status status) {
        this.status = status;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<InvoiceDetailDTO> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<InvoiceDetailDTO> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
