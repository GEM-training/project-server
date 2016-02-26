package com.gem.nrserver.persistent.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by qsoft on 2/22/16.
 */
@Entity
@Table(name = "product_store")
@IdClass(ProductStore.Id.class)
public class ProductStore {

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "price")
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Embeddable
    public static class Id implements Serializable {
        public Product product;
        public Store store;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (!product.equals(id.product)) return false;
            return store.equals(id.store);

        }

        @Override
        public int hashCode() {
            int result = product.hashCode();
            result = 31 * result + store.hashCode();
            return result;
        }
    }

}
