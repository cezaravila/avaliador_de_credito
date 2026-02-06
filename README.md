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
  Configurações centralizadas reutilizáveis:
  - Segurança (JWT)
  - RabbitMQ
  - OpenAPI / Swagger

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

------------------------------------------------------------

## 🐳 Execução do Projeto

Subir todo o ambiente:

docker compose up -d --build

O Docker Compose cria automaticamente:
- Network
- PostgreSQL + schemas
- RabbitMQ + exchanges + filas + DLQ
- Keycloak + realm + clients + usuários
- Todos os microsserviços

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

Implementado padrão de mercado:

- Exchange principal
- Fila principal
- Fila de retry (com TTL)
- Dead Letter Queue (DLQ)

Fluxo:
- Erro transitório → Retry automático
- Erro permanente → Mensagem enviada para DLQ

RabbitMQ Management UI:
http://localhost:15672

------------------------------------------------------------

## 📦 Estrutura do Projeto

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

------------------------------------------------------------

## 🎯 Objetivo do Projeto

Este projeto demonstra para recrutadores:

- Arquitetura de microsserviços real
- Segurança moderna com JWT e Keycloak
- Mensageria resiliente com Retry e DLQ
- Configuração centralizada
- Infraestrutura automatizada
- Padrões prontos para produção

------------------------------------------------------------

## 👨‍💻 Autor

Cezar de Oliveira Avila  
Campo Grande – MS
