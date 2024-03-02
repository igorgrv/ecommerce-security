# Ecommerce  :convenience_store:
**Tech Challenge - Phase 4 - Graduate/Pós-Graduação**

* GitHub Repository: https://github.com/igorgrv/ecommerce-security
* Swagger: http://localhost:8080/swagger-ui/index.html


## About :book:

Welcome to **Ecommerce **! An innovative project that combines the powerful technologies of:

* Java 17;
* MongoDB;
* Maven;
* Spring Boot;
* Spring Security
* Spring Validation;
* Lombok;



## Goals/Tasks

| Task                                                         | Status |
| ------------------------------------------------------------ | ------ |
| **Login e Registro de Usuário:** Os usuários devem ser capazes de se cadastrar e fazer login no sistema  usando as ferramentas do Spring Security para autenticação e autorização |        |
| **Gestão de Itens:** Os usuários administradores terão acesso a uma tela de gestão de itens, basicamente o controle de cadastro e manutenção de itens, bem como seus |        |
| **Carrinho de Compras:** Os usuários podem adicionar e remover itens do carrinho de compras. O carrinho de compras deve ser persistente e associado ao usuário logado |        |
| **Pagamentos (Simulação):** Implementar uma tela que simule o processo de pagamento, onde os usuários possam visualizar os itens do carrinho e concluir uma compra fictícia. Não é necessário integrar com formas de pagamento reais, apenas uma simulação |        |
| Utilize o framework Spring Boot para criar o sistema.        |        |
| Utilize o Spring Security para implementar o controle de autenticação e autorização. |        |
| Implemente a arquitetura de microsserviços para as funcionalidades de login, gestão de itens, gestão de preços, carrinho de compras e pagamento. Cada funcionalidade deve ser um microsserviço separado. |        |
| Utilize um banco de dados para armazenar informações de usuários, itens, preços e carrinhos de com-pras. Você pode escolher o banco de dados de sua preferência |        |
| Forneça uma documentação adequada do sistema, incluindo instruções de instalação e uso |        |
| O sistema deve ser capaz de lidar com sessões de usuário e manter o estado do carrinho de compras entre as sessões |        |
| Implemente validações adequadas para garantir a segurança e a integridade dos dad﻿﻿﻿os |        |
| Garanta a segurança dos endpoints dos microsserviços usando o Spring Security |        |


## Challenges

* Work with Spring Security;
* Work with NoSQL databases;

## Working with Posgress

```bash
docker run --name ecommerce -e POSTGRES_PASSWORD=12345678 -d -p 5432:5432 postgres

podman run --name ecommerce -e POSTGRES_PASSWORD=12345678 -d -p 5432:5432 postgres
```
