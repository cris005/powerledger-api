CREATE TABLE batteries(
    id uuid PRIMARY KEY,
    name VARCHAR(255),
    postcode VARCHAR(255),
    capacity INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);