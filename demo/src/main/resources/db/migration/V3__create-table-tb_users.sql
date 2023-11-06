CREATE TABLE tb_users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,

    CHECK (role in ('ADMIN', 'USER'))
)