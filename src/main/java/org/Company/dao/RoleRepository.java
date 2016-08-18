/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.dao;

import java.io.Serializable;
import org.Company.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ayoub
 */
public interface RoleRepository extends JpaRepository<Role, String>
{
    
}
