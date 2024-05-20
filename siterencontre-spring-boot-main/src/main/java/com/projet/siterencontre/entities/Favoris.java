package com.projet.siterencontre.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "favoris")
public class Favoris {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false)
    private String from_user_email;
    @Column(length = 128, nullable = false)
    private String to_user_email;
    
    public Favoris() {
    }
    
    public Favoris(String from_user_email, String to_user_email) {
        this.from_user_email = from_user_email;
        this.to_user_email = to_user_email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom_user_email() {
        return from_user_email;
    }

    public void setFrom_user_email(String from_user_email) {
        this.from_user_email = from_user_email;
    }

    public String getTo_user_email() {
        return to_user_email;
    }

    public void setTo_user_email(String to_user_email) {
        this.to_user_email = to_user_email;
    }
}
