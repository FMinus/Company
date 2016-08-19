/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.Company.dao.EtudiantRepository;
import org.Company.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ayoub
 */
@RestController
public class EtudiantRestService
{
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Secured(value = {"ROLE_ADMIN","ROLE_SCOLARITE"})
    @RequestMapping(value = "/saveEtudiant",method = RequestMethod.POST)
    public Object saveEtudiant(@RequestBody @Valid Etudiant e,BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            Map<String,Object> errors = new HashMap<>();
            errors.put("errors", true);
            
            for(FieldError fe : bindingResult.getFieldErrors())
            {
                errors.put(fe.getField(), fe.getDefaultMessage());
            }
            
            return errors;
        }
        
        return etudiantRepository.save(e);
    }
    
    @Secured(value = {"ROLE_ADMIN","ROLE_SCOLARITE","ROLE_PROF","ROLE_ETUDIANT"})
    @RequestMapping(value = "etudiants")
    public Page<Etudiant> listEtudiant(@RequestParam(name = "page") int page,@RequestParam(name = "size") int size)
    {
        return etudiantRepository.findAll(new PageRequest(page,size));
    }
    
    @RequestMapping(value = "getLoggedUser")
    public Map<String,Object> getLoggedUser(HttpServletRequest httpServletRequest)
    {
        HttpSession session = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        
        String username = securityContext.getAuthentication().getName();
        List<String> roles = new ArrayList();
        for(GrantedAuthority ga : securityContext.getAuthentication().getAuthorities())
        {
            roles.add(ga.getAuthority());
        }
        
        Map<String,Object> params = new HashMap<>();
        params.put("username", username);
        params.put("roles", roles);
        
        return params;
    }
}
