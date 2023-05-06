ALTER TABLE public.stock ADD quant_max integer NULL DEFAULT 20;


DROP TYPE etat;
CREATE TYPE etat AS ENUM ('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler');

DROP TYPE etat CASCADE ;
CREATE TYPE etat AS ENUM ('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler');

ALTER TYPE etat ADD VALUE 'Livrer' BEFORE 'Valider';

-- Creer la colonne etat_cmd de type etat dans la table stock et la table commande
ALTER TABLE public.stock ADD etat_cmd etat NULL DEFAULT 'En_Attente';
ALTER TABLE public.commande ADD etat_cmd etat NULL DEFAULT 'En_Cours';

'En_Attente'::etat

DROP TYPE etat CASCADE ;
ALTER TABLE commande MODIFY COLUMN etat_cmd ENUM('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler');

ALTER TABLE commande ALTER COLUMN etat_cmd TYPE ENUM('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler') USING etat_cmd::text::enum_etat_cmd;

DROP TYPE IF EXISTS etat CASCADE;
CREATE TYPE etat AS ENUM('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler');

-- Pour changer un type de colonne en ENUM dans PostgreSQL, vous pouvez utiliser la commande 

-- ALTER TABLE suivie de la commande  stock ALTER COLUMN pour changer le type de la colonne spécifique :

ALTER TABLE commande ALTER COLUMN etat_cmd TYPE etat USING etat_cmd::etat;
ALTER TABLE stock ALTER COLUMN etat_cmd TYPE etat USING etat_cmd::etat;

-- Notez que vous devez d'abord créer le type ENUM à l'aide de la commande CREATE TYPE :

DROP TYPE IF EXISTS etat CASCADE;
CREATE TYPE etat AS ENUM ('Livrer', 'Valider', 'En_Cours', 'En_Attente', 'Annuler');

ALTER TABLE commande ADD COLUMN etat_cmd etat;
ALTER TABLE stock ADD COLUMN etat_cmd etat;


ALTER TABLE task ADD COLUMN etat_cmd etat;
ALTER TABLE "task" DROP COLUMN "etat_cmd";


CREATE TYPE stateTask AS ENUM ('Executer', 'Anuller', 'En_Attente', 'Archiver');
ALTER TABLE task ADD COLUMN task_etat stateTask;

ALTER TYPE stateTask RENAME VALUE 'Anuller' TO 'Annuler';

-- Alter TABLE task_history RENAME VALUE 'Anuller' TO 'Annuler';
