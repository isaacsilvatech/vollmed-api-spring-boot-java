# API de Agendamento de Consultas Médicas

Esta aplicação é uma **API REST** para gerenciamento de médicos, pacientes e consultas médicas.

O objetivo do projeto é demonstrar uma arquitetura backend organizada, com separação clara de responsabilidades, aplicação de boas práticas e foco em **manutenibilidade**.

---

## 🚀 Funcionalidades

- Cadastro, atualização, listagem e inativação de médicos
- Agendamento de consultas médicas
- Cancelamento de consultas
- Validações de regras de negócio (horário de funcionamento, disponibilidade, etc.)
- Persistência de dados com banco relacional
- Versionamento de banco de dados

---

## 🧱 Arquitetura

O projeto segue uma abordagem inspirada em **DDD pragmático**, com divisão em camadas:

- **Controller**: camada de entrada (REST)
- **Application / Use Cases**: orquestração dos casos de uso
- **Domain**: regras de negócio e entidades
- **Infrastructure**: persistência e configurações técnicas

A ideia é manter o código simples, explícito e fácil de evoluir.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17** – linguagem principal
- **Spring Boot 3** – framework base da aplicação
- **Maven** – gerenciamento de dependências e build
- **MySQL** – banco de dados relacional
- **Hibernate (JPA)** – ORM para persistência
- **Flyway** – versionamento e migração de banco de dados
- **Lombok** – redução de boilerplate
- **Docker** – containerização da aplicação e dependências

---

## 🐳 Executando com Docker

A aplicação pode ser executada utilizando Docker para facilitar o setup do ambiente.

```bash
docker-compose up
```

Isso irá subir:
- Banco de dados MySQL

---

## 📌 Observações

- O projeto prioriza **clareza e simplicidade** em vez de seguir padrões de forma dogmática.
- Decisões de acoplamento foram feitas de forma consciente, considerando custo x benefício.
- A estrutura permite evolução gradual conforme o sistema cresce.

---

## 👨‍💻 Autor

Projeto desenvolvido para fins de estudo e demonstração de boas práticas em backend Java com Spring Boot.

