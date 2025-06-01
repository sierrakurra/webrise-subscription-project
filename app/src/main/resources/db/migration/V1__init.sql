CREATE TABLE users (
    id uuid PRIMARY KEY,
    surname varchar(50) NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    password varchar(30) NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now()
);

-- Ввел новую таблицу исходя из ТЗ и возможной бизнес-логики (было непонятно как будут крепиться подписки пользователя)
CREATE TABLE platforms (
    id uuid PRIMARY KEY,
    name varchar(100) NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now()
);

CREATE TABLE subscriptions (
    id uuid PRIMARY KEY,
    user_id uuid NOT NULL,
    platform_id uuid NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    -- идентификатор на платформе подписки, может быть как непосредственно числовым или иным идентификатором,
    -- так и мнемоническим идентификатором (username)
    platform_account_id varchar(100) NOT NULL,
    FOREIGN KEY (platform_id) REFERENCES platforms(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);