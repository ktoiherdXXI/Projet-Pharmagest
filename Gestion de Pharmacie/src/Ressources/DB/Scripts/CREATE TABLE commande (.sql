    
CREATE TABLE commande (
    id_cmd INT PRIMARY KEY,
    idmedicament INT NOT NULL,
    nom VARCHAR(50) NOT NULL,
    quantite INT NOT NULL,
    quant_min INT NOT NULL,
    quant_max INT NOT NULL,
    prix FLOAT NOT NULL,
    fournisseur VARCHAR(50) NOT NULL,
    qty_a_cmd INT NOT NULL,
    montant_cmd FLOAT NOT NULL,
    etat_cmd etat NOT NULL,
    date_cmd DATE NOT NULL
);