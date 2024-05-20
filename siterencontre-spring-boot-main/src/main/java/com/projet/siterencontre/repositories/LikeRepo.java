package com.projet.siterencontre.repositories;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Like;
import com.projet.siterencontre.entities.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepo extends CrudRepository<Like, Integer> {
    @Query("select l from Like l where l.to_user_email= :email")
    public List<Like> getLikeToUser(@Param("email") String to_user_email);



}
