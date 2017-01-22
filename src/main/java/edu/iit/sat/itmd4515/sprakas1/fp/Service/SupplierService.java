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
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class SupplierService extends AbstractService<Supplier>{

    private static final Logger LOG = Logger.getLogger(SupplierService.class.getName());

    public SupplierService() {
        super(Supplier.class);
    }

    @Override
    public List<Supplier> findAll() {
        return this.getEntityManager().createNamedQuery("Supplier.findAll", Supplier.class).getResultList();
    }
    
    public List<Supplier> findSupplierByIngredient(Long ingredientId){
        return this.getEntityManager().createNamedQuery("Supplier.findSupplierByIngredient", Supplier.class).setParameter("ingredientID", ingredientId).getResultList();
    }
 
    public Supplier findBySupplierID(Long supplierId){
        return this.getEntityManager().createNamedQuery("Supplier.findById", Supplier.class).setParameter("supplierID", supplierId).getSingleResult();
    }
    public List<Ingredient> findAllIngredientsForSupplier(Long supplierID){
        return this.getEntityManager().createNamedQuery("Supplier.findAllIngredients", Ingredient.class).setParameter("supplierID", supplierID).getResultList();
    }
    
    public void updateSupplier(Supplier s){
        s= this.getEntityManager().getReference(Supplier.class, s.getId());
        s.setIngredientList(this.findAllIngredientsForSupplier(s.getId()));
        getEntityManager().merge(s);
    }

    @Override
    public void delete(Supplier entity) {
        entity = getEntityManager().getReference(Supplier.class, entity.getId());
        entity.getIngredientList();
        for(int i=0; i<entity.getIngredientList().size();i++){
            LOG.info(entity.getIngredientList().get(i).toString());
        }
        LOG.log(Level.INFO, "Deleting Supplier{0} Supplier {1}", new Object[]{this.getClass(), entity.toString()});
        getEntityManager().remove(entity);
    }
}
