/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.User;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.UserService;
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
public class UserBean extends AbstractJSFBean{

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    
    @Inject
    private LoginBean loginBean;
    @EJB
    private UserService userService;
    
    private User user;
    
    @PostConstruct
    private void  postConstruct(){
        super.postContruct();
        this.user = userService.findUser(loginBean.getRemoteUser());
    }
    
    public String updatePassoword(){
        LOG.log(Level.INFO, "Updating User Password{0}", this.user.toString());
        facesContext.addMessage(null, new FacesMessage("Password Updated", "Password Updated"));
        userService.updatePassword(user, user.getPassword());
        return this.loginBean.getPortalPathByRole("/welcome.xhtml");
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
