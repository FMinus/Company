/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.service;

import java.util.List;
import org.Company.dao.RoleRepository;
import org.Company.dao.UserRepository;
import org.Company.entities.Role;
import org.Company.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ayoub
 */
@RestController
@Secured(value = {"ROLE_ADMIN"})
public class UserRestService
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @RequestMapping(value = "/addUser")
    public User saveUser(User u)
    {
        return userRepository.save(u);
    }
    
    @RequestMapping(value = "/findUsers")
    public List<User> findUsers()
    {
        return userRepository.findAll();
    }
    
    @RequestMapping(value = "/addRole")
    public Role saveRole(Role u)
    {
        return roleRepository.save(u);
    }
    
    @RequestMapping(value = "/findRoles")
    public List<Role> findRoles()
    {
        return roleRepository.findAll();
    }
    
    @RequestMapping(value = "/addRoleToUser")
    public User addRoleToUser(String username,String role)
    {
        User u = userRepository.findOne(username);
        Role r = roleRepository.findOne(role);
        
        u.getRoles().add(r);
        
        return userRepository.save(u);
    }
    
}
