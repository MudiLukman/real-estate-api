CREATE TABLE personnel (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE power_supplies (
    id UUID PRIMARY KEY NOT NULL,
    status VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE phases (
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    personnel_id UUID REFERENCES personnel,
    power_supply_id UUID REFERENCES power_supplies
);

CREATE TABLE houses (
    id UUID PRIMARY KEY NOT NULL,
    number VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    phase_id UUID REFERENCES phases
);

CREATE TABLE tenants (
    id UUID PRIMARY KEY NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    house_id UUID REFERENCES houses
);