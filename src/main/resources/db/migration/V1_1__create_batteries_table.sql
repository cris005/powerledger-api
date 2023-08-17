CREATE TABLE batteries(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(255),
    postcode VARCHAR(255),
    capacity INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);