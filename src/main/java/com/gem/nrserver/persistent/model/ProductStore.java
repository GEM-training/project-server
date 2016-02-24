package com.gem.nrserver.persistent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qsoft on 2/22/16.
 */
@Entity
@Table(name = "product_store")
public class ProductStore {
    @Id
    @SequenceGenerator(name = "product_store_id_seq", sequenceName = "product_store_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_store_id_seq")


    @Column(name = "id")
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
