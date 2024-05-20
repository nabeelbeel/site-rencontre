package com.projet.siterencontre.services;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Like;
import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.FavorisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorisService {

    @Autowired
    FavorisRepo repo;

    public boolean createFavori(Favoris favori, String sessionEmail, String likeEmail){
        List<Favoris> allLikes = (List<Favoris>)repo.findAll();
        for (Favoris favorisCheck: allLikes) {
            if (favorisCheck.getFrom_user_email().equals(sessionEmail) && favorisCheck.getTo_user_email().equals(likeEmail)){
                return false;
            }
        }
        repo.save(favori);
        return true;
    }

    public List<Favoris> getFavorisOfUser(String email){
        return repo.getFavorisOfUser(email);
    }

    public boolean deleteFavori(Integer id){
        repo.deleteById(id);
        return !repo.existsById(id);
    }

}
