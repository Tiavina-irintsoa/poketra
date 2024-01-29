

create function f_min_annee_vente()
returns table (
    year numeric
) AS $$
BEGIN
    RETURN QUERY
    select 
    extract(year from date) 
    from vente
    where date=(select min(date) from vente ) 
    limit 1;
    return ;
end;
$$ LANGUAGE plpgsql;
 


create function f_etat_stock_matiere(date1 date, date2 date)
returns table (
    id integer,
    nom varchar,
    stock_date1 numeric,
    stock_date2 numeric,
    prix_unitaire numeric, 
    montant_total numeric
) AS $$
BEGIN
    RETURN QUERY
    SELECT
    matiere.id_matiere, matiere.nom_matiere, coalesce(avant_date1.stock,0) as stock_date1, sum(mouvement_stock.quantite_plus-mouvement_stock.quantite_moins) as stock_date2, sum(mouvement_stock.quantite_plus*mouvement_stock.prix_unitaire)/sum(mouvement_stock.quantite_plus) as prix_unitaire, sum(mouvement_stock.quantite_plus*mouvement_stock.prix_unitaire) as montant_total
    from 
    mouvement_stock
    join matiere 
        on matiere.id_matiere = mouvement_stock.id_matiere
    left join (
        select 
        sum(quantite_plus-quantite_moins) as stock, id_matiere
        from mouvement_stock
        where date<=date1
        group by id_matiere
    ) as avant_date1
    on avant_date1.id_matiere = mouvement_stock.id_matiere
    where mouvement_stock.date <= date2
    group by matiere.id_matiere, matiere.nom_matiere, avant_date1.stock;
    return ;
end;
$$ LANGUAGE plpgsql;
 
