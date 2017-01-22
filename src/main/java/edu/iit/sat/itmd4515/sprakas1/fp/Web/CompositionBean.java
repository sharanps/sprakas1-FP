/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.Creation;
import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.CreationService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@RequestScoped
public class CompositionBean extends AbstractJSFBean{
    
    @Inject
    private LoginBean loginBean;
    
    @EJB 
    private CreationService creationService;
    
    private Creation creation;
    
    private Ingredient ingredient;
    
    @PostConstruct
    protected void postContruct() {
        super.postContruct(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
