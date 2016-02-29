package com.gem.nrserver.service.dto;

/**
 * Created by qsoft on 2/29/16.
 */
public class CompanyDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private int  numOfStores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfStores() {
        return numOfStores;
    }

    public void setNumOfStores(int numOfStores) {
        this.numOfStores = numOfStores;
    }
}
