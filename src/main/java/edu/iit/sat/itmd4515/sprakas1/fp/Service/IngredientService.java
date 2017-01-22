/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
import edu.iit.sat.itmd4515.sprakas1.fp.Supplier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class IngredientService extends AbstractService<Ingredient> {

    private static final Logger LOG = Logger.getLogger(IngredientService.class.getName());

    @EJB
    private SupplierService supplierService;

    public IngredientService() {
        super(Ingredient.class);
    }

    @Override
    public List<Ingredient> findAll() {
        return this.getEntityManager().createNamedQuery("Ingredient.findAll", Ingredient.class).getResultList();
    }

    public void doCreate(Ingredient ingredient, Supplier supplier) {

        supplier = getEntityManager().getReference(supplier.getClass(), supplier.getId());

        supplier.getIngredientList().add(ingredient);
//        supplier.setIngredientList(supplierService.findAllIngredientsForSupplier(supplier.getId()));
//        supplier.addIngredient(ingredient);
        LOG.log(Level.INFO, "Supplier Details{0}", supplier.toString());
        ingredient.addSupplier(supplier);
        getEntityManager().persist(ingredient);
        for (int i = 0; i < supplier.getIngredientList().size(); i++) {
            LOG.log(Level.INFO, "Ingredients for this supplier is as below {0}", supplier.getIngredientList().get(i).toString());
        }
        getEntityManager().merge(supplier);
    }

    public void updateIngredient(Ingredient i, Supplier s) {

        i = getEntityManager().getReference(Ingredient.class, i.getId());
        s = getEntityManager().getReference(Supplier.class, s.getId());

        i.getSupplierList().remove(s);
        s.getIngredientList().remove(i);

        getEntityManager().merge(i);
        getEntityManager().merge(s);
    }

    public boolean addSupplierToList(Ingredient ingredient, Supplier supplier) {

        ingredient = getEntityManager().getReference(Ingredient.class, ingredient.getId());
        supplier = getEntityManager().getReference(Supplier.class, supplier.getId());

        if (!ingredient.getSupplierList().contains(supplier)) {
            ingredient.getSupplierList().add(supplier);
            supplier.getIngredientList().add(ingredient);
            getEntityManager().merge(ingredient);
            getEntityManager().merge(supplier);
            return true;
        }
        return false;
    }

}
