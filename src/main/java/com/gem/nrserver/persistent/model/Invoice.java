package com.gem.nrserver.persistent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by qsoft on 2/22/16.
 */
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_id_seq", sequenceName = "invoice_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "username")
    private User customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private Set<InvoiceDetail> invoiceDetails;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

}
