CREATE OR REPLACE VIEW v_liste_matiere_look AS(
    SELECT 
    ml.id_matiere_look,ml.id_look,ml.id_matiere,l.nom_look,m.nom_matiere 
    FROM Matiere_look as ml 
    JOIN Look as l 
        on ml.id_look=l.id_look 
    JOIN Matiere as m 
        on ml.id_matiere=m.id_matiere
);

CREATE OR REPLACE VIEW v_all_possibilites_matiere_look AS (
    SELECT 
    * 
    FROM Look,Matiere
);
CREATE OR REPLACE VIEW v_liste_matier_look_non_compatible AS(
    SELECT
    vall.id_look,vall.nom_look,vall.id_matiere,vall.nom_matiere
    FROM v_all_possibilites_matiere_look AS vall
    LEFT JOIN v_liste_matiere_look AS ml
        ON  vall.id_matiere=ml.id_matiere 
        AND vall.id_look=ml.id_look
    WHERE ml.id_matiere_look is NULL
);

create or replace view v_prix_unitaire_matiere as(
    select 
    matiere.id_matiere,coalesce(sum(quantite_plus*prix_unitaire)/sum(quantite_plus),0) as prixunitaire
    from
    v_entree_stock 
    right join matiere
        on matiere.id_matiere = v_entree_stock.id_matiere
    group by matiere.id_matiere
);

CREATE OR REPLACE VIEW v_formule_confection AS(
    SELECT
    MS.id_sac, matiere.id_matiere,MS.quantite, v_prix_unitaire_matiere.prixunitaire, sac.nom_sac
    FROM matiere_sac AS MS
    join sac on sac.id_sac = ms.id_sac
    join matiere on MS.id_matiere = matiere.id_matiere
    join v_prix_unitaire_matiere on v_prix_unitaire_matiere.id_matiere = ms.id_matiere
    WHERE quantite>0
);
create or replace view v_cout_matiere_sac as (
    SELECT
    id_sac, nom_sac,sum(prixunitaire*quantite) as prix
    from 
    v_formule_confection
    group by id_sac, nom_sac
);

create or replace view v_stock_matiere as(
    select 
    id_matiere, nom_matiere, sum(quantite_plus - quantite_moins) as reste
    from mouvement_stock
    natural join matiere
    group by id_matiere, nom_matiere
);

create or replace view v_quantite_matiere_stock_besoin as(
    select 
    matiere_sac.id_sac, matiere_sac.quantite, matiere.nom_matiere, coalesce(v_stock_matiere.reste,0) as reste, matiere.id_matiere
    from v_stock_matiere
    right join matiere_sac
    on matiere_sac.id_matiere = v_stock_matiere.id_matiere
    join matiere
    on matiere.id_matiere = matiere_sac.id_matiere
);

create or replace view v_max_date_taille_ressources_humaines as (
    select 
    id_type_rh, max(date) as date
    from taille_nb_ressources_humaines
    group by id_type_rh
);

create or replace view v_dernier_taille_ressources_humaines as (
    select 
    taille_nb_ressources_humaines.*
    from taille_nb_ressources_humaines
    join v_max_date_taille_ressources_humaines
        on v_max_date_taille_ressources_humaines.id_type_rh = taille_nb_ressources_humaines.id_type_rh
        and v_max_date_taille_ressources_humaines.date = taille_nb_ressources_humaines.date
);

create or replace view v_max_date_type_duree as(
    select
    idtype ,id_type_rh, max(date) as date
    from type_duree_travail
    group by idtype, id_type_rh
);
create or replace view v_dernier_type_duree_travail as (
    select
    type_duree_travail.*
    from type_duree_travail
    join v_max_date_type_duree
    on v_max_date_type_duree.idtype = type_duree_travail.idtype
    and v_max_date_type_duree.id_type_rh = type_duree_travail.id_type_rh
    and v_max_date_type_duree.date = type_duree_travail.date
);

create or replace view v_cout_rh as (
    select 
    sac.id_sac, sum(v_dernier_type_duree_travail.duree*v_dernier_taille_ressources_humaines.nombre*type_rh.taux_horaire) as cout_rh
    from sac 
    join v_dernier_type_duree_travail
        on v_dernier_type_duree_travail.idtype = sac.id_type
    join v_dernier_taille_ressources_humaines
        on v_dernier_taille_ressources_humaines.id_dimension= sac.id_dimension
    join type_rh 
        on v_dernier_taille_ressources_humaines.id_type_rh = type_rh.id_type_rh
        and v_dernier_type_duree_travail.id_type_rh = type_rh.id_type_rh
    group by id_sac
);

