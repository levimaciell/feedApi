CREATE TABLE tb_Comments (
    commentId TEXT PRIMARY KEY UNIQUE NOT NULL,
    comment TEXT NOT NULL
)

-----------------------------------------------------------

ALTER TABLE tb_Comments
ADD COLUMN post_id TEXT NOT NULL;

ALTER TABLE tb_Comments
ADD FOREIGN KEY (post_id) REFERENCES tb_posts(id);

-----------------------------------------------------------

ALTER TABLE tb_Comments
ADD COLUMN user_id TEXT NOT NULL;

ALTER TABLE tb_Comments
ADD FOREIGN KEY (user_id) REFERENCES tb_users(id)

----------------------------------------------------------

ALTER TABLE tb_Comments
DROP COLUMN id