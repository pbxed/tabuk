CREATE TABLE IF NOT EXISTS address (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    houseNameOrNumber VARCHAR(250) NOT NULL,
    addressLine1 VARCHAR(250) NOT NULL,
    addressLine2 VARCHAR(250),
    city VARCHAR(250),
    postCode VARCHAR(250),
    country VARCHAR(250)
);