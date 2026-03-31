# 🛒 E-Commerce API (Backend)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)

## 📌 Sobre o Projeto
Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para um motor de E-commerce. O objetivo é fornecer um backend robusto e escalável capaz de gerenciar todo o fluxo de compras de uma loja virtual: desde o catálogo de produtos e gerenciamento de categorias, até a lógica de carrinho de compras e consolidação de pedidos.

Atualmente, o projeto encontra-se na fase de validação do MVP (Produto Mínimo Viável), com a infraestrutura de banco de dados 100% mapeada e operando na nuvem, camada de acesso a dados implementada e regras de negócio em construção.

---

## 🏗️ Arquitetura

### Padrão de Organização: Package by Features (PBF)

A aplicação utiliza **Package by Features** como estratégia de organização de pacotes, permitindo escalabilidade futura para arquitetura de microserviços sem refatorações significativas.

```
com.natodev.ecommerce
├── domain/
│   ├── product/              # Domínio de Produtos
│   │   ├── domain/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   └── exception/
│   │   ├── application/       # Services
│   │   └── presentation/      # DTOs, Controllers
│   ├── user/                  # Domínio de Usuários
│   ├── cart/                  # Domínio de Carrinho
│   └── order/                 # Domínio de Pedidos
└── shared/
    ├── response/              # Padrão de respostas HTTP
    └── handler/               # Tratamento centralizado de exceções
```

### Padrão Domain-Driven Design (DDD)

A estrutura interna de cada domínio segue princípios de DDD:

- **Domain Layer:** Entidades, Value Objects, Repositórios (contratos) e Exceções de domínio
- **Application Layer:** Serviços que orquestram a lógica de negócio
- **Presentation Layer:** DTOs (Data Transfer Objects) e Controllers que expõem os endpoints

**Benefícios:**
- Isolamento claro de responsabilidades
- Facilita compreensão do domínio de negócio
- Reduz acoplamento entre camadas
- Preparado para evolução para microserviços

---

## 📊 Padrão de Resposta HTTP

Toda resposta da API segue um padrão padronizado através da classe `ApiResponse<T>`:

### Sucesso (200, 201, etc.)
```json
{
  "success": true,
  "message": "Requisição processada com sucesso!",
  "data": {
    "usuarioId": "550e8400-e29b-41d4-a716-446655440000",
    "nome": "João Silva",
    "email": "joao@example.com",
    "dataCriacao": "2025-03-31T10:30:00"
  },
  "timestamp": "2025-03-31T10:30:00"
}
```

### Erro (400, 404, 409, 500, etc.)
```json
{
  "success": false,
  "message": "E-mail usuario@example.com já está cadastrado!",
  "data": null,
  "timestamp": "2025-03-31T10:30:00"
}
```

**Vantagens desta abordagem:**
- Resposta consistente em toda a API
- Contexto claro sobre sucesso/falha
- Timestamp padronizado para auditoria
- Facilita parsing no cliente

---

## 🛡️ Tratamento de Exceções

Implementado através de `@ControllerAdvice` centralizado (`GlobalExceptionHandler`), que padroniza o tratamento de todas as exceções da aplicação.

### Mapeamento de Exceções

| Exceção | Status HTTP | Descrição |
|---------|-------------|-----------|
| `EmailCadastradoException` | 409 Conflict | E-mail já existe no sistema |
| `UsuarioNaoEncontradoException` | 404 Not Found | Usuário não encontrado |
| `CategoriaNaoExisteException` | 404 Not Found | Categoria não encontrada |
| Validações de entrada | 400 Bad Request | Dados inválidos ou incompletos |
| Erros genéricos | 500 Internal Server Error | Erro inesperado no servidor |

**Benefícios:**
- Tratamento centralizado em um único lugar
- Padrão consistente de respostas de erro
- Facilita manutenção e evolução
- Pronto para escalabilidade em microserviços

---

## 🧠 Decisões Técnicas e Trade-offs

### 1. Chaves Primárias Universais (UUID)

**Decisão:** Utilizar `UUID` em vez de IDs sequenciais.

**Trade-offs:**
- ✅ Evita ataques de enumeração
- ✅ Preparado para distribuição (sharding)
- ✅ Mantém privacidade dos dados
- ❌ Maior uso de memória (16 bytes vs 8 bytes de Long)
- ❌ Performance ligeiramente menor em buscas

**Quando vale a pena:** E-commerce é crítico em segurança, justificando o overhead.

---

### 2. Proteção de DDL (`ddl-auto=none`)

**Decisão:** Desativar geração automática de tabelas via Hibernate.

**Trade-offs:**
- ✅ Controle total sobre o schema
- ✅ Previne alterações destrutivas acidentais
- ✅ Versionamento explícito do banco
- ❌ Requer manutenção manual de scripts SQL
- ❌ Sincronização entre código e banco é responsabilidade do dev

**Quando vale a pena:** Produção exige controle rígido; risco de perda de dados justifica o trabalho extra.

---

### 3. Package by Features vs Package by Layer

**Decisão:** Package by Features para preparar escalabilidade futura.

