package com.gem.nrserver.persistent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "invoice_detail")
@IdClass(InvoiceDetail.Id.class)
public class InvoiceDetail {

    @javax.persistence.Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @javax.persistence.Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "quantity", nullable = false)
    private int quantity = 0;

    @Column(name = "price_each", nullable = false)
    private double priceEach = 0;

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Embeddable
    public static class Id implements Serializable {
        public Product product;
        public Invoice invoice;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return product.equals(id.product) && invoice.equals(id.invoice);

        }

        @Override
        public int hashCode() {
            int result = product.hashCode();
            result = 31 * result + invoice.hashCode();
            return result;
        }
    }
}
