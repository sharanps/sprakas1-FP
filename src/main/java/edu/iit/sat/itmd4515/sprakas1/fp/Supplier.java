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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;

/**
 * Supplier class to hold basic supplier details for ingredients of the
 * composition
 *
 * @author sharan
 */
@Entity(name = "supplier")
@NamedQueries({
    @NamedQuery(name = "searchBySupplierName", query = "select s from supplier s where s.supplierName = :fName"),
    @NamedQuery(name = "Supplier.findSupplierByIngredient", query = "select s from supplier s join s.ingredientList i where i.id = :ingredientID"),
    @NamedQuery(name = "Supplier.findAll", query = "select s from supplier s"),
    @NamedQuery(name = "Supplier.findById", query = "select s from supplier s where s.id=:supplierID"),
    @NamedQuery(name = "Supplier.findAllIngredients", query = "select c1 from Ingredient c1 join c1.supplierList c2 where c2.id = :supplierID")
})
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String supplierName;

    @Column(nullable = false)
    private String supplierAddress;

    @Column(nullable = false)
    private String supplierEmail;

    @ManyToMany(mappedBy = "supplierList", cascade = CascadeType.PERSIST)
    @OrderBy("id")
    private List<Ingredient> ingredientList = new ArrayList<>();

    /**
     * Method to add Ingredients supplied by the supplier
     *
     * @param i
     */
    public void addIngredient(Ingredient i) {
        if (!ingredientList.contains(i)) {
            ingredientList.add(i);
        }
    }

    // ======================================
    // =            Constructors            =
    // ======================================
    public Supplier() {
    }

    public Supplier(String supplierName, String supplierAddress, String supplierEmail) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierEmail = supplierEmail;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", supplierName=" + supplierName + ", supplierAddress=" + supplierAddress + ", supplierEmail=" + supplierEmail + ", ingredientList=" + ingredientList + '}';
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Supplier s = (Supplier) object;
            if(this.supplierName == s.getSupplierName() && this.supplierEmail == s.getSupplierEmail()){
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.supplierName.hashCode();
        hash = 7 * hash + this.supplierEmail.hashCode();
        return hash;
    }

}
