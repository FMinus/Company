/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.dao;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.Company.entities.Etudiant;
/**
 *
 * @author Ayoub
 */
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>
{
    
}
