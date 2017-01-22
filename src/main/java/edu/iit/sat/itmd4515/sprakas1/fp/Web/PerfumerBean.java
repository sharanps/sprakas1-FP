/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.Perfumer;
import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.Group;
import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.User;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.GroupService;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.PerfumerService;
import edu.iit.sat.itmd4515.sprakas1.fp.Service.UserService;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class PerfumerBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(PerfumerBean.class.getName());

    @EJB 
    private GroupService groupService;
    
    @EJB
    private UserService userService;
    
    @EJB
    private PerfumerService perfumerService;
    
    private Perfumer perfumer;

    private String firstName;

    private String lastName;

    private String city;

    private String email;

    private User user;

    private Group group;
    
    private String userName;

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
    }

    public void createPerfumer() {
        this.group = groupService.findByGroupName("Perfumer");
        this.user = new User(userName, "password");
        group.addUser(user);
        user.addGroup(group);
        user.setEnabled(Boolean.TRUE);
        userService.create(user);
        perfumer = new Perfumer(firstName, lastName, city, email,userName);
        perfumerService.create(perfumer);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
