/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.Creation;
import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
import edu.iit.sat.itmd4515.sprakas1.fp.Perfumer;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.CreationService;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.IngredientService;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.PerfumerService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@SessionScoped
public class CreationBean extends AbstractJSFBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CreationBean.class.getName());

    private Creation creation;
    private Ingredient ingredient;
    private String creationName;
    private String category;
    private Map<Ingredient, Integer> compositionMap;
    private List<Ingredient> allIngredients;
    private Integer quantity;
    private Integer addQuantity;
    @Inject
    private LoginBean loginBean;
    @EJB
    private IngredientService ingredientService;
    @EJB
    private CreationService creationService;
    @EJB
    private PerfumerService perfumerService;

    @PostConstruct
    private void postConstruct() {
        LOG.info("Creation Bean is Instanciated");
        super.postContruct();
        creation = new Creation();
        this.allIngredients = ingredientService.findAll();
    }

    public String doUpdate(Creation c) {
        LOG.info("Loading manageCreation page");
        this.creation = c;
//        flash.put("creation", this.creation);
        this.compositionMap = this.creation.getComposition();
        this.allIngredients = ingredientService.findAll();
        updateIngredientList();
        return this.loginBean.getPortalPathByRole("/manageCreations.xhtml");
    }

    private void updateIngredientList() {
        List<Ingredient> updatedList = new ArrayList<>();
        for (int i = 0; i < allIngredients.size(); i++) {
            if (!compositionMap.containsKey(allIngredients.get(i))) {
                LOG.log(Level.INFO, "This Ingredient is available in the list{0}", allIngredients.get(i).toString());
                updatedList.add(allIngredients.get(i));
            }
        }
        this.allIngredients = updatedList;
    }

    public String editCreation(Ingredient ingredient, Integer q) {
        LOG.log(Level.INFO, "Edit Compositon function{0}", ingredient.toString());
        LOG.log(Level.INFO, "Edit Compositon Quantity{0}", this.quantity);
        creationService.doUpdate(creation, ingredient, quantity);
        this.quantity = null;
        this.creation = creationService.getCreationById(creation.getId());
        this.compositionMap = this.creation.getComposition();
        this.allIngredients = ingredientService.findAll();
        updateIngredientList();
        return this.loginBean.getPortalPathByRole("/manageCreations.xhtml");
    }

    public String addIngredient(Ingredient ingredient) {
        LOG.log(Level.INFO, "Creation :  {0}", this.creation.toString());
        LOG.log(Level.INFO, "Adding Ingredient : {0}", ingredient.toString());
        LOG.log(Level.INFO, "Quantity :  {0}", this.addQuantity);
        creationService.addIngredient(this.creation, ingredient, addQuantity);
        this.addQuantity = null;
        this.creation = creationService.getCreationById(creation.getId());
        this.compositionMap = this.creation.getComposition();
        this.allIngredients = ingredientService.findAll();
        updateIngredientList();
        return loginBean.getPortalPathByRole("/manageCreations.xhtml");
    }

    public String deleteIngredient(Ingredient ingredient) {
        LOG.log(Level.INFO, "Creation :  {0}", this.creation.toString());
        LOG.log(Level.INFO, "Deleting Ingredient : {0}", ingredient.toString());
        creationService.removeIngredient(creation, ingredient);
        this.creation = creationService.getCreationById(creation.getId());
        this.compositionMap = this.creation.getComposition();
        this.allIngredients = ingredientService.findAll();
        updateIngredientList();
        return loginBean.getPortalPathByRole("/manageCreations.xhtml");
    }

    public String createCreation() {
        LOG.log(Level.INFO, "Creating Creation : {0}", this.creationName);
        LOG.log(Level.INFO, "Creating Category : {0}", this.category);
        LOG.log(Level.INFO,"Creation Name: ",this.loginBean.getRemoteUser());
        Creation newCreation = new Creation(creationName, category);
        Perfumer perfumer = perfumerService.findPerfumerByUserName(loginBean.getRemoteUser());
        perfumer.addCretion(newCreation);
        newCreation.setPerfumer(perfumer);
        perfumerService.updatePerfumer(perfumer, newCreation);
        this.creationName = null;
        this.category = null;
        return loginBean.getPortalPathByRole("/welcome.xhtml");
    }

    private boolean checkQuantity() {
        if (this.quantity == null || this.quantity == 0) {
            return false;
        }
        return true;
    }

    public Creation getCreation() {
        return creation;
    }

    public void setCreation(Creation creation) {
        this.creation = creation;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Map<Ingredient, Integer> getCompositionMap() {
        return compositionMap;
    }

    public void setCompositionMap(Map<Ingredient, Integer> compositionMap) {
        this.compositionMap = compositionMap;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAddQuantity() {
        return addQuantity;
    }

    public void setAddQuantity(Integer addQuantity) {
        this.addQuantity = addQuantity;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
