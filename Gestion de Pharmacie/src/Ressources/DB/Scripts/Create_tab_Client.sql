CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    date_naissance DATE NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    telephone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ssn VARCHAR(255) NOT NULL,
    assurance VARCHAR(255),
    allergies VARCHAR(255),
    conditions VARCHAR(255),
    medicaments VARCHAR(255),
    antecedents VARCHAR(255),
    derniere_visite DATE
);

ALTER TABLE public.clients ALTER COLUMN date_naissance TYPE varchar(50) USING date_naissance::varchar;
