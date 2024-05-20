package com.projet.siterencontre.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean active;
    @Column(length = 64, nullable = false)
    private String nom;
    @Column(length = 64, nullable = false)
    private String prenom;
    @Column(length = 128, nullable = false)
    private String email;
    @Column(length = 64, nullable = false)
    private String password;
    private String photoFileName;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photoFileContent;
    @Column(length = 45, nullable = false)
    private String ville;
    @Column(length = 300)
    private String description;
    @Column(nullable = false)
    private Integer age;
    @Column(length = 15, nullable = false)
    private String role;
    @Column(columnDefinition = "boolean default false")
    private boolean online;
    @Column(nullable = false)
    private boolean genre;
    
    public Utilisateur() {
    
    }

    public Utilisateur(boolean active, String nom, String prenom, String email, String password, String ville, String description, Integer age, String role, boolean genre) {
        this.active = active;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.ville = ville;
        this.description = description;
        this.age = age;
        this.role = role;
        this.genre = genre;
    }

    public Utilisateur(boolean active, String nom, String prenom, String email, String password, String photoFileName, byte[] photoFileContent, String ville, String description, Integer age, String role, boolean genre) {
        this.active = active;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.photoFileName = photoFileName;
        this.photoFileContent = photoFileContent;
        this.ville = ville;
        this.description = description;
        this.age = age;
        this.role = role;
        this.genre = genre;
    }

    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id=id;
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setActive(boolean active){
        this.active=active;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom=nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getVille(){
        return ville;
    }
    
    public void setVille(String ville){
        this.ville=ville;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description=description;
    }
    
    public Integer getAge(){
        return age;
    }
    
    public void setAge(Integer age){
        this.age=age;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role=role;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean getGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", active=" + active +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ville='" + ville + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", online=" + online +
                ", genre=" + genre +
                '}';
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public byte[] getPhotoFileContent() {
        return photoFileContent;
    }

    public void setPhotoFileContent(byte[] photoFileContent) {
        this.photoFileContent = photoFileContent;
    }
}
