CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,

    PRIMARY KEY(id),
    UNIQUE INDEX username_email (username, email)
);


CREATE TABLE course (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)
);


CREATE TABLE topic (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    status VARCHAR(100) NOT NULL,
    active BOOLEAN,

    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topic_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_topic_course_id FOREIGN KEY (course_id) REFERENCES course (id)
);


CREATE TABLE reply (
    id BIGINT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    solution BOOLEAN,

    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,

    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_reply_topic_id FOREIGN KEY (topic_id) REFERENCES topic (id),
    CONSTRAINT fk_reply_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);
