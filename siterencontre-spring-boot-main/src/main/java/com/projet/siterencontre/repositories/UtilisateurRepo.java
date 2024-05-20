package com.projet.siterencontre.repositories;

import com.projet.siterencontre.entities.Utilisateur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UtilisateurRepo extends CrudRepository<Utilisateur, Integer> {


    @Query("select u from Utilisateur u where u.email= :email and u.password= :password")
    public Utilisateur getUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Transactional
    @Modifying
    @Query("update Utilisateur u set u.active= :active, u.nom= :nom, u.prenom= :prenom, u.email= :email, u.password= :password, u.photoFileName= :photoFileName, u.photoFileContent= :photoFileContent, u.ville= :ville, u.description= :description, u.age= :age, u.role= :role where u.id= :id")
    public Integer modifyUserById(
            @Param("active") boolean active,
            @Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("email") String email,
            @Param("password") String password,
            @Param("photoFileName") String photoFileName,
            @Param("photoFileContent") byte[] photoFileContent,
            @Param("ville") String ville,
            @Param("description") String description,
            @Param("age") Integer age,
            @Param("role") String role,
            @Param("id") Integer id
        );

    @Transactional
    @Modifying
    @Query("update Utilisateur u set u.active= :active, u.nom= :nom, u.prenom= :prenom, u.email= :email, u.password= :password, u.ville= :ville, u.description= :description, u.age= :age, u.role= :role where u.id= :id")
    public Integer modifyUserByIdWithoutPhoto(
            @Param("active") boolean active,
            @Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("email") String email,
            @Param("password") String password,
            @Param("ville") String ville,
            @Param("description") String description,
            @Param("age") Integer age,
            @Param("role") String role,
            @Param("id") Integer id
    );

    @Query("select u from Utilisateur u where u.id= :id")
    public Utilisateur userById(@Param("id") Integer id);

    @Query("select u from Utilisateur u where u.email= :email")
    public Utilisateur userByEmail(@Param("email") String email);
    @Query("select u from Utilisateur u where u.ville= :ville")
    public Utilisateur userByVille(@Param("ville") String ville);

    @Query("select u from Utilisateur u where u.nom= :nom")
    public Utilisateur userByNom(@Param("nom") String nom);

    @Query("select u from Utilisateur u where u.genre= :genre")
    public List<Utilisateur> userByGenre(@Param("genre") boolean genre);

    @Transactional
    @Modifying
    @Query("update Utilisateur u set u.online= :online where u.id= :id")
    public void modifyUserOnline(@Param("online") boolean online, @Param("id") Integer id);

    @Query("SELECT u FROM Utilisateur u WHERE u.prenom LIKE %?1% or u.nom LIKE %?1%")
    public List<Utilisateur> findByNomPrenom(String keyword);

    Utilisateur readUtilisateurByPhotoFileName(String photoFileName);

    @Transactional
    @Modifying
    @Query("update Utilisateur u set u.photoFileName= :photoFileName, u.photoFileContent= :photoFileContent where u.id= :id")
    public Integer modifyUserPhotoById(
            @Param("photoFileName") String photoFileName,
            @Param("photoFileContent") byte[] photoFileContent,
            @Param("id") Integer id
    );
}