create or replace view v_cout_sac as (
    select 
    sac.id_sac, sac.nom_sac, v_cout_rh.cout_rh,prix as cout_matiere, cout_rh+prix as cout
    from sac 
    join v_cout_rh
        on v_cout_rh.id_sac = sac.id_sac
    join v_cout_matiere_sac
        on v_cout_matiere_sac.id_sac = sac.id_sac
);
create or replace view v_benefice as (
    select 
    sac.prix_vente, v_cout_sac.cout_rh,v_cout_sac.cout_matiere, v_cout_sac.cout, prix_vente - cout as benefice, sac.id_sac,sac.nom_sac
    from sac 
    join v_cout_sac
        on v_cout_sac.id_sac  = sac.id_sac
);
---
create view v_max_date_param_profil as(
    select 
    id_profil, max(date) as date
    from 
    param_profil
    group by id_profil
);
create or replace view v_dernier_param_profil as (
    select 
    param_profil.* 
    from param_profil
    join v_max_date_param_profil
        on v_max_date_param_profil.date = param_profil.date 
        and v_max_date_param_profil.id_profil = param_profil.id_profil
); 
create or replace view v_duree_travail as(
    select 
    employe.*, profil.duree_travail_min+extract(year from now())-extract(year from date_embauche) as duree_reelle
    from 
    employe
    join v_dernier_param_profil as profil
        on profil.id_profil = employe.id_profil_depart
);

create or replace view v_profil_actuel as (
    select 
    v_duree_travail.*, v_dernier_param_profil.id_profil
    from 
    v_duree_travail
    join v_dernier_param_profil
        on v_dernier_param_profil.duree_travail_min <= v_duree_travail.duree_reelle
        and v_dernier_param_profil.duree_travail_max > v_duree_travail.duree_reelle
);
create or replace view v_taux_horaire_base as(
    select 
    taux 
    from tauxhorairebase 
    where date = (select max(date) from tauxhorairebase )
);
create or replace view v_profil_taux_horaire_actuel as(
    select 
    v_profil_actuel.*, profil.nom_profil, v_dernier_param_profil.taux_horaire * (select taux from v_taux_horaire_base) as taux_horaire
    from v_profil_actuel
    join v_dernier_param_profil
        on v_dernier_param_profil.id_profil = v_profil_actuel.id_profil
    join profil
        on profil.id_profil = v_profil_actuel.id_profil
);
create or replace view v_details_vente as(
    select 
    details_vente.*,vente.id_client, vente.date, client.genre
    from details_vente
    join vente
        on vente.id_vente = details_vente.id_vente
    join client
        on client.id_client = vente.id_client
);

create or replace view v_vente_par_sac_genre as(
    select 
    sac.id_sac, sac.nom_sac, coalesce(sum(quantite),0) AS quantite, genre
    from v_details_vente
    right join sac on sac.id_sac = v_details_vente.id_sac
    group by sac.id_sac, sac.nom_sac, genre
);

create or replace view v_vente_all_genre as (
    SELECT 
    coalesce(sum(quantite),0) as quantite ,0 as id_sac  ,genre , 'tous' as nom_sac
    FROM v_vente_par_sac_genre 
    group by genre
);

create or replace view v_vente_par_sac as(
    select 
    v_vente_par_sac_genre.id_sac,v_vente_par_sac_genre.nom_sac, sum(quantite) AS quantite
    from v_vente_par_sac_genre
    group by v_vente_par_sac_genre.id_sac, v_vente_par_sac_genre.nom_sac
);
create or replace view v_vente_all as(
    select 
    v_vente_all_genre.id_sac,sum(quantite) AS quantite
    from v_vente_all_genre
    group by v_vente_all_genre.id_sac
);

create or replace view v_entree_stock as(
    select 
    *
    from mouvement_stock
    where quantite_moins=0
);


create or replace view v_annee_vente as(
    select 
    generate_series(f_min_annee_vente(), extract(year from now())) as annee
);

create or replace view v_vente_produit_mois_default as(
    select 
    sac.id_sac,0 as quantite,mois , annee
    from sac, mois, v_annee_vente
);

create or replace view v_vente_produit_mois as(
    select 
    d.id_sac, d.mois,d.annee ,greatest(sum(v_details_vente.quantite),d.quantite) as quantite
    from 
    v_details_vente
    right join v_vente_produit_mois_default as d
        on d.annee = extract(year from date) 
        and d.mois = extract(month from date)
        and d.id_sac = v_details_vente.id_sac
    group by d.id_sac, d.mois, d.annee,d.quantite
);
create or replace view v_vente_tous_mois as(
    select 
    sum(quantite) as quantite, 0 as id_sac, annee, mois
    from v_vente_produit_mois
    group by mois, annee
);

create or replace view v_benefice_vente_sac as(
    select 
    sac.id_sac,sac.nom_sac, v_vente_par_sac.quantite, v_benefice.cout_rh * v_vente_par_sac.quantite as cout_rh,v_benefice.cout_matiere * v_vente_par_sac.quantite as cout_matiere,v_benefice.prix_vente * v_vente_par_sac.quantite as ca, v_benefice.benefice* v_vente_par_sac.quantite as rentabilite, v_benefice.prix_vente as prix_vente_unitaire
    from sac 
    join v_vente_par_sac
        on v_vente_par_sac.id_sac = sac.id_sac
    join v_benefice 
        on v_benefice.id_sac = sac.id_sac
);

create or replace view v_benefice_vente_all as (
    select 
    0 as id_sac, 'Tous' as nom_sac, sum(quantite) as quantite, sum(cout_rh) as cout_rh, sum(cout_matiere) as cout_matiere, sum(ca) as ca, sum(rentabilite) as rentabilite
    from v_benefice_vente_sac
);

