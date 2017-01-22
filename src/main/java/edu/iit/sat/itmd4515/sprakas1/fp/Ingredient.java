/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Ingredient base class to be added to a perfume composition
 *
 * @author sharan
 */
@Entity
@Table(name = "Ingredient")
@NamedQueries({
    @NamedQuery(name = "findByID", query = "select i from Ingredient i where i.id= :fID"),
    @NamedQuery(name = "findByIngredientName", query = "select i from Ingredient i where i.ingredientName = :fName"),
    @NamedQuery(name = "Ingredient.findAll", query = "select i from Ingredient i"),
    @NamedQuery(name = "Ingredient.findSupplierByIngredient", query = "select s from supplier s join s.ingredientList i where i.id = :ingredientID")
})
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String ingredientName;

    @Column(nullable = false, name = "price")
    private BigInteger price;

    @ManyToMany
    @JoinTable(name = "Ingredient_Supplier",
            joinColumns = @JoinColumn(name = "ingredientID"),
            inverseJoinColumns = @JoinColumn(name = "supplierID"))
    @OrderBy("id")
    private List<Supplier> supplierList = new ArrayList<>();

    /**
     * Method to add supplier for a ingredient
     *
     * @param supplier
     */
    public void addSupplier(Supplier supplier) {
        if (!supplierList.contains(supplier)) {
            supplierList.add(supplier);
        }
    }
    // ======================================
    // =            Constructors            =
    // ======================================

    public Ingredient() {
    }

    public Ingredient(String ingredientName, BigInteger price) {
        this.ingredientName = ingredientName;
        this.price = price;
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

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + id + ", ingredientName=" + ingredientName + ", price=" + price + '}';
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Ingredient i = (Ingredient) object;
            if ((this.ingredientName == null ? i.getIngredientName() == null : this.ingredientName.equals(i.getIngredientName())) && this.price == i.getPrice()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.ingredientName.hashCode();
        hash = 7 * hash + this.price.hashCode();
        return hash;
    }
}
