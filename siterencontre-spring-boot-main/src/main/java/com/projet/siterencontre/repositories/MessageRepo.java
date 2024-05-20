package com.projet.siterencontre.repositories;

import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {

    @Query("select m from Message m where m.from_user_email= :email")
    public List<Message> getMessagesFromUser(@Param("email") String from_user_email);

    @Query("select m from Message m where m.to_user_email= :email")
    public List<Message> getMessagesToUser(@Param("email") String to_user_email);

}


