CREATE TABLE assignments (
  id BINARY(16) DEFAULT (UUID()) NOT NULL,
  name VARCHAR(255) NOT NULL,
  content LONGBLOB,
  PRIMARY KEY (id)
);
