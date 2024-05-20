package com.projet.siterencontre.services;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.repositories.UtilisateurRepo;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Transactional
@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepo repo;

    public Utilisateur connexionUser(String email, String password){
        Utilisateur utilisateur = repo.getUserByEmailAndPassword(email, password);
        return utilisateur;
    }

    public boolean createUser(Utilisateur utilisateur, MultipartFile request){
        String fileName = StringUtils.cleanPath(request.getOriginalFilename());
        try {
            utilisateur.setPhotoFileName(fileName);
            utilisateur.setPhotoFileContent(request.getBytes());
            Utilisateur savedUser = repo.save(utilisateur);
            return repo.existsById(savedUser.getId());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public byte[] downloadPhoto(String fileName){
        Utilisateur utilisateur = repo.readUtilisateurByPhotoFileName(fileName);
        return utilisateur.getPhotoFileContent();
    }

    public Utilisateur ajouteruser(Utilisateur utilisateur){
        return repo.save(utilisateur);
    }

    public Iterable<Utilisateur> getAllUsers(){
        return repo.findAll();
    }

    public Utilisateur getUserById(Integer id){
        return repo.userById(id);
    }

    public List<Utilisateur> afficherUserById(Integer id){
        return (List<Utilisateur>) repo.userById(id);
    }

    public List<Utilisateur> getUserByGenre(boolean genre){
        return repo.userByGenre(genre);
    }


    public boolean modifyUser(Utilisateur user, Integer id, MultipartFile request){
        String fileName = StringUtils.cleanPath(request.getOriginalFilename());
        try {
            byte[] photoFileContent = request.getBytes();
            Integer retour = repo.modifyUserById(
                    user.getActive(),
                    user.getNom(),
                    user.getPrenom(),
                    user.getEmail(),
                    user.getPassword(),
                    fileName,
                    photoFileContent,
                    user.getVille(),
                    user.getDescription(),
                    user.getAge(),
                    user.getRole(),
                    id);
            return retour > 0;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public boolean deleteUser(Integer id){
        repo.deleteById(id);
        return !repo.existsById(id);
    }

    public Utilisateur getUserByEmail(String email) {
        Utilisateur user;
        user = repo.userByEmail(email);
        return user;
    }
    public Utilisateur getUserByVille(String ville) {
        Utilisateur user;
        user = repo.userByVille(ville);
        return user;
    }


    public void putSingleUserOnline(Integer id){
        repo.modifyUserOnline(true, id);
    }

    public void putSingleUserOffline(Integer id){
        repo.modifyUserOnline(false, id);
    }

    public void putUsersOffline(){
        Iterable<Utilisateur> listeUtilisateurs = repo.findAll();
        for (Utilisateur user : listeUtilisateurs) {
            repo.modifyUserOnline(false, user.getId());
        }
    }

    public boolean isEmailUnique(String email) {
        Utilisateur utilisateur = repo.userByEmail(email);
        if (utilisateur == null) return true;
        return false;
    }

    public List<Utilisateur> getUsersByName(String keyword){return repo.findByNomPrenom(keyword);}

    public void modfifyPhoto(String fileName, byte[] fileContent, Integer id){
        repo.modifyUserPhotoById(fileName, fileContent, id);
    }

    public boolean modifyUserWithoutPhoto(Utilisateur user, Integer id) {
        Integer retour = repo.modifyUserByIdWithoutPhoto(
                user.getActive(),
                user.getNom(),
                user.getPrenom(),
                user.getEmail(),
                user.getPassword(),
                user.getVille(),
                user.getDescription(),
                user.getAge(),
                user.getRole(),
                id);
        return retour > 0;

    }
}
