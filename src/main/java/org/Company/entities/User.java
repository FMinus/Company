/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ayoub
 */
@Entity
@Table(name="users")
public class User implements Serializable
{
    @Id
    private String username;
    private String password;
    
    @ManyToMany
    @JoinTable(name = "USERS_ROLES")
    private Collection<Role> roles;
    
    private boolean activated;

    public User(String username, String password, Collection<Role> roles,boolean activated)
    {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.activated = activated;
    }

    public User()
    {
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }
    
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Collection<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
        this.roles = roles;
    }
    
    
}
