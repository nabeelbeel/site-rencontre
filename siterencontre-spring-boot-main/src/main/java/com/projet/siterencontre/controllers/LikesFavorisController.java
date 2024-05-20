package com.projet.siterencontre.controllers;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Like;
import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import com.projet.siterencontre.services.FavorisService;
import com.projet.siterencontre.services.LikeService;
import com.projet.siterencontre.services.MailService;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LikesFavorisController {

    @Autowired
    LikeService likeService;

    @Autowired
    FavorisService favorisService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    EntityManager manager;
    @Autowired
    UtilisateurRepo userRepo;

    @Autowired
    MailService mail;

    @RequestMapping("/likes_favoris")
    public String pageLikesEtFavoris(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        String userEmail = (String) session.getAttribute("email");
        List<Like> listeLikes = likeService.getLikesToUser(userEmail);
        List<Favoris> listeFavoris = favorisService.getFavorisOfUser(userEmail);
        Iterable<Utilisateur> listeUtilisateurs = utilisateurService.getAllUsers();

        List<Like> tempListeLikes = listeLikes;
        List<Favoris> tempListeFavoris = listeFavoris;

        List<Like> nomLikes = new ArrayList<>();
        for (Like like: tempListeLikes) {
            manager.detach(like);
            String fromUser = like.getFrom_user_email();
            Utilisateur user = utilisateurService.getUserByEmail(fromUser);
            like.setFrom_user_email(user.getPrenom() + " " + user.getNom());
            nomLikes.add(like);
        }

        List<Favoris> nomFavoris = new ArrayList<>();
        for (Favoris favoris: tempListeFavoris) {
            manager.detach(favoris);
            String favUser = favoris.getTo_user_email();
            Utilisateur user = utilisateurService.getUserByEmail(favUser);
            favoris.setTo_user_email(user.getPrenom() + " " + user.getNom());
            nomFavoris.add(favoris);
        }

        List<String> listeMutualString = likeService.getMutualLikes(userEmail);
        List<String> listeMutualName = new ArrayList<>();
        for (String like: listeMutualString) {
            Utilisateur user = utilisateurService.getUserByEmail(like);
            like = user.getPrenom() + " " + user.getNom();
            listeMutualName.add(like);
        }

        model.addAttribute("matchs", listeMutualName);
        model.addAttribute("nomLikes", nomLikes);
        model.addAttribute("nomFavoris", nomFavoris);
        model.addAttribute("listeLikes", listeLikes);
        model.addAttribute("listeFavoris", listeFavoris);
        model.addAttribute("listeUtilisateurs", listeUtilisateurs);
        return "likesFavoris";
    }

    @GetMapping("/favoris/supprimer/{id}")
    public String supprimerFavori(@PathVariable Integer id, Model model){
        favorisService.deleteFavori(id);
        return "redirect:/likes_favoris";
    }
}
