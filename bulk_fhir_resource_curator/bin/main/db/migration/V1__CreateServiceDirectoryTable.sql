CREATE TABLE service_directory
	( id BIGINT PRIMARY KEY AUTO_INCREMENT,
    api_url VARCHAR(255) NOT NULL,
    jwks_url VARCHAR(255) NOT NULL,
    access_token VARCHAR(255) NOT NULL,
    last_updated DATETIME NOT NULL DEFAULT '2000-01-01');