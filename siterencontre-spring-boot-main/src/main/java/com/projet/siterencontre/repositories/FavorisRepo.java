package com.projet.siterencontre.repositories;

import com.projet.siterencontre.entities.Favoris;
import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.entities.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavorisRepo extends CrudRepository<Favoris, Integer> {

    @Query("select f from Favoris f where f.from_user_email= :from_user_email")
    public List<Favoris> getFavorisOfUser(@Param("from_user_email") String from_user_email);

}
