
# Avaliador de Crédito – Microsserviços
![Build Status](https://github.com/cezaravila/avaliador_de_credito/actions/workflows/ci.yml/badge.svg)

Projeto profissional de microsserviços voltado para demonstração em portfólio, incluindo:

- Arquitetura distribuída
- Eureka Service Discovery
- API Gateway
- Spring Cloud OpenFeign
- Microsserviços isolados
- Perfis DEV e PRODUÇÃO
- Autenticação Bearer Token (JWT)
- Execução local (IntelliJ) e Docker
- Pipeline CI com GitHub Actions

------------------------------------------------------------

## 🧱 Arquitetura do Sistema

- **eurekaserver** → Service Discovery
- **msclientes** → gerenciamento de clientes
- **mscartoes** → cartões e limites
- **msavaliadorcredito** → avaliação de crédito
- **mscloudgateway** → API Gateway que centraliza chamadas
- **core-config** → configurações compartilhadas

Fluxo:
1. Cliente chama Gateway (+ token)
2. Gateway valida autenticação (DEV ou PROD)
3. Gateway encaminha para msavaliadorcredito
4. msavaliadorcredito usa Feign para chamar msclientes e mscartoes
5. Retorno agregado e padronizado

------------------------------------------------------------

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Cloud 2023**
- **Spring Security (JWT/Bearer)**
- **OpenFeign**
- **Eureka Server**
- **Docker + Docker Compose**
- **PostgreSQL**
- **Flyway**
- **GitHub Actions (CI)**

------------------------------------------------------------

## 🚀 Execução em Ambiente DEV (IntelliJ)

Cada microsserviço deve ser executado com:

SPRING_PROFILES_ACTIVE=dev


------------------------------------------------------------

## 🗄️ Banco de Dados e Migrations (Branch `sql-version`)

A branch **sql-version** introduz versionamento explícito de banco de dados utilizando **Flyway**,
mantendo **Hibernate apenas como consumidor do schema**.

### Estratégia de Schema
- Um único database PostgreSQL
- **Um schema por microsserviço**
  - `msclientes` → schema `msclientes`
  - `mscartoes` → schema `mscartoes`
- Cada schema possui seu próprio `flyway_schema_history`

### Flyway
- Cada microsserviço contém migrations em `db/migration`
- `V1__*.sql` representa a criação inicial do schema
- Alterações estruturais devem ser feitas via `V2`, `V3`, etc.
- Migrations aplicadas não devem ser editadas

### DEV x PRODUÇÃO
- Mesma lógica de schema em ambos os ambientes
- DEV: conexão via `localhost`
- PRODUÇÃO (Docker): conexão via hostname `postgres`
- A diferença entre ambientes é apenas o profile ativo

### Observação
Nenhuma funcionalidade anterior foi removida.
Esta seção **apenas documenta a evolução técnica da branch `sql-version`**.
