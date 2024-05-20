package com.projet.siterencontre;

import com.projet.siterencontre.entities.Utilisateur;
import com.projet.siterencontre.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class Insert {
    @Autowired
    UtilisateurService service;
    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void insertImages() throws IOException {

        File file = resourceLoader.getResource("classpath:/static/images/users/will-smith.jpg").getFile();
        String fileName = StringUtils.cleanPath(file.getName());
        byte[] fileContent = Files.readAllBytes(file.toPath());
        service.modfifyPhoto(fileName, fileContent, 1);

        File file1 = resourceLoader.getResource("classpath:/static/images/users/allada-pavan.jpg").getFile();
        String fileName1 = StringUtils.cleanPath(file1.getName());
        byte[] fileContent1 = Files.readAllBytes(file1.toPath());
        service.modfifyPhoto(fileName1, fileContent1, 2);

        File file2 = resourceLoader.getResource("classpath:/static/images/users/bruce-kitchell.jpg").getFile();
        String fileName2 = StringUtils.cleanPath(file2.getName());
        byte[] fileContent2 = Files.readAllBytes(file2.toPath());
        service.modfifyPhoto(fileName2, fileContent2, 3);

        File file3 = resourceLoader.getResource("classpath:/static/images/users/muhammad-evran.jpg").getFile();
        String fileName3 = StringUtils.cleanPath(file3.getName());
        byte[] fileContent3 = Files.readAllBytes(file3.toPath());
        service.modfifyPhoto(fileName3, fileContent3, 4);
    }
}
