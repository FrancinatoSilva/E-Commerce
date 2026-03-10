# 🛒 E-Commerce API (Backend)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)

## 📌 Sobre o Projeto
Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para um motor de E-commerce. O objetivo é fornecer um backend robusto e escalável capaz de gerenciar todo o fluxo de compras de uma loja virtual: desde o catálogo de produtos e gerenciamento de categorias, até a lógica de carrinho de compras e consolidação de pedidos.

Atualmente, o projeto encontra-se na fase de validação do MVP (Produto Mínimo Viável), com a infraestrutura de banco de dados 100% mapeada e operando na nuvem.

---

## 🏗️ Arquitetura e Modelagem de Dados

A aplicação segue o padrão de arquitetura em camadas (Controller, Service, Repository) para garantir o isolamento das regras de negócio e facilitar a manutenção.

### Diagrama Entidade-Relacionamento (ERD)
> **Nota do Desenvolvedor:** Abaixo está a modelagem relacional central que sustenta o ecossistema de compras da aplicação.


**Principais Regras de Relacionamento:**
* **Carrinho de Compras:** Cada `Usuário` possui um único `Carrinho` ativo (*One-to-One*), que atua como um agregador temporário de `ItemCarrinho`.
* **Catálogo:** Os `Produtos` são organizados hierarquicamente através de `Categorias` (*Many-to-One*).
* **Checkout:** O fluxo finaliza na conversão do carrinho em um `Pedido` consolidado, contendo seus respectivos `ItemPedido`, mantendo o histórico de compras do usuário (*One-to-Many*).

---

## 🧠 Decisões Técnicas e Arquiteturais

Para garantir que a aplicação esteja alinhada com as melhores práticas de mercado e pronta para produção, as seguintes decisões foram tomadas na camada de infraestrutura:

1. **Chaves Primárias Universais (UUID):** Todas as entidades utilizam `UUID` em vez de IDs sequenciais. Isso evita ataques de enumeração (onde usuários maliciosos adivinham o ID do pedido de outros clientes) e prepara o banco para futuras estratégias de banco de dados distribuído.
2. **Proteção de DDL (`ddl-auto=none`):** A geração automática de tabelas pelo ecossistema do Hibernate foi desativada. O esquema do banco de dados é rigorosamente versionado e controlado através de scripts SQL manuais, prevenindo alterações destrutivas acidentais em produção.
3. **Persistência Serverless (Supabase):** O banco de dados PostgreSQL está hospedado na infraestrutura do Supabase, garantindo alta disponibilidade, testes de integração reais desde a primeira etapa de desenvolvimento e preparação para deploy em nuvem.
4. **Isolamento de Dados (Spring Data JPA):** Implementação de interfaces *Repository* focadas em suas respectivas tabelas, utilizando *Query Methods* para evitar o acoplamento de consultas SQL rígidas dentro da camada de negócio.

---

## 🚀 Status do Desenvolvimento

- [x] Modelagem de Entidades e Relacionamentos (JPA/Hibernate)
- [x] Scripts SQL DDL e Definição de Constraints
- [x] Integração com PostgreSQL na Nuvem (Supabase)
- [x] Implementação da Camada de Acesso a Dados (Repositories)
- [x] Validação de Contexto e Conexão (Spring Boot Test)
- [ ] Implementação de DTOs e Bean Validation (Próxima Issue)
- [ ] Construção das Regras de Negócio (Services)
- [ ] Exposição de Endpoints REST (Controllers)

---

## ⚙️ Como executar o projeto localmente

**1. Clone o repositório:**
```bash
git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
```

**2. Configure o Ambiente:**
Crie um arquivo `.env` na raiz do projeto contendo as credenciais de acesso ao banco PostgreSQL (Supabase ou Local):
```properties
DB_URL=jdbc:postgresql://seu-host:5432/postgres
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

**3. Inicialize o Banco de Dados:**
Execute o script DDL localizado em `src/main/resources/scripts/init.sql` no seu SGBD preferido para construir a estrutura de tabelas.

**4. Rode a aplicação:**
Execute via terminal ou através da sua IDE (IntelliJ/VS Code):
```bash
mvn spring-boot:run
```