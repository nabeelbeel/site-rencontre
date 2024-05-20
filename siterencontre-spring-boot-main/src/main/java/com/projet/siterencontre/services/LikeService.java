package com.projet.siterencontre.services;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Like;
import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepo repo;


    public LikeService(LikeRepo likeRepository) {
        this.repo = likeRepository;
    }

    public boolean createLike(Like like, String sessionEmail, String likeEmail){
        List<Like> allLikes = (List<Like>)repo.findAll();
        for (Like likeCheck: allLikes) {
            if (likeCheck.getFrom_user_email().equals(sessionEmail) && likeCheck.getTo_user_email().equals(likeEmail)){
                return false;
            }
        }
        repo.save(like);
        return true;
    }

    public List<Like> getLikesToUser(String email){
        return repo.getLikeToUser(email);
    }

    public List<String> getMutualLikes(String userEmail){
        List<Like> rawList = (List<Like>)repo.findAll();

        List<String> emailCheck = new ArrayList<>();
        List<String> matchs = new ArrayList<>();

        for (Like like: rawList) {
            if (like.getFrom_user_email().equals(userEmail)){
                emailCheck.add(like.getTo_user_email());
            }
        }
        for (Like like: rawList) {
            for (String email: emailCheck) {
                if (email.equals(like.getFrom_user_email()) && like.getTo_user_email().equals(userEmail)) {
                    matchs.add(email);
                }
            }
        }
        return matchs;
    }
}