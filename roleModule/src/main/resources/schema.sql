    CREATE TABLE IF NOT EXISTS roles (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

    CREATE TABLE IF NOT EXISTS users (
    created_at TIMESTAMP(6),
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    updated_at TIMESTAMP(6),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    role_id BIGINT REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

