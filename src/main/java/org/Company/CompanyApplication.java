package org.Company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.Company.dao.EtudiantRepository;
import org.Company.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CompanyApplication {
    
    public static void main(String[] args) throws ParseException
    {
        ApplicationContext ctx = SpringApplication.run(CompanyApplication.class, args);
        
        EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        /*
        etudiantRepository.save(new Etudiant("ayoub","deqqaq",df.parse("1991-11-05")));
        etudiantRepository.save(new Etudiant("hassan","kamel",df.parse("1992-06-23")));
        etudiantRepository.save(new Etudiant("anas","jihadi",df.parse("1993-04-11")));
        */
        List<Etudiant> etus = etudiantRepository.findAll();
        
        etus.forEach(e -> System.out.println(e.getNom()));
    }
}
