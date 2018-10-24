CREATE TABLE service_directory
	( id bigInt PRIMARY KEY,
      api_url VARCHAR(255),
      jwks_url VARCHAR(255),
      access_token VARCHAR(255),
      last_updated datetime );