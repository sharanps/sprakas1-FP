/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.Creation;
import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
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
public class CreationService extends AbstractService<Creation> {

    private static final Logger LOG = Logger.getLogger(CreationService.class.getName());

    public CreationService() {
        super(Creation.class);
    }

    @Override
    public List<Creation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void doUpdate(Creation c, Ingredient i, Integer quantity) {
        LOG.log(Level.INFO, "Persisting  Creation {0}", c.toString());
        LOG.log(Level.INFO, "Ingredient is {0}", i.toString());
        LOG.log(Level.INFO, "Quantity is {0}", quantity);
//        for (Map.Entry<Ingredient, Integer> map : c.getComposition().entrySet()) {
//            LOG.info("Composition Key : " + map.getKey().toString());
//            LOG.info("Composition Value : " + map.getValue());
//        }
        c = getEntityManager().getReference(Creation.class, c.getId());
        c.addComposition(i, quantity);
        getEntityManager().merge(c);
    }

    public void addIngredient(Creation creation, Ingredient i, Integer quantity) {
        creation = getEntityManager().getReference(Creation.class, creation.getId());
        LOG.log(Level.INFO, "Persisting  Creation {0}", creation.toString());
        creation.addComposition(i, quantity);
        getEntityManager().merge(creation);
    }
    
    public void removeIngredient(Creation creation, Ingredient ingredient){
        creation = getEntityManager().getReference(Creation.class, creation.getId());
        creation.removeComposition(ingredient);
        getEntityManager().merge(creation);
    }

    public Creation getCreationById(Long id) {
        return this.getEntityManager().createNamedQuery("creation.findCreationById", Creation.class).setParameter("id", id).getSingleResult();
    }
}
