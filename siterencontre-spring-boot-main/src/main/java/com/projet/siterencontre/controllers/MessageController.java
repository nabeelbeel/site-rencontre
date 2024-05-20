package com.projet.siterencontre.controllers;

import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.services.MailService;
import com.projet.siterencontre.services.MessageService;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    EntityManager manager;
    @Autowired
    MailService mail;

    @RequestMapping("/messages")
    public String pageMessages(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        String userEmail = (String) session.getAttribute("email");
        List<Message> listeMessagesEnvoyes = messageService.showMessagesFromUser(userEmail);
        List<Message> listeMessagesRecus = messageService.showMessagesToUser(userEmail);
        Iterable<Utilisateur> listeUtilisateurs = utilisateurService.getAllUsers();

        List<Message> tempListeRecus = listeMessagesRecus;
        List<Message> tempListeEnvoyes = listeMessagesEnvoyes;

        List<Message> nomMessagesRecus = new ArrayList<>();
        for (Message message: tempListeRecus) {
            manager.detach(message);
            String fromUser = message.getFrom_user_email();
            Utilisateur user = utilisateurService.getUserByEmail(fromUser);
            message.setFrom_user_email(user.getPrenom() + " " + user.getNom());
            nomMessagesRecus.add(message);
        }

        List<Message> nomMessagesEnvoyes = new ArrayList<>();
        for (Message message: tempListeEnvoyes) {
            manager.detach(message);
            String toUser = message.getTo_user_email();
            Utilisateur user = utilisateurService.getUserByEmail(toUser);
            message.setTo_user_email(user.getPrenom() + " " + user.getNom());
            nomMessagesEnvoyes.add(message);
        }

        model.addAttribute("nomMessagesRecus", nomMessagesRecus);
        model.addAttribute("nomMessagesEnvoyes", nomMessagesEnvoyes);
        model.addAttribute("listeMessagesEnvoyes", listeMessagesEnvoyes);
        model.addAttribute("listeMessagesRecus", listeMessagesRecus);
        model.addAttribute("listeUtilisateurs", listeUtilisateurs);
        return "messagesPersonnels";
    }

    @PostMapping("/messages/envoyer")
    public String envoyerMessage( @RequestParam("file") MultipartFile file,@RequestParam("to_user_email") String to_user_email, @RequestParam("message") String message, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String subject = "Recover | Vous avez reçu un nouveau message";
        String Message = " Salut, un utilisateur vous a envoyé un nouveau message";
        String userEmail = (String) session.getAttribute("email");
        Message savedMessage = new Message(userEmail, to_user_email, message, LocalDateTime.now());

        boolean retourBD = messageService.createMessage(savedMessage);
        // ajouter des données temporairement durant la requete
        //redirectAttributes.addFlashAttribute("message", "Le message a été envoyé avec success");

        if (retourBD) {
            if (!file.isEmpty()) {
                // Handle the file upload
                String fileName = file.getOriginalFilename();
                // Save the file or perform any required operations

                // Attach file information to the email or perform necessary operations
                mail.sendEmailWithFile(to_user_email, subject, message, fileName, file);
            } else {
                mail.sendEmail(to_user_email, subject, message);
            }
            return "redirect:/messages";
        } else {
            return "redirect:/messages";
        }
    }

}
