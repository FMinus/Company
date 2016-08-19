/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Company.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Ayoub
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private String table = "users";
    private String usernameCol = "username";
    private String passwordCol = "password";
    private String joinTable = "users_roles";
    private String joinTableUserCol = "user_username";
    private String joinTableRoleCol = "roles_role";
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception
    {
        /*
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","PROF");
        auth.inMemoryAuthentication().withUser("prof1").password("123").roles("PROF");
        auth.inMemoryAuthentication().withUser("et1").password("123").roles("ETUDIANT");
        auth.inMemoryAuthentication().withUser("scol1").password("123").roles("SCOLARITE");
        */
        
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select "+usernameCol+" as principal , "+passwordCol+" as credentials, true from "+table+" where "+usernameCol+" = ?")
                .authoritiesByUsernameQuery("select "+joinTableUserCol+" as principal , "+joinTableRoleCol+" as role from "+joinTable+" where "+joinTableUserCol+" =?")
                .rolePrefix("ROLE_");
}

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/css/**" , "/js/**","/images/**").permitAll()
                    .anyRequest().authenticated()
                        .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/index.html")
                        .and()
                .logout()
                    .invalidateHttpSession(true)
                    .logoutUrl("/logout")
                    .permitAll()
                //.("/error_access.html")
                
                ;
    }

   
    
    
}
