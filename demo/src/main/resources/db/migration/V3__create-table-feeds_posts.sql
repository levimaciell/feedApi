CREATE TABLE feeds_posts (
    feed_fk TEXT NOT NULL,
    post_fk TEXT NOT NULL,

    FOREIGN KEY (feed_fk) REFERENCES tb_feeds,
    FOREIGN KEY (post_fk) REFERENCES tb_posts
)