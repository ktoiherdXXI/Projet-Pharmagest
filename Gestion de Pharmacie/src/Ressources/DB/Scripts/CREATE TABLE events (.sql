
    DROP TABLE events;

    CREATE TABLE events (
        id VARCHAR(50) NOT NULL,
        name VARCHAR(50) NOT NULL,
        date_time TIMESTAMP NOT NULL,
        state VARCHAR(50) NOT NULL DEFAULT 'waiting',
        PRIMARY KEY (id)
    );