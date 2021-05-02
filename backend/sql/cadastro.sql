CREATE TABLE user (
                        id bigint NOT NULL,
                        created_at datetime(6) DEFAULT NULL,
                        updated_at datetime(6) DEFAULT NULL,
                        birth_date datetime(6) NOT NULL,
                        cpf varchar(11) COLLATE utf8_swedish_ci NOT NULL,
                        email varchar(100) COLLATE utf8_swedish_ci DEFAULT NULL,
                        gender varchar(6) COLLATE utf8_swedish_ci DEFAULT NULL,
                        name varchar(60) COLLATE utf8_swedish_ci NOT NULL,
                        nationality varchar(60) COLLATE utf8_swedish_ci DEFAULT NULL,
                        naturalness varchar(60) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

ALTER TABLE user
    ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_2qv8vmk5wxu215bevli5derq (cpf);

ALTER TABLE user
    MODIFY id bigint NOT NULL AUTO_INCREMENT;
COMMIT;