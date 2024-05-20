package com.projet.siterencontre.controllers;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InscriptionController {

    @Autowired
    UtilisateurService service;

    @PostMapping("/inscriptionUser")
    public String inscription(Utilisateur utilisateur, @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        boolean retourBD = false;
        retourBD = service.createUser(utilisateur, file);
        if (retourBD) {
            String message = "Vous êtes maintenant inscrit " + utilisateur.getPrenom() + " " + utilisateur.getNom();
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/";
        } else {
            String message = "Une erreur est survenue lors de l'inscription. Veuillez réessayer";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/";
        }
    }
}
