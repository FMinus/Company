package org.Company.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Ayoub
 */
@Entity
public class Etudiant implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    
    private String nom;
    
    private String prenom;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    public Etudiant()
    {
    }

    public Etudiant(String nom, String prenom, Date dateNaissance)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Long getIdEtudiant()
    {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant)
    {
        this.idEtudiant = idEtudiant;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public Date getDateNaissance()
    {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }
    
    
    
}
