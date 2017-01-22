/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.IngredientService;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.SupplierService;
import edu.iit.sat.itmd4515.sprakas1.fp.Supplier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@RequestScoped
public class IngredientBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(IngredientService.class.getName());

    private Ingredient ingredient;

    private List<Ingredient> allIngredients;

    private List<Supplier> supplierList;

    private Supplier supplier;

    @Inject
    private LoginBean loginBean;

    @EJB
    private IngredientService ingredientService;

    @EJB
    private SupplierService supplierService;

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
        ingredient = new Ingredient();
        supplier = new Supplier();
        getIngredients();
    }

    public String doUpdate(Ingredient ingredient) {
        this.ingredient = ingredient;
        LOG.log(Level.INFO, "The Ingredient to update is {0}", ingredient.toString());
        LOG.log(Level.INFO, "Supplier for this ingredient{0}", this.supplier.toString());
        return this.loginBean.getPortalPathByRole("/manageIngredient.xhtml");
    }

    public String executeSave() {
        if (this.ingredient.getId() != null) {
            LOG.log(Level.INFO, "Updating the ingredient {0}", this.ingredient.toString());
            if (this.supplier.getId() == null) {
                ingredientService.update(ingredient);
                facesContext.addMessage(null, new FacesMessage("Ingredient Updated", "Ingredient Updated"));
                return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");
            }
            LOG.log(Level.INFO, "Supplier for this ingredient{0}", this.supplier.toString());
            if (ingredientService.addSupplierToList(ingredient, supplier)) {
                getIngredients();
                facesContext.addMessage(null, new FacesMessage("Ingredient Updated", "Ingredient Updated with supplier"));
                return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");
            }
            getIngredients();
            facesContext.addMessage(null, new FacesMessage("Ingredient Created", "Ingredient Supplier Already Exists"));
            return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");

        } else if (this.supplier.getId() == null) {
            ingredientService.create(ingredient);
            getIngredients();
            facesContext.addMessage(null, new FacesMessage("Ingredient Creation", "Ingredient Created"));
            return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");
        } else {
            LOG.log(Level.INFO, "Creating new Ingredient{0}", this.ingredient.toString());
            supplier = supplierService.findBySupplierID(this.supplier.getId());
            LOG.log(Level.INFO, "Supplier for this ingredient{0}", this.supplier.toString());
            ingredientService.doCreate(ingredient, supplier);
            getIngredients();
            facesContext.addMessage(null, new FacesMessage("Ingredient Created", "Ingredient Created"));
            return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");
        }
    }

    private void getIngredients() {
        this.allIngredients = ingredientService.findAll();
        for (int i = 0; i < allIngredients.size(); i++) {
            this.allIngredients.get(i).getSupplierList();
            for (int j = 0; j < this.allIngredients.get(i).getSupplierList().size(); j++) {
                LOG.log(Level.INFO, "{0}Ingredient Supplier list ", this.allIngredients.get(i).getSupplierList().get(j).toString());
            }
        }
    }

    public String deleteSupplier(Ingredient i, Supplier s) {

        LOG.log(Level.INFO, "Delete Supplier : {0}", s.toString());
        LOG.log(Level.INFO, "For Ingredient :{0}", i.toString());

        ingredientService.updateIngredient(i, s);
        getIngredients();
        return loginBean.getPortalPathByRole("/viewAllIngredients.xhtml");
    }

    public List<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    public void setAllIngredients(List<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    private List<Supplier> allSupplierByIngredient() {
        return supplierService.findSupplierByIngredient(this.ingredient.getId());
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
