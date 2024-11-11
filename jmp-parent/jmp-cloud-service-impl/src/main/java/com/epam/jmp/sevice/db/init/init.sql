CREATE TABLE bank_schema.user(
  id SERIAL PRIMARY KEY,
  name VARCHAR(20),
  surname VARCHAR(40),
  birthdate DATE
);

CREATE TABLE bank_schema.bankcard(
  id SERIAL PRIMARY KEY,
  number VARCHAR(16),
  type VARCHAR(6),
  CONSTRAINT user_id
        FOREIGN KEY(id)
          REFERENCES bank_schema.user(id)
);

CREATE TABLE bank_schema.subscription(
  id SERIAL PRIMARY KEY,
  bankcard VARCHAR(16),
  startdate DATE
);

INSERT INTO bank_schema.user (name, surname, birthdate) VALUES ('Dzina', 'Andreyeva', '1986-01-06');
INSERT INTO bank_schema.user (name, surname, birthdate) VALUES ('Iryna', 'Krasnova', '1976-01-06');