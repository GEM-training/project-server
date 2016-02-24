package com.gem.nrserver.persistent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by qsoft on 2/22/16.
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch =  FetchType.LAZY)
    Set<Invoice> invoices;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {

        return id;
    }



    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }


}
