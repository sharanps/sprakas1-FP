/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.Address;
import edu.iit.sat.itmd4515.sprakas1.fp.Admin;
import edu.iit.sat.itmd4515.sprakas1.fp.Creation;
import edu.iit.sat.itmd4515.sprakas1.fp.Ingredient;
import edu.iit.sat.itmd4515.sprakas1.fp.Perfumer;
import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.Group;
import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.User;
import edu.iit.sat.itmd4515.sprakas1.fp.Supplier;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author sharan
 */
@Singleton
@Startup
public class DatabaseLoader {
    
    @EJB
    private AddressService addressService;
    
    @EJB
    private AdminService adminService;
            
    @EJB
    private CreationService creationService;
    
    @EJB
    private IngredientService ingredientService;
    
    @EJB
    private PerfumerService perfumerService;
    
    @EJB
    private SupplierService supplierService;
    
    @EJB
    private GroupService groupService;
    
    @EJB
    private UserService userService;
           
    
    @PostConstruct
    private void loadDatabase(){
        
        Group admin = new Group("Admin", "Administrative Group");
        Group perfumerGroup = new Group("Perfumer", "Perfumer Group");
        
        User sharan = new User("Divya", "Ka");
        User ad = new User("admin", "admin");
        
        sharan.addGroup(perfumerGroup);
        ad.addGroup(admin);
        
        Perfumer perfumer = new Perfumer("Sharan", "Prakash", "Bangalore", "shara@shara.com","sharan");
        Perfumer per = new Perfumer("James", "Davidoff", "France", "david@doff.com","james");
        
        Admin admin1 = new Admin("Admin", "Operations", "admin@admin.co");
        
        Address a = new Address("2001 S Michigan", "Chicago", "60616");
        
        Address a1 = new Address("1 S Michigan", "Chicago", "60616");
        perfumer.setAddress(a);
        admin1.setAddress(a1);

        Creation creation1 = new Creation("Screwback", "Perfume");
        Creation creation = new Creation("Coolwater", "Perfume");

        Supplier supplier = new Supplier("ABC", "Down Town, Chicago", "abc@abc.com");
        Supplier supplier1 = new Supplier("XYZ", "Down Town, NYC", "xyz@xyz.com");
        
        Ingredient i1 = new Ingredient("Ald C 11", new BigInteger("1000"));
        Ingredient i2 = new Ingredient("Ald C 12 MNA", new BigInteger("2000"));
        Ingredient i3 = new Ingredient("Ald C 12 Lauric", new BigInteger("3000"));
        Ingredient i4 = new Ingredient("Lavender", new BigInteger("2500"));
        Ingredient i5 = new Ingredient("Bergomot", new BigInteger("1500"));

        supplier.addIngredient(i1);
        supplier.addIngredient(i2);
        supplier.addIngredient(i3);
        supplier.addIngredient(i4);
        
        supplier1.addIngredient(i1);
        supplier1.addIngredient(i2);
        supplier1.addIngredient(i5);
        
        i1.addSupplier(supplier);
        i2.addSupplier(supplier);
        i3.addSupplier(supplier);
        
        i1.addSupplier(supplier1);
        i2.addSupplier(supplier1);
        
        creation.addComposition(i1, 100);
        creation.addComposition(i2, 200);
        creation.addComposition(i3, 300);

        creation1.addComposition(i1, 10);
        creation1.addComposition(i2, 20);
        creation1.addComposition(i3, 30);

        perfumer.addCretion(creation);
        per.addCretion(creation1);

        groupService.create(admin);
        groupService.create(perfumerGroup);
        userService.create(ad);
        userService.create(sharan);
        
        supplierService.create(supplier);        
        supplierService.create(supplier1);
        
        ingredientService.create(i1);
        ingredientService.create(i2);
        ingredientService.create(i3);
        ingredientService.create(i4);
        ingredientService.create(i5);
        
        creationService.create(creation);
        creationService.create(creation1);
        addressService.create(a1);
        addressService.create(a);
        adminService.create(admin1);
        perfumerService.create(per);
        perfumerService.create(perfumer);
        
    }
}
