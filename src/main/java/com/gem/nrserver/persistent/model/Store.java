package com.gem.nrserver.persistent.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @SequenceGenerator(name = "store_id_seq", sequenceName = "store_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Inventory> inventories;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<User> staffs;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "store")
    private Set<ProductStore> productStores;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public long getId() {
        return id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Set<User> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<User> staffs) {
        this.staffs = staffs;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<ProductStore> getProductStores() {
        return productStores;
    }

    public void setProductStores(Set<ProductStore> productStores) {
        this.productStores = productStores;
    }
}

