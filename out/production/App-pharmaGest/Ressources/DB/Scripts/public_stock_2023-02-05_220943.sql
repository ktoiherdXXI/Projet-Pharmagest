DROP TABLE IF EXISTS stock;
CREATE TABLE stock(
    idmedicament integer,
    nom character varying,
    categorie character varying,
    date_f character varying,
    date_e character varying,
    fournisseur character varying,
    prix double precision,
    quantite integer,
    qty_a_cmd integer,
    montant_cmd double precision,
    quant_max integer,
    quant_min integer,
    countdouwn_date timestamp without time zone,
    etat_cmd USER-DEFINED,
    PRIMARY KEY(idmedicament)
);