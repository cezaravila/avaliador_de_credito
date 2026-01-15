![Build Status](https://github.com/cezaravila/avaliador_de_credito/actions/workflows/ci.yml/badge.svg)

🏦 Sistema de Avaliação de Crédito  
Microserviços com Spring Boot, Spring Cloud, Keycloak, Feign, Eureka, Gateway e Docker

Este projeto implementa um ecossistema completo de microserviços para avaliação de crédito utilizando arquitetura moderna, autenticação via JWT (Keycloak) e comunicação interna com propagação de token entre micros.

Desenvolvido com foco em **boas práticas**, **padronização profissional** e **ambiente dev vs produção bem separados**, ideal para portfólio, estudo e demonstração técnica em entrevistas.

---

## 🚀 Arquitetura Geral

A solução é composta pelos seguintes serviços:

core-config  
eurekaserver  
msclientes  
mscartoes  
msavaliadorcredito  
mscloudgateway  

Fluxo:

CLIENTE → Gateway → Micros → Feign → Token JWT propagado

---

## 🛠️ Tecnologias Utilizadas

- Java 17  
- Spring Boot 3.4.x  
- Spring Cloud  
- Spring Security (OAuth2 Resource Server / JWT)  
- OpenFeign  
- Eureka Server  
- Spring Cloud Gateway  
- Swagger / Springdoc OpenAPI  
- Docker & Docker Compose  
- H2 Database (dev)

---

## 🧩 Microserviços

### msclientes
- CRUD de clientes  
- JWT em produção  
- Swagger ativo  

### mscartoes
- Cadastro e consulta de cartões  
- Banco relacional  
- JWT ativo  

### msavaliadorcredito
- Orquestrador  
- Chamada Feign com propagação de token JWT  
- Endpoint principal: /situacao-cliente  

### Gateway
- Entrada única  
- Validação JWT  
- Roteamento inteligente  

### Eureka
- Registro e descoberta  
- Healthchecks  

### Core-Config
- Configuração de segurança DEV + PROD  
- Swagger liberado em ambos  

---

## 🔐 Segurança

### DEV (IntelliJ)
- Segurança simplificada  
- Basic Auth  
- Sem Keycloak  
- Swagger aberto  

### PRODUÇÃO (Docker)
- Keycloak como Authorization Server  
- Micros como Resource Servers  
- JWT obrigatório  
- Swagger exige Bearer Token  
- Feign repassa token automaticamente  

---

## 🐳 Executando via Docker

mvn clean install  
docker compose up -d --build  

Gateway: http://localhost:8080  
Eureka: http://localhost:8761  

---