**Trade-offs:**
- ✅ Transição suave para microserviços
- ✅ Domínios isolados e coesos
- ✅ Cada feature é auto-contida
- ❌ Código compartilhado fica em `shared/`
- ❌ Aprendizado maior para devs acostumados com PBL

**Quando vale a pena:** MVP que vai crescer; investimento inicial que economiza refatoração depois.

---

### 4. Padrão de Resposta com Envelope

**Decisão:** Envolver TODA resposta em `ApiResponse<T>`.

**Trade-offs:**
- ✅ Padronização total da API
- ✅ Contexto claro sobre sucesso/falha
- ✅ Facilita logging e auditoria
- ❌ Overhead de dados (campos extras em toda resposta)
- ❌ Parsing adicional no cliente

**Quando vale a pena:** API pública/escalável; consistência vale mais que performance marginal.

---

### 5. GlobalExceptionHandler Centralizado

**Decisão:** Um único `@ControllerAdvice` para toda a API.

**Trade-offs:**
- ✅ Tratamento único e consistente
- ✅ Fácil de manutenção
- ✅ Reutilizável em microserviços
- ❌ Menos flexibilidade por domínio
- ❌ Lógica complexa pode ficar grande

**Quando vale a pena:** MVP e crescimento previsto; flexibilidade futura justifica centralização.

---

## 📚 Modelagem de Dados

### Diagrama Entidade-Relacionamento (ERD)
> **Nota do Desenvolvedor:** Abaixo está a modelagem relacional central que sustenta o ecossistema de compras da aplicação.


**Principais Regras de Relacionamento:**
* **Carrinho de Compras:** Cada `Usuário` possui um único `Carrinho` ativo (*One-to-One*), que atua como um agregador temporário de `ItemCarrinho`.
* **Catálogo:** Os `Produtos` são organizados hierarquicamente através de `Categorias` (*Many-to-One*).
* **Checkout:** O fluxo finaliza na conversão do carrinho em um `Pedido` consolidado, contendo seus respectivos `ItemPedido`, mantendo o histórico de compras do usuário (*One-to-Many*).



## 🚀 Status do Desenvolvimento

### MVP - Phase 1: Foundation (Em Progresso)

- [x] Modelagem de Entidades e Relacionamentos (JPA/Hibernate)
- [x] Scripts SQL DDL e Definição de Constraints
- [x] Integração com PostgreSQL na Nuvem (Supabase)
- [x] Implementação da Camada de Acesso a Dados (Repositories)
- [x] Validação de Contexto e Conexão (Spring Boot Test)
- [x] Implementação de DTOs e Bean Validation
- [x] Construção das Regras de Negócio (Services)
- [x] Padrão de Resposta HTTP (`ApiResponse<T>`)
- [x] Tratamento Centralizado de Exceções (`GlobalExceptionHandler`)
- [ ] Exposição de Endpoints REST (Controllers)
    - [ ] UsuarioController (POST, GET, PUT, DELETE)
    - [ ] ProdutoController (POST, GET)
    - [ ] CarrinhoController (POST, GET, PUT)
    - [ ] PedidoController (POST, GET)
- [ ] Testes Unitários das Services
- [ ] Testes de Integração dos Endpoints

### MVP - Phase 2: Refinement (Planejado)

- [ ] Autenticação e Autorização (JWT)
- [ ] Validações de Negócio mais Complexas
- [ ] Paginação e Filtros nos GET
- [ ] Rate Limiting
- [ ] Documentação com Swagger/OpenAPI

---

---

## 📚 Stack Tecnológico

| Componente | Tecnologia | Versão |
|-----------|-----------|--------|
| **Language** | Java | 25 |
| **Framework** | Spring Boot | 4.0.2 |
| **ORM** | JPA/Hibernate | Spring Data JPA |
| **Database** | PostgreSQL | 16 (via Supabase) |
| **Validation** | Jakarta Validation | 3.0+ |
| **Productivity** | Lombok | Latest |
| **Build Tool** | Maven | 4.0.0 |

---

## ⚙️ Como Executar o Projeto Localmente

### Pré-requisitos

- Java 25+
- Maven 4.0.0+
- PostgreSQL 16+ (ou Supabase)
- Git

### Passos

**1. Clone o repositório:**
```bash
git clone https://github.com/FrancinatoSilva/E-Commerce.git
cd E-Commerce
```

**2. Configure o Ambiente:**

Crie um arquivo `.env` na raiz do projeto:
```properties
DB_URL=jdbc:postgresql://seu-host:5432/postgres
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

Ou configure via `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://seu-host:5432/postgres
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=none
```

**3. Inicialize o Banco de Dados:**

Execute o script DDL localizado em `src/main/resources/scripts/init.sql`.

**4. Execute a Aplicação:**

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📖 Referências e Inspirações

- [Google API Design Guide](https://cloud.google.com/apis/design)
- [Spring Boot Best Practices](https://spring.io/guides)
- [Domain-Driven Design - Eric Evans](https://www.domainlanguage.com/ddd/)
- [Microservices Patterns - Chris Richardson](https://microservices.io/)

---

## 📝 Licença

Este projeto é de uso educacional e de portfólio.

---

**Última atualização:** 31 de março de 2025
