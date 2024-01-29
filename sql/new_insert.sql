insert into Look values
(default,'Luxe'),
(default,'Debraille'),
(default,'Normal');

INSERT INTO unite (nom_unite) VALUES
('kg'),
('piece'),
('metre carre');

INSERT INTO type (nom_type) VALUES
('Sac a dos'),
('Sac a main'),
('cabas');

INSERT INTO dimension (nom_dimension) VALUES
('PM'),
('MM'),
('GM');


INSERT INTO Matiere VALUES
(DEFAULT,'Cuir',3,3000),
(DEFAULT,'Soga',2,2000),
(DEFAULT,'Rafia',1,1500),
(DEFAULT,'tissu brode',1,1000),
(DEFAULT,'Fermeture',1,800),
(DEFAULT,'accesoires',1,400);

insert into prix_unitaire (id_prix_unitaire,id_matiere,prix) values
(DEFAULT,1,3000),
(DEFAULT,2,2000),
(DEFAULT,3,1500),
(DEFAULT,4,1000),
(DEFAULT,5,800),
(DEFAULT,6,400);

INSERT INTO matiere_look (id_look,id_matiere) VALUES
(1,1),
(1,4),
(1,5),
(1,6),
(2,2),
(2,4),
(2,5),
(2,6),
(2,3),
(2,4),
(2,5),
(2,6);

insert into sac (id_type,id_look,id_dimension,nom_sac,prix_vente) values
(1,1,1,'Sac a dos luxe PM',100000),
(1,1,2,'Sac a dos luxe MM',200000),
(1,1,3,'Sac a dos luxe GM',300000),
(1,2,1,'Sac a dos debraille PM',90000),
(1,2,2,'Sac a dos debraille MM',180000),
(1,2,3,'Sac a dos debraille GM',260000),
(1,3,1,'Sac a dos normal PM',60000),
(1,3,2,'Sac a dos normal MM',120000),
(1,3,3,'Sac a dos normal GM',180000),
(2,1,1,'Sac a main luxe PM',30000),
(2,1,2,'Sac a main luxe MM',40000),
(2,1,3,'Sac a main luxe GM',50000),
(2,2,1,'Sac a main debraille PM',20000),
(2,2,2,'Sac a main debraille MM',25000),
(2,2,3,'Sac a main debraille GM',30000),
(2,3,1,'Sac a main normal PM',14000),
(2,3,2,'Sac a main normal MM',20000),
(2,3,3,'Sac a main normal GM',30000);

insert into prix_vente (id_sac, prix_vente) values
(1,100000),
(2,200000),
(3,300000),
(4,90000),
(5,180000),
(6,260000),
(7,60000),
(8,120000),
(9,180000),
(10,30000),
(11,40000),
(12,50000),
(13,20000),
(14,25000),
(15,30000),
(16,14000),
(17,20000),
(18,30000);

insert into matiere_sac (id_sac,id_matiere,quantite) values
(1,1,1),
(1,4,1),
(1,5,1),
(1,6,5),

(2,1,1.5),
(2,4,2),
(2,5,2),
(2,6,10),

(3,1,2),
(3,4,2),
(3,5,2),
(3,6,15),

(4,2,1),
(4,4,1),
(4,5,1),
(4,6,3),

(5,2,1.5),
(5,4,1.5),
(5,5,1.2),
(5,6,5),

(6,2,2),
(6,4,2),
(6,5,2),
(6,6,7),

(7,2,1),
(7,4,1),
(7,5,1),
(7,6,1),

(8,2,1.2),
(8,4,1.3),
(8,5,1.5),
(8,6,2),

(9,2,2),
(9,4,2),
(9,5,2),
(9,6,3),

-- sac a main
(10,1,0.7),
(10,4,0.7),
(10,5,0.5),
(10,6,5),

(11,1,1.2),
(11,4,1.9),
(11,5,2),
(11,6,10),

(12,1,1.7),
(12,4,1.7),
(12,5,2),
(12,6,13),

(13,2,0.7),
(13,4,1),
(13,5,1),
(13,6,3),

(14,2,1.1),
(14,4,1.4),
(14,5,1.2),
(14,6,5),

(15,2,1.9),
(15,4,2),
(15,5,2),
(15,6,7),

(16,2,1),
(16,4,1.1),
(16,5,1),
(16,6,1),

(17,2,1.2),
(17,4,1.3),
(17,5,1.5),
(17,6,2),

(18,2,2),
(18,4,2),
(18,5,2),
(18,6,3);

insert into fournisseur values
(default, 'ABC','0328989089'),
(default, 'Pavillon 89','0389267893'),
(default, 'Pavillon 60','0348157565');

insert into type_rh values 
(default,'Ouvrier',3000),
(default,'Transporteur',500),
(default,'Couturier',4000);

insert into taille_nb_ressources_humaines values 
(1,1,now(), 1),
(1,1,now(), 2),
(1,1,now(), 3),
--
(2,2,now(), 1),
(2,2,now(), 2),
(2,2,now(), 3),
--
(3,4,now(), 1),
(3,4,now(), 2),
(3,4,now(), 3);

insert into type_duree_travail values
(default, 1, 0.2, 1, now()),
(default, 1, 0.5, 2, now()),
(default, 1, 1, 3, now()),
--
(default, 2, 0.5, 1, now()),
(default, 2, 0.7, 2, now()),
(default, 2, 1.5, 3, now()),
--
(default, 3, 0.3, 1, now()),
(default, 3, 0.3, 2, now()),
(default, 3, 1, 3, now());


insert into client values(default, 'Rakoto',1);
insert into client values(default, 'Gabriel',1);
insert into client values(default, 'Rasoa',0);
insert into client values(default, 'Stephanie',0);

insert into vente values(default, 1,1,2);
insert into vente values(default, 2,2,2);
insert into vente values(default, 3,3,5);

insert into profil values(default, 'Debutant');
insert into profil values(default, 'Senior');
insert into profil values(default, 'Expert');

insert into mois values
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12);