/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sharan
 */
@Named
@RequestScoped
public class LoginBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    @NotNull(message = "You shall not pass without a password!")
    @Size(min = 5, message = "Password must be at least 5 characters in length.")
    private String password;
    @NotNull(message = "You shall not pass without a username!")
    private String username;

    public LoginBean() {
        super();
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    public boolean isAdmin() {
        return facesContext.getExternalContext().isUserInRole("admin");
    }

    public boolean isPerfumer() {
        return facesContext.getExternalContext().isUserInRole("perfumer");
    }

    // Fun: http://www.code-thrill.com/2012/08/stringbuilder-optimizations-demystified.html
    public String getPortalPathByRole(String path) {
        if (isAdmin()) {
            return "/admin" + path;// + FACES_REDIRECT;
        } else if (isPerfumer()) {
            return "/perfumer" + path;// + FACES_REDIRECT;
        } else {
            return path;// + FACES_REDIRECT;
        }
    }

    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.login(username, password);
            LOG.log(Level.INFO, "The User currently logged in : {0}", username);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "There has been a problem invoking HttpServletRequest.login", ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login", "Detail: You made a bad login!"));
            // return the user to the login page with an error message
            return "/error.xhtml";
        }

        // send user to welcome page
        return getPortalPathByRole("/welcome.xhtml");
    }

    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, "There has been a problem invoking HttpServletRequest.logout", ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout", "Bad Logout"));
            // return the user to the login page with an error message
            return "/error.xhtml";
        }

        // return the user to the login page
        return "/login.xhtml";
    }

    public String changePassword() {
        return getPortalPathByRole("/changePassword.xhtml");
    }

    public String viewIngredients() {
        return getPortalPathByRole("/viewAllIngredients.xtml");
    }

    public String goHome() {
        return getPortalPathByRole("/welcome.xhtml");
    }

    public String createIngredient() {
        return getPortalPathByRole("/manageIngredient.xhmtl");
    }

    public String viewSuppliers() {
        return getPortalPathByRole("/viewSuppliers.xhmtl");
    }

    public String manageSuppliers() {
        return getPortalPathByRole("/manageSuppliers.xhmtl");
    }

    public String goToEditComposition() {
        return getPortalPathByRole("/editComposition.xhmtl");
    }

}
