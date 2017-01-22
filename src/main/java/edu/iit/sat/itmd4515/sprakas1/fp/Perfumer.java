/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Perfumer class used to hold compositions and basic perfumer details
 *
 * @author sharan
 */
@Entity
@Table(name = "perfumer")
@NamedQueries({
    @NamedQuery(name = "findByFirstName", query = "select p from Perfumer p where p.firstName = :fname"),
    @NamedQuery(name = "Perfumer.findByUserName", query = "select p from Perfumer p where p.userName = :userName"),
    @NamedQuery(name = "findAllPerfumers",query = "select p from Perfumer p")    
})
public class Perfumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfumer_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String city;

    private String email;
    
    private String userName;

    @OneToMany(mappedBy = "perfumer", cascade = CascadeType.ALL)
    private List<Creation> myCreations = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Address_Id")
    private Address address;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Perfumer() {
    }

    public Perfumer(String firstName, String lastName, String city, String email, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
        this.userName = userName;
    }



  // ======================================
  // =          Getters & Setters         =
  // ======================================

    public List<Creation> getMyCreations() {
        return myCreations;
    }


    public void addCretion(Creation creation) {
        if (!myCreations.contains(creation)) {
            myCreations.add(creation);
        }
        creation.setPerfumer(this);
    }


    public void removeCreation(Creation creation) {

        if (myCreations.contains(creation)) {
            myCreations.remove(creation);
        }
    }

   
    public Long getId() {
        return id;
    }

  
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Perfumer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", email=" + email + ", myCreations=" + myCreations + '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
