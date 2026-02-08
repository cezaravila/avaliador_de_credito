# Avaliador de Crédito – Microsserviços
![Build Status](https://github.com/cezaravila/avaliador_de_credito/actions/workflows/ci.yml/badge.svg)

Projeto profissional de microsserviços desenvolvido para portfólio, seguindo padrões reais de mercado, com foco em arquitetura, segurança, mensageria e automação de infraestrutura.

Todo o ambiente é provisionado automaticamente via Docker Compose, sem necessidade de configuração manual.

------------------------------------------------------------

## ✅ Pré-requisitos

- Java 17+
- Maven 3.9+
- Docker
- Docker Compose
- PostgreSQL (executado via Docker)

⚠️ Importante  
Não é necessário configurar manualmente:
- Banco de dados
- Schemas
- Filas
- Exchanges
- DLQ
- Keycloak
- Usuários ou credenciais

Tudo é criado automaticamente ao subir o Docker Compose.

------------------------------------------------------------

## 🧱 Arquitetura do Sistema

```
avaliador_de_credito/
├── core-config
├── eurekaserver
├── mscloudgateway
├── msclientes
├── mscartoes
├── msavaliadorcredito
├── docker
│   ├── keycloak
│   └── rabbitmq
├── docker-compose.yml
└── .github/workflows/ci.yml
```

Módulos principais:

- eurekaserver  
  Service Discovery (Eureka)

- mscloudgateway  
  API Gateway + Segurança

- msclientes  
  Gerenciamento de clientes

- mscartoes  
  Gerenciamento de cartões e limites

- msavaliadorcredito  
  Avaliação de crédito e orquestração

- core-config  
  Módulo de infraestrutura compartilhada contendo:
  - Configuração centralizada do RabbitMQ (exchanges, filas, retry, DLQ)
  - Configuração de segurança (JWT / OAuth2)
  - Configuração base de Swagger/OpenAPI

Infraestrutura:

- PostgreSQL
- RabbitMQ (Retry + DLQ)
- Keycloak (OAuth2 / JWT)

------------------------------------------------------------

## 🔁 Fluxo Principal

1. Cliente acessa o Gateway com Bearer Token
2. Gateway valida o token no Keycloak
3. Requisição é roteada para o microsserviço
4. Comunicação síncrona via OpenFeign
5. Eventos assíncronos via RabbitMQ
6. Falhas transitórias usam Retry
7. Falhas permanentes vão para DLQ

------------------------------------------------------------

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.x
- Spring Cloud
- Spring Security
- OAuth2 / JWT
- OpenFeign
- Eureka Server
- RabbitMQ
- PostgreSQL
- Flyway
- Keycloak
- Docker
- Docker Compose
- GitHub Actions (CI)

------------------------------------------------------------

## 🗄️ Banco de Dados

- PostgreSQL executado via Docker
- Um schema por microsserviço
- Controle de versão com Flyway
- Criação automática de tabelas e schemas

Obs: Para usar esse banco junto com o Intellij é preciso instalar e abrir o Postgre, colocar nele o usuário como 'postgres', e senha 'admin'. E criar o banco de dados com nome  'avaliador_credito'. Feito isso quando você der o run pela primeira vez o flyway vai verificar se as tabelas já existem, se existe não faz nada, mas se não existir vai criar as tabelas e todas as colunas. Depois disso é só inserir dados.

------------------------------------------------------------

## 🔐 Segurança

- Autenticação via Keycloak
- Tokens JWT
- Gateway protegido
- Microsserviços validam JWT
- Propagação automática de token via Feign
- Swagger protegido (DEV e PROD)

------------------------------------------------------------

## 🐰 RabbitMQ – Retry e DLQ

Toda a configuração de mensageria (exchanges, filas, retry e DLQ)
é centralizada no módulo core-config e compartilhada entre os
microsserviços.

- Exchange principal
- Fila principal
- Fila de retry (com TTL)
- Dead Letter Queue (DLQ)

Fluxo:
- Erro transitório → Retry automático
- Erro permanente → Mensagem enviada para DLQ

RabbitMQ Management UI:
http://localhost:15672
- username = bootstrap
- password = bootstrap

------------------------------------------------------------

## ⚡ Como testar em 2 minutos

1️⃣ Subir o ambiente:
docker compose up --build -d

2️⃣ Gerar token JWT no Keycloak

3️⃣ Usar o token no Swagger ou Insomnia

4️⃣ Se for usar no Intellij dar o stop nos containers, dixando apenas os containers do keycloak e rabbitmq rodando

---

## 📘 Swagger (via Gateway)

🔐 Requer Bearer Token (JWT)

- Clientes  
http://localhost:8082/msclientes/swagger-ui.html

- Cartões  
http://localhost:8083/mscartoes/swagger-ui.html

- Avaliador de Crédito  
http://localhost:8084/msavaliadorcredito/swagger-ui.html

---

## 🧪 Insomnia (recomendado)

🧠 Fluxo sugerido:

Gerar o token JWT criando uma requisição GET sem URL, ir na aba Auth e escolher OAuth 2.0. E preencher os campos da seguinte forma.
- GRANTE TYPE: Client Credentials
- ACCESS TOKEN URL: http://localhost:8081/realms/mscourserealm/protocol/openid-connect/token
- CLIENT ID: mscredito
- CLIENT SECRET: 2Ng3dHOHa3Ku8dVReeqGu4Y0MntaLoFm

E clicar no botão Fetch Tokens, isso vai gerar o Token para ser usado no proprio Insomnia ou no Swagger, isso funciona no Intellij e no container.

------------------------------------------------------------

## 👨‍💻 Autor

Cezar de Oliveira Avila  
Campo Grande – MS
