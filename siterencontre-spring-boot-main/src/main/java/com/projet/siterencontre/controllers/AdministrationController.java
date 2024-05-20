package com.projet.siterencontre.controllers;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdministrationController {

    @Autowired
    UtilisateurService service;

    @RequestMapping("/administration")
    public String pageAdministration(Model model){
        Iterable<Utilisateur> listeUtilisateurs = service.getAllUsers();
        model.addAttribute("listeUtilisateurs", listeUtilisateurs);
        return "administration";

    }


    @RequestMapping("/administration/ajouter")
    public String pageAjouter(Model model){
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        return "inscriptionAdmin";
    };

    @PostMapping("/administration/ajouterUtilisateur")
    public String ajouterUtilisateur(Utilisateur utilisateur, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        boolean retourBD = false;
        retourBD = service.createUser(utilisateur, file);
        if (retourBD){
            String message = "L'utilisateur " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " a bien été ajouté";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/administration";
        } else {
            String message = "Une erreur est survenue lors de l'inscription. Veuillez réessayer";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/administration";
        }
    }

    @GetMapping("/administration/modif_display/{id}")
    public String modifDisplay(@PathVariable Integer id, Model model){
        Utilisateur utilisateur = null;
        utilisateur = service.getUserById(id);
        if (utilisateur != null) {
            model.addAttribute("utilisateur", utilisateur);
            return "modificationAdmin";
        } else {
            return "redirect:/administration";
        }
    }

    @PostMapping("/administration/modifier/{id}")
    public String modifierUtilisateur(@PathVariable Integer id, @RequestParam("file")MultipartFile file, Utilisateur utilisateur, RedirectAttributes redirectAttributes){
        boolean retourBD = false;
        if (file.isEmpty()){
            retourBD = service.modifyUserWithoutPhoto(utilisateur,id);
        } else{
            retourBD = service.modifyUser(utilisateur, id, file);
        }
        if (retourBD){
            String message = "L'utilisateur " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " a bien été modifié";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/administration";
        } else {
            String message = "Une erreur est survenue lors de la modification. Veuillez réessayer";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/administration";
        }
    }

    @GetMapping("/administration/supprimer/{id}")
    public String supprimerUtilisateur(@PathVariable Integer id, Model model){
        boolean retourBD = false;
        retourBD = service.deleteUser(id);
        if (retourBD){
            String message = "L'utilisateur avec l'id " + id + " a bien été supprimé";
            model.addAttribute("message", message);
            return "redirect:/administration";
        } else {
            return "redirect:/administration";
        }
    }

    @PostMapping("/membres/chercher")
    public String chercherMembres(@RequestParam("keyword") String keyword, Model model){
        List<Utilisateur> listeUtilisateurs = service.getUsersByName(keyword);
        model.addAttribute("listeUtilisateurs", listeUtilisateurs);
        model.addAttribute("keyword", keyword);
        return "administration";
    }

}
