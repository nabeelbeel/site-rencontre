package com.projet.siterencontre.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false)
    private String from_user_email;
    @Column(length = 128, nullable = false)
    private String to_user_email;
    @Column(length = 2000, nullable = false)
    private String message;
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = true, length = 64)
    private String file;

    
    public Message() {
    }

    public Message(String from_user_email, String to_user_email, String message, LocalDateTime date) {
        this.from_user_email = from_user_email;
        this.to_user_email = to_user_email;
        this.message = message;
        this.date=date;



    }

    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id=id;
    }
    
    public String getFrom_user_email(){
        return from_user_email;
    }
    
    public void setFrom_user_email(String from_user_email){
        this.from_user_email=from_user_email;
    }
    
    public String getTo_user_email(){
        return to_user_email;
    }
    
    public void setTo_user_email(String to_user_email){
        this.to_user_email=to_user_email;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message=message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }





}
