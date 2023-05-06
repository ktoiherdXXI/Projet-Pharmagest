CREATE TABLE task (
    task_id serial PRIMARY KEY,
    task_name text NOT NULL,
    task_date timestamp NOT NULL,
    task_frequency int NOT NULL
);


CREATE TABLE task_history (
    task_id serial PRIMARY KEY,
    task_name text NOT NULL,
    task_date timestamp NOT NULL,
    task_frequency int NOT NULL,
    task_etat stateTask NOT NULL,
    deleted_date timestamp NOT NULL
);
