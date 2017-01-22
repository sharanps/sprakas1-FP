/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Web;

import edu.iit.sat.itmd4515.sprakas1.fp.Service.SupplierService;
import edu.iit.sat.itmd4515.sprakas1.fp.Supplier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SupplierBean extends AbstractJSFBean {

    private static final Logger LOG = Logger.getLogger(SupplierBean.class.getName());

    @EJB
    private SupplierService supplierService;

    @Inject
    private LoginBean loginBean;

    private Supplier supplier;

    private List<Supplier> supplierList;

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
        this.supplier = new Supplier();
        this.supplierList = supplierService.findAll();
    }

    public String executeSave() {

        if (this.supplier.getId() != null) {
            LOG.log(Level.INFO, "Performing Supplier Update for {0}", supplier.toString());
            supplierService.update(supplier);
            refreshSuppliers();
            return loginBean.getPortalPathByRole("/viewSuppliers.xhtml");
        } else {
            LOG.log(Level.INFO, "Performin Supplier Creation for{0}", supplier.toString());
            supplierService.create(supplier);
            refreshSuppliers();
            return loginBean.getPortalPathByRole("/viewSuppliers.xhtml");
        }
    }

    public String doUpdate(Supplier supplier) {
        this.supplier = supplier;
        supplierService.updateSupplier(supplier);
        refreshSuppliers();
        return loginBean.getPortalPathByRole("/manageSuppliers.xhtml");
    }

    public String doDelete(Supplier supplier) {
        this.supplier = supplier;
        supplierService.delete(supplier);
        LOG.log(Level.INFO, "Deleting Supplier {0}", supplier.toString());
        refreshSuppliers();
        return loginBean.getPortalPathByRole("/viewSuppliers.xhtml");
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    private void refreshSuppliers() {
        this.supplierList = supplierService.findAll();
    }

}
