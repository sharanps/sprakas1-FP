/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Creation class to hold the composition
 *
 * @author sharan
 */
@Entity
@Table(name = "creation")
@NamedQueries({
    @NamedQuery(name = "findByName", query = "select c from Creation c where c.creationName = :cName"),
    @NamedQuery(name = "creation.findCreationById", query = "select c from Creation c where c.id = :id")
})
public class Creation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creation_id")
    private Long id;

    @Column(nullable = false, name = "creation_name")
    private String creationName;

    @Column(nullable = false, name = "category")
    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "perfumer_id")
    private Perfumer perfumer;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Composition",
            joinColumns = @JoinColumn(name = "Creation_id"))
    @MapKeyJoinColumn(name = "Ingredient_id", referencedColumnName = "ingredientName")
    @Column(name = "Quantity_In_grams")
    private Map<Ingredient, Integer> composition = new HashMap<>();

    // ======================================
    // =            Constructors            =
    // ======================================
    public Creation() {

    }

    public Creation(String creationName, String category) {
        this.creationName = creationName;
        this.category = category;
        this.creationDate = new Date();
        this.lastUpdated = new Date();
    }

    /**
     * Method add composition with quantities
     *
     * @param i - Ingredient to add be added
     * @param quantity - Quantity of the ingredient in the composition
     */
    public void addComposition(Ingredient i, Integer quantity) {

//        if (composition.containsKey(i)) {
//            composition.replace(i, quantity);
//        } else {
        composition.put(i, quantity);
//        }
        this.lastUpdated = new Date();
    }

    public void removeComposition(Ingredient ingredient) {
        this.composition.remove(ingredient);
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

    public Perfumer getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(Perfumer perfumer) {
        this.perfumer = perfumer;
        this.lastUpdated = new Date();
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
        this.lastUpdated = new Date();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.lastUpdated = new Date();
    }

    @Override
    public String toString() {
        return "Creations{" + "id=" + id + ", creationName=" + creationName + ", category=" + category + '}';
    }

    public Map<Ingredient, Integer> getComposition() {
        return composition;
    }

    public void setComposition(Map<Ingredient, Integer> composition) {
        this.composition = composition;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Creation i = (Creation) object;
            if ((this.creationName == null ? i.creationName == null : this.creationName.equals(i.getCreationName())) && this.creationDate == i.getCreationDate()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.creationName.hashCode();
        hash = 7 * hash + this.creationDate.hashCode();
        return hash;
    }

}
