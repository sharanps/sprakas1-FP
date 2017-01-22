/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Class to store address of Perfumer and Admin users
 *
 * @author sharan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findByCity", query = "select a from Address a where a.city = :cName")
})
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String city;

    private String zipcode;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private Perfumer perfumer;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Address(String address, String city, String zipcode) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Address() {
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Perfumer getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(Perfumer perfumer) {
        this.perfumer = perfumer;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", address=" + address + ", city=" + city + ", zipcode=" + zipcode + ", perfumer=" + perfumer + '}';
    }

}
