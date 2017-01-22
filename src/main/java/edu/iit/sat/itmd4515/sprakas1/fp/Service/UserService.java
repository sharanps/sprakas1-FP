/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.User;
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
public class UserService extends AbstractService<User> {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());
    
    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User findUser(String userName) {
        return this.getEntityManager().createNamedQuery("User.findByName", User.class).
                setParameter("userName", userName).getSingleResult();
    }
    
    public void updatePassword(User user, String password){
        LOG.log(Level.INFO, "Update function called{0}", user.getUserName());
        user = getEntityManager().getReference(User.class, user.getUserName());
        user.setPassword(password);
        getEntityManager().merge(user);
    }

}
