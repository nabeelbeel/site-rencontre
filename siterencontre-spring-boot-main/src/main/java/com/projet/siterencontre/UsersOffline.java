package com.projet.siterencontre;

import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class UsersOffline {

    @Autowired
    UtilisateurService service;

    @PreDestroy
    public void putUsersOffline(){
        service.putUsersOffline();
    }

}
