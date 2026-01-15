# 🏦 Avaliador de Crédito

![Build Status](https://github.com/cezaravila/avaliador_de_credito/actions/workflows/ci.yml/badge.svg)

Projeto de microsserviços com Spring Boot, Spring Cloud, Eureka, API Gateway, Keycloak, Docker e integração contínua com GitHub Actions.

O objetivo deste repositório é demonstrar um sistema completo de microsserviços para avaliação de crédito, com:

- Autenticação via JWT (Bearer Token)
- Descoberta de serviços (Eureka)
- API Gateway
- Comunicação entre micros com Feign
- Perfis de execução (dev/prod)
- Docker Compose para orquestração
- Pipeline CI (Maven + GitHub Actions)

---

## 🚀 Tecnologias

| Categoria      | Tecnologias |
|---------------|-------------|
| Linguagem     | Java 17 |
| Framework     | Spring Boot, Spring Cloud |
| API Docs      | Swagger / Springdoc |
| Segurança     | Spring Security + OAuth2 JWT + Keycloak |
| Service Discovery | Eureka Server |
| Routing / API | Spring Cloud Gateway |
| Comunicação entre Micros | OpenFeign |
| Contêineres   | Docker / Docker Compose |
| CI/CD         | GitHub Actions |
| Testes        | JUnit 5 |

---

## 🏗️ Arquitetura

O projeto é composto por múltiplos módulos:

core-config
eurekaserver
msclientes
mscartoes
msavaliadorcredito
mscloudgateway


Fluxo básico de requisição (exemplo):

Cliente → API Gateway → msavaliadorcredito → msclientes / mscartoes


- O **API Gateway** atua como ponto de entrada.
- Serviços se descobrem via **Eureka**.
- Chamadas internas utilizam **Feign Clients** com propagação automática do token JWT.
- Cada microserviço pode ter seu próprio Swagger para documentação.

---

## 🧪 Testes

Testes unitários básicos estão configurados para todos os módulos.  
Em especial, o `mscartoes` possui um teste simples que garante que o módulo está configurado corretamente (sem subir contexto completo).

Você pode rodar:

```bash
mvn clean verify

Ou de forma isolada em um módulo:

mvn -pl mscartoes test

🔧 Como rodar
🟢 1. Ambiente de Desenvolvimento (DEV)

No IntelliJ:
 1. Defina o profile como dev
     Nas configurações de run:

