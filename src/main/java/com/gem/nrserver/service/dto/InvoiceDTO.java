package com.gem.nrserver.service.dto;

import com.gem.nrserver.persistent.model.Invoice;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {
    private Long id;
    private Invoice.Status status;
    private StoreDTO store;
    private UserDTO customer;
    private Date createdDate;
    private Date updatedDate;
    private List<Detail> invoiceDetails;

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

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
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

    public List<Detail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(List<Detail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }


    public static class Detail {
        private ProductDTO product;
        private int quantity;
        private double priceEach;

        public ProductDTO getProduct() {
            return product;
        }

        public void setProduct(ProductDTO product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPriceEach() {
            return priceEach;
        }

        public void setPriceEach(double priceEach) {
            this.priceEach = priceEach;
        }
    }
}
