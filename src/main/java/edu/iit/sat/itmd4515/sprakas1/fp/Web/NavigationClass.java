/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sharan
 */
@ManagedBean(name = "navigationClass", eager = true)
@RequestScoped
public class NavigationClass {

    public String doLogout(){
        return "/login.xhtml";
    }
}
