package com.projet.siterencontre.rest;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Like;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import com.projet.siterencontre.services.FavorisService;
import com.projet.siterencontre.services.LikeService;
import com.projet.siterencontre.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestLikeFavoriController {

    @Autowired
    LikeService likeService;
    @Autowired
    UtilisateurRepo userRepo;
    @Autowired
    MailService mail;
    @Autowired
    FavorisService favorisService;

    @PostMapping("/likeUser")
    public String createLike(@Param("fromEmail") String fromEmail, @Param("toEmail") String toEmail) {

        String subject = "Recover | Vous avez reçu un nouveau like";
        String Message = " Salut, un utilisateur a aimé votre profil";

        boolean reponse = likeService.createLike(new Like(fromEmail, toEmail), fromEmail, toEmail);
        mail.sendEmail(toEmail,subject,Message);

        return reponse ? "Success" : "Deja like";
    }

    @PostMapping("/favoriUser")
    public String createFavori(@Param("fromEmail") String fromEmail, @Param("toEmail") String toEmail) {

        String subject = "Recover | Vous avez reçu un nouveau like";
        String Message = " Salut, un utilisateur a favorisé votre profil";

        boolean reponse = favorisService.createFavori(new Favoris(fromEmail, toEmail), fromEmail,toEmail);
        mail.sendEmail(toEmail,subject,Message);

        return reponse ? "Success" : "Deja favori";
    }
}
