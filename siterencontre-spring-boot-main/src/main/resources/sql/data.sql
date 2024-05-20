INSERT INTO `utilisateurdb`.`utilisateurs`
(`active`, `nom`, `prenom`, `email`, `password`, `ville`, `description`, `age`, `role`, `genre`)

VALUES
    (1, 'Smith','Will', 'willsmith@gmail.com', 'Smith123', 'Montréal', 'Description...', '34', 'Invite', 1),
    (1, 'Pavan','Allada', 'kanna.allada@gmail.com', 'Pavan123', 'Laval', 'Description...', '25', 'Regulier', 0),
    (1, 'Kitchell','Bruce', 'aecllc.bnk@gmail.com', 'Kitchell123', 'Québec', 'Description...', '28', 'Privilege', 1),
    (1, 'Evran','Muhammad', 'muhammad.evran13@gmail.com', 'Evran123', 'Terrebonne', 'Description...', '31', 'Admin', 0);

INSERT INTO `utilisateurdb`.`favoris`
(`from_user_email`,`to_user_email`)

VALUES
    ('willsmith@gmail.com', 'kanna.allada@gmail.com'),
    ('willsmith@gmail.com', 'aecllc.bnk@gmail.com'),
    ('willsmith@gmail.com', 'muhammad.evran13@gmail.com'),
    ('kanna.allada@gmail.com', 'muhammad.evran13@gmail.com'),
    ('aecllc.bnk@gmail.com', 'willsmith@gmail.com');

INSERT INTO `utilisateurdb`.`likes`
(`from_user_email`,`to_user_email`)

VALUES
    ('willsmith@gmail.com', 'kanna.allada@gmail.com'),
    ('kanna.allada@gmail.com', 'willsmith@gmail.com'),
    ('aecllc.bnk@gmail.com', 'kanna.allada@gmail.com');

INSERT INTO `utilisateurdb`.`messages`
(`from_user_email`,`to_user_email`,`message`, `date`)

VALUES
    ('aecllc.bnk@gmail.com', 'kanna.allada@gmail.com', 'Nullam dui nibh, porta quis elit non, varius dignissim elit. Praesent ac massa et lacus laoreet elementum et nec sem.', '2023-04-24 15:53:13');