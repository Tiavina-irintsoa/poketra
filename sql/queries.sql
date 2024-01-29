select 
matiere.id_matiere, matiere.nom_matiere, coalesce(avant_date1.stock,0) as stock_date1, sum(mouvement_stock.quantite_plus-mouvement_stock.quantite_moins) as stock_date2, sum(mouvement_stock.quantite_plus*mouvement_stock.prix_unitaire)/sum(mouvement_stock.quantite_plus) as prix_unitaire, sum(mouvement_stock.quantite_plus*mouvement_stock.prix_unitaire) as montant_total
from 
mouvement_stock
join matiere 
    on matiere.id_matiere = mouvement_stock.id_matiere
left join (
    select 
    sum(quantite_plus-quantite_moins) as stock, id_matiere
    from mouvement_stock
    where date<='2024-01-27'
    group by id_matiere
) as avant_date1
on avant_date1.id_matiere = mouvement_stock.id_matiere
where mouvement_stock.date <= '2024-02-10'
group by matiere.id_matiere, matiere.nom_matiere, avant_date1.stock;




