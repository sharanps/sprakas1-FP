/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sprakas1.fp.Service;

import edu.iit.sat.itmd4515.sprakas1.fp.SecurityGroup.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author sharan
 */
@Named
@Stateless
public class GroupService extends AbstractService<Group> {

    public GroupService() {
        super(Group.class);
    }

    @Override
    public List<Group> findAll() {
        return this.getEntityManager().createNamedQuery("Group.findAll").getResultList();
    }

    public Group findByGroupName(String groupName) {
        return this.getEntityManager().createNamedQuery("Group.findByGroupName", Group.class)
                .setParameter("groupName", groupName).getSingleResult();
    }

}
