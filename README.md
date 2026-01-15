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
- **H2 Database (DEV)**
- **GitHub Actions (CI)**

------------------------------------------------------------

## 🚀 Execução em Ambiente DEV (IntelliJ)

Cada microsserviço deve ser executado com:

SPRING_PROFILES_ACTIVE=dev

**No IntelliJ:**
1. Criar Run Configuration do tipo *Spring Boot*
2. Adicionar:
   - Em *Environment Variables*: `SPRING_PROFILES_ACTIVE=dev`
   - ou em *VM Options*: `-Dspring.profiles.active=dev`
3. Executar nessa ordem:
   1. eurekaserver
   2. msclientes
   3. mscartoes
   4. msavaliadorcredito
   5. mscloudgateway

### 🔗 URLs DEV
- Eureka: http://localhost:8761  
- Gateway: http://localhost:8080  
- Swagger de cada serviço:
  - msclientes → http://localhost:8081/swagger-ui.html  
  - mscartoes → http://localhost:8082/swagger-ui.html  
  - msavaliadorcredito → http://localhost:8083/swagger-ui.html  

------------------------------------------------------------

## 🐳 Execução em PRODUÇÃO (Docker)

No Docker, o profile muda para:

SPRING_PROFILES_ACTIVE=production

### ▶️ Subir toda stack
docker compose up -d --build

### 🔗 URLs PRODUÇÃO
- Eureka → http://localhost:8761
- Gateway → http://localhost:8080/swagger-ui.html

------------------------------------------------------------

## 🔐 Segurança (JWT / Bearer Token)

### DEV
- Segurança simplificada
- Basic Auth via Spring Security
- Swagger liberado

### PRODUÇÃO
- Segurança forte com JWT
- Swagger protegido
- Feign repassa automaticamente o Bearer Token

```
Authorization: Bearer SEU_TOKEN_AQUI
```

------------------------------------------------------------

## 🧪 Testes

### Testes unitários mínimos foram adicionados em cada módulo:

Exemplo:
@SpringBootTest
class MscartoesApplicationTests {
    @Test
    void contextLoads() {}
}

Eles garantem que o ApplicationContext inicializa sem erros.

------------------------------------------------------------

## 🤖 CI/CD – GitHub Actions

Arquivo:
.github/workflows/ci.yml

Pipeline executa:
- mvn clean verify
- valida a build completa
- badge automático no README

Badge Markdown:
![Build Status](https://github.com/cezaravila/avaliador_de_credito/actions/workflows/ci.yml/badge.svg)

------------------------------------------------------------

## 📦 Estrutura do Repositório

avaliador_de_credito/
├── core-config  
├── eurekaserver  
├── msclientes  
├── mscartoes  
├── msavaliadorcredito  
├── mscloudgateway  
├── docker-compose.yml  
└── .github/workflows/ci.yml  

------------------------------------------------------------

## 📚 Objetivo do Projeto

Este projeto foi construído com foco em **portfólio profissional**, seguindo padrões reais do mercado:

- microsserviços independentes  
- comunicação via OpenFeign  
- discovery com Eureka  
- autenticação JWT  
- execução em múltiplos ambientes  
- CI automatizado  

Excelente demonstração de arquitetura moderna para entrevistas.

------------------------------------------------------------

## 👨‍💻 Autor
Cezar de Oliveira Ávila  
Campo Grande – MS  
Developer Programmer
