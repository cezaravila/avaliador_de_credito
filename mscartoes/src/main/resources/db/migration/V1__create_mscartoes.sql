-- Tabela de cartões disponíveis
CREATE TABLE IF NOT EXISTS cartoes (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(100) NOT NULL,
    bandeira_cartao VARCHAR(50) NOT NULL,
    renda           NUMERIC(15,2) NOT NULL,
    limite_basico   NUMERIC(15,2) NOT NULL
);

-- Tabela de cartões vinculados ao cliente
CREATE TABLE IF NOT EXISTS cartao_cliente  (
    id              BIGSERIAL PRIMARY KEY,
    cpf_cliente     VARCHAR(11) NOT NULL,
    id_cartao       BIGINT NOT NULL,
    limite_aprovado NUMERIC(15,2) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_cartao_cliente_cpf ON cartao_cliente (cpf_cliente);
CREATE INDEX IF NOT EXISTS idx_cartao_cliente_id_cartao ON cartao_cliente (id_cartao);