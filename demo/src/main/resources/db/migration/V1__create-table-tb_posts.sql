CREATE TABLE tb_posts (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    message TEXT NOT NULL
)

ALTER TABLE tb_posts
ADD COLUMN user_id TEXT NOT NULL;

ALTER TABLE tb_posts
ADD FOREIGN KEY (user_id) REFERENCES tb_users(id);
