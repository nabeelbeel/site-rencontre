package com.projet.siterencontre.controllers;


import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import com.projet.siterencontre.services.UtilisateurService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UtilisateurController {

    @Autowired
    UtilisateurService service;

    @Autowired
    UtilisateurRepo repo;

    @GetMapping("/listeMembres")
    public String afficherUtilisateur(Model model){
        Iterable<Utilisateur> listUtilisateurs = service.getAllUsers();
        /*Iterable<Utilisateur> listVilles = (Iterable<Utilisateur>) service.getUserByVille(ville);
        model.addAttribute("listVilles",listVilles);*/
        model.addAttribute("listUtilisateurs",listUtilisateurs);
        return "listeMembres";
    }
    @PostMapping("/listeMembresGenre")
    public String afficherUtilisateurHomme(@RequestParam(name="genre") String genre, Model model){
        if(genre.equals("true")){
            Iterable<Utilisateur> listUtilisateurs = service.getUserByGenre(true);
            model.addAttribute("listUtilisateurs",listUtilisateurs);
            return "listeMembres";
        }
        else if (genre.equals("false")){
            Iterable<Utilisateur> listUtilisateurs = service.getUserByGenre(false);
            model.addAttribute("listUtilisateurs",listUtilisateurs);
            return "listeMembres";
        }
        else{
            Iterable<Utilisateur> listUtilisateurs = service.getAllUsers();
            model.addAttribute("listUtilisateurs",listUtilisateurs);
            return "listeMembres";
        }
        /*Iterable<Utilisateur> listVilles = (Iterable<Utilisateur>) service.getUserByVille(ville);
        model.addAttribute("listVilles",listVilles);*/

    }

    @GetMapping("/profile")
    public String afficherProfil(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Utilisateur user = service.getUserById((Integer)session.getAttribute("id"));
        model.addAttribute("user",user);
        return "profile";
    }

    @ModelAttribute("uniqueCities")
    public Set<String> getUniqueCities() {
        Set<String> uniqueCities = new HashSet<>();
        Iterable<Utilisateur> listUtilisateurs = service.getAllUsers();

        for (Utilisateur utilisateur : listUtilisateurs) {
            uniqueCities.add(utilisateur.getVille());
        }

        return uniqueCities;
    }

    @ModelAttribute("uniqueGenre")
    public Set<Boolean> getUniqueGenre() {
        Set<Boolean> uniqueGenre = new HashSet<>();
        Iterable<Utilisateur> listUtilisateurs = service.getAllUsers();

        for (Utilisateur utilisateur : listUtilisateurs) {
            uniqueGenre.add(utilisateur.getGenre());
        }

        return uniqueGenre;
    }

    @GetMapping("/rechercher/utilisateur")
    public String rechercherUtilisateur(Model model, @RequestParam ("keyword") String keyword) {
        List<Utilisateur> listUtilisateurs = service.getUsersByName(keyword);
        model.addAttribute("listUtilisateurs", listUtilisateurs);
        model.addAttribute("keyword", keyword);
        return "listeMembres";
    }

    @GetMapping("/utilisateurs/edit/{id}")
    public String modification( @PathVariable(name="id") Integer id, Model model) {
        Utilisateur utilisateur=service.getUserById(id);
        model.addAttribute("pageTitle","Modifier utilisateur (ID: " +id+")");
        model.addAttribute("utilisateur",utilisateur);
        return "modifierProfil";
    }

    @PostMapping("/utilisateurs/save")
    public String modifyUser(Utilisateur utilisateur, @RequestParam(name= "id") Integer id, @RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        if (file.isEmpty()){
            service.modifyUserWithoutPhoto(utilisateur,id);
        } else{
            service.modifyUser(utilisateur,id, file);
        }
        redirectAttributes.addFlashAttribute("message", "Votre profil a bien été modifié!");
        return "redirect:/profile";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadAssignment(@PathVariable String fileName) {

        byte[] response = service.downloadPhoto(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/jpg"))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\""
                )
                .body(response);
    }

}
