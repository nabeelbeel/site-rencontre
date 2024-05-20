package com.projet.siterencontre;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import com.projet.siterencontre.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ComponentScan("com.projet.siterencontre.services")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UtilisateurRepoTests {
    @Autowired
    private UtilisateurRepo repo;

    @Autowired
    private UtilisateurService service;

    @Test
    public void testGetUserByEmailAndPassword(){
        Utilisateur utilisateur = repo.getUserByEmailAndPassword("kanna.allada@gmail.com","Pavan123");
        System.out.println(utilisateur);
        assertThat(utilisateur).isNotNull();
    }
    @Test
    public void test(){
        Utilisateur utilisateur = service.connexionUser("kanna.allada@gmail.com","Pavan123");
        System.out.println(utilisateur);
        assertThat(utilisateur).isNotNull();
    }

/*    @Test
    public void testCreateUser(){
        Utilisateur user = new Utilisateur(true, "nom", "prenom", "emailooo", "password", "photo", "ville", "description", 20, "role");
        Utilisateur savedUser = repo.save(user);
        *//*assertThat(savedUser.getId()).isGreaterThan(0);*//*
        boolean exists = repo.existsById(savedUser.getId());
        System.out.println(exists);
    }*/

    @Test
//    @Bean(name = "monService")
    public void getUserNameFromEmail() {
        String email = "willsmith@gmail.com";
        Utilisateur utilisateur = null;
        String nom = null;
        String prenom = null;
        utilisateur = repo.userByEmail(email);
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        assertThat(nom).isNotNull();
    }

}
