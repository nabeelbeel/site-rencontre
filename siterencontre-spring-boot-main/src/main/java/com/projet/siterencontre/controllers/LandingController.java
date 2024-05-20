package com.projet.siterencontre.controllers;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LandingController {

    @Autowired
    UtilisateurService service;

    @RequestMapping("/")
    public String pageLanding(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null) {
            String nom = (String) session.getAttribute("nom");
            if (nom != null) {
                Iterable<Utilisateur> listeUtilisateurs = service.getAllUsers();
                model.addAttribute("listeUtilisateurs", listeUtilisateurs);
                model.addAttribute("emailUser", session.getAttribute("email"));
                return "accueil";
            } else {

                return "landing";
            }
        } else {
            return "landing";
        }
    }

    @RequestMapping("/connexion")
    public String pageConnexion(@CookieValue(value = "email", defaultValue = "") String email, @CookieValue(value = "password", defaultValue = "") String password, Model model){
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        return "connexion";
    }

    @RequestMapping("/inscription")
    public String pageInscription(Model model){
        model.addAttribute("utilisateur", new Utilisateur());
        return "inscriptionUser";
    }

}
