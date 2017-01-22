/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sharan
 * @param <T>
 */
public abstract class AbstractService<T> {

    @PersistenceContext(unitName = "sprakas1PU")
    private EntityManager em;

    private final Class<T> entityClass;
    
    protected AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public abstract List<T> findAll();

    public void update(T entity) {
        em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    public void deleteById(Object id) {
        em.remove(em.getReference(entityClass, id));
    }
}
