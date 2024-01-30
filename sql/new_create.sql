  CREATE TABLE Look(
    id_look SERIAL PRIMARY KEY,
    nom_look VARCHAR(90)
);
CREATE TABLE unite(
    id_unite serial primary key ,
    nom_unite varchar(90)
  );
  CREATE TABLE dimension(
    id_dimension serial primary key ,
    nom_dimension varchar(90),
    valeur integer default 0
  );

  CREATE TABLE type(
   id_type serial primary key ,
   nom_type varchar(90)
  );

CREATE TABLE Matiere(
    id_matiere SERIAL PRIMARY KEY,
    nom_matiere VARCHAR(90),
    id_unite smallint references unite(id_unite)
);
CREATE TABLE Matiere_look(
    id_matiere_look SERIAL PRIMARY KEY,
    id_look INTEGER NOT NULL REFERENCES Look(id_look),
    id_matiere INTEGER NOT NULL REFERENCES Matiere(id_matiere)
);
CREATE TABLE sac(
    id_sac serial primary key ,
    id_dimension smallint references dimension(id_dimension),
    id_type smallint references type(id_type),
    id_look smallint references look(id_look),
    nom_sac varchar(90),
    prix_vente numeric default 0
);


CREATE TABLE matiere_sac(
    id_matiere_sac serial primary key ,
    id_matiere smallint references matiere(id_matiere),
    id_sac integer not null references sac(id_sac),
    quantite numeric
);
create table fournisseur(
  id_fournisseur serial primary key ,
  nom_fournisseur varchar,
  contact varchar
);

create table mouvement_stock(
  id_mouvement serial primary key,
  id_matiere INTEGER references matiere(id_matiere),
  quantite_plus numeric default 0,
  quantite_moins numeric default 0,
  date date,
  prix_unitaire numeric,
  id_fournisseur INTEGER references fournisseur(id_fournisseur) 
);
alter table mouvement_stock add column qualite integer default 0;

create table fabrication(
    id_fabrication serial primary key, 
    id_sac integer references sac(id_sac),
    quantite numeric, 
    date timestamp
);
create table type_rh(
    id_type_rh serial primary key, 
    nom varchar
);

create table taille_nb_ressources_humaines(
  id_dimension INTEGER not null references dimension(id_dimension),
  nombre integer default 0,
  date timestamp default now(),
  id_type_rh integer references type_rh(id_type_rh),
);
create table type_duree_travail(
  id_type_duree_travail serial primary key, 
  idtype integer not null references type(id_type),
  duree numeric, 
  id_type_rh integer not null references type_rh(id_type_rh),
  date timestamp default now()
);
create table param_dimension_rh (
  nb_personne_standard integer default 0,
  id_type_rh integer not null references type_rh(id_type_rh),
  mult integer default 2,
  date timestamp default now()
);
create table prix_vente (
  id_prix_vente serial primary key, 
  id_sac integer references sac(id_sac),
  prix_vente numeric, 
  date timestamp default now()
);

--

create table tauxhorairebase (
  id_taux serial primary key, 
  taux numeric default 0, 
  date date default now()
);
create table param_profil(
  id_profil integer references profil(id_profil),
  taux_horaire numeric default 1,
  duree_travail_min numeric default 1,
  duree_travail_max numeric default 1, 
  date timestamp default now()
);
create table employe(
  id_employe serial primary key, 
  nom_employe varchar, 
  date_embauche date, 
  id_profil_depart integer references profil(id_profil)
);
create table client(
  id_client serial primary key, 
  nom_client varchar, 
  genre integer
);
create table vente(
  id_vente serial primary key, 
  id_client integer references client(id_client),
  date timestamp default now()
);
create table details_vente (
  id_vente integer references vente(id_vente),
  id_sac integer references sac(id_sac), 
  quantite numeric
);
create table mois(
  mois integer
);

create table cause_penalisation (
  id_cause serial primary key,
  cause varchar, 
  valeur_actuelle integer
);

create table valeur_penalisation (
  id_cause integer references cause_penalisation(id_cause),
  valeur integer,
  date timestamp
);

create table penalisation(
  id_fournisseur integer references fournisseur(id_fournisseur),
  id_cause integer references cause_penalisation(id_cause),
  valeur integer,
  date date
);

