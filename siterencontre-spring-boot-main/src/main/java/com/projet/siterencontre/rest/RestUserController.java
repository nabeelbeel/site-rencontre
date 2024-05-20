package com.projet.siterencontre.rest;

import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    @Autowired
    UtilisateurService service;

    @PostMapping("/utilisateurs/doublon")
    public String verifierDoublonProduit(@Param("email") String email){
        return service.isEmailUnique(email)?"OK":"Doublon";
    }

}
