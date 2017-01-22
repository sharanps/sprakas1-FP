/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class AdminService extends AbstractService<Admin> {

    public AdminService() {
        super(Admin.class);
    }

    @Override
    public List<Admin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
