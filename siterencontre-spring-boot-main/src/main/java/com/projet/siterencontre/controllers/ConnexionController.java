package com.projet.siterencontre.controllers;

import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.projet.siterencontre.entities.Utilisateur;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ConnexionController {

    @Autowired
    UtilisateurService service;

    @PostMapping("/connexionCheck")
    public String connexion(@RequestParam("inputEmail") String email, @RequestParam("inputPassword") String password, @RequestParam(value = "remember", defaultValue = "") String remember,
                            HttpServletRequest request, HttpServletResponse response, Model model){

        Utilisateur utilisateur;
        utilisateur = service.connexionUser(email, password);

        if (utilisateur != null){
            if (remember.equals("true")){
                Cookie cookieEmail = new Cookie("email", email);
                Cookie cookiePassword = new Cookie("password", password);
                cookieEmail.setMaxAge(3600);
                cookiePassword.setMaxAge(3600);
                response.addCookie(cookieEmail);
                response.addCookie(cookiePassword);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("id", utilisateur.getId());
            session.setAttribute("active", utilisateur.getActive());
            session.setAttribute("nom", utilisateur.getNom());
            session.setAttribute("prenom", utilisateur.getPrenom());
            session.setAttribute("email", utilisateur.getEmail());
            session.setAttribute("password", utilisateur.getPassword());
            session.setAttribute("photo", utilisateur.getPhotoFileName());
            session.setAttribute("ville", utilisateur.getVille());
            session.setAttribute("description", utilisateur.getDescription());
            session.setAttribute("age", utilisateur.getAge());
            session.setAttribute("role", utilisateur.getRole());
            service.putSingleUserOnline(utilisateur.getId());
            return "redirect:/";
        } else {
            model.addAttribute("invalide", "L'email ou le mot de passe est invalide");
            return "connexion";
        }
    }

    @RequestMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null) {
            String nom = (String) session.getAttribute("nom");
            if (nom != null) {
                Integer id = (Integer) session.getAttribute("id");
                service.putSingleUserOffline(id);
                session.invalidate();
                model.addAttribute("deconnexion", nom);
                return "redirect:/";
            } else {
                return "redirect:/connexion";
            }
        } else {
            return "redirect:/connexion";
        }
    }
}
