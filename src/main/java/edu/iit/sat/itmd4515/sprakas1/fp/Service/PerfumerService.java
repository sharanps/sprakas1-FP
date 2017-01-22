/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.Creation;
import edu.iit.sat.itmd4515.sprakas1.fp.Perfumer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class PerfumerService extends AbstractService<Perfumer> {

    private Perfumer perfumer;

    public PerfumerService() {
        super(Perfumer.class);
    }

    @Override
    public List<Perfumer> findAll() {
        return getEntityManager().createNamedQuery("findAllPerfumers").getResultList();
    }

    public List<Creation> findAllCreations(String userName) {
        perfumer = getEntityManager().createNamedQuery("Perfumer.findByUserName", Perfumer.class).
                setParameter("userName", userName).getSingleResult();
        return perfumer.getMyCreations();
    }
    
    public Perfumer findPerfumerByUserName(String userName){
        return getEntityManager().createNamedQuery("Perfumer.findByUserName", Perfumer.class).
                setParameter("userName", userName).getSingleResult();
    }
    
    public void updatePerfumer(Perfumer perfumer, Creation creation){
        perfumer = getEntityManager().getReference(Perfumer.class, perfumer.getId());
        
        perfumer.addCretion(creation);
        getEntityManager().persist(creation);
        getEntityManager().merge(perfumer);
        
    }

}
