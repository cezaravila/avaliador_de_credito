-- Tabela de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id          BIGSERIAL PRIMARY KEY,
    cpf         VARCHAR(11) NOT NULL UNIQUE,
    nome        VARCHAR(150) NOT NULL,
    idade       INTEGER NOT NULL
);
