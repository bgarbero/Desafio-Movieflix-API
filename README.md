# MovieFlix API

API REST para um sistema de catálogo e avaliação de filmes, desenvolvida como desafio de implementação do curso **Java Spring Professional** da [DevSuperior](https://devsuperior.com.br).

## Sobre o projeto

MovieFlix é uma plataforma de filmes onde usuários autenticados podem navegar pelo catálogo, filtrar por gênero e registrar avaliações. O acesso é diferenciado por perfil: visitantes podem apenas listar e visualizar filmes, enquanto membros também podem publicar avaliações.

### Modelo conceitual

```
User (1) ──── (*) Review (*) ──── (1) Movie (*) ──── (1) Genre
  └── (*) Role
```

Entidades: `User`, `Role`, `Movie`, `Genre`, `Review`

## Casos de uso implementados

### Listar filmes
- Listagem paginada de filmes com título, subtítulo, ano e imagem, ordenada alfabeticamente
- Filtragem opcional por gênero via parâmetro `genreId`
- Acessível para visitantes e membros

### Visualizar detalhes do filme
- Retorna título, subtítulo, ano, imagem, sinopse e avaliações com o nome do autor
- Acessível para visitantes e membros

### Avaliar filme
- Apenas membros (`ROLE_MEMBER`) podem registrar avaliações
- Texto da avaliação é obrigatório (validação com Bean Validation)
- A avaliação é associada automaticamente ao usuário autenticado

## Tecnologias

- Java 25
- Spring Boot 4.0.6
- Spring Security + OAuth2 (Authorization Server / Resource Server)
- Spring Data JPA / Hibernate
- H2 Database (perfil de testes)
- Bean Validation (Jakarta)
- Maven

## Segurança

A autenticação utiliza **OAuth2 com Password Flow customizado** (grant type `password`). O token JWT é emitido pelo Authorization Server embutido na própria aplicação.

| Perfil | Permissões |
|---|---|
| `ROLE_VISITOR` | Listar filmes, visualizar detalhes |
| `ROLE_MEMBER` | Tudo acima + registrar avaliações |

## Endpoints

| Método | Rota | Perfil mínimo | Descrição |
|---|---|---|---|
| `GET` | `/genres` | VISITOR | Lista todos os gêneros |
| `GET` | `/movies?genreId={id}` | VISITOR | Lista filmes paginados, com filtro opcional por gênero |
| `GET` | `/movies/{id}` | VISITOR | Detalhes do filme com avaliações |
| `POST` | `/reviews` | MEMBER | Registra uma avaliação |
| `GET` | `/users/me` | VISITOR | Dados do usuário autenticado |

## Como executar

### Pré-requisitos

- Java 25+
- Maven 3.9+

### Rodando localmente

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/movieflix-api.git
cd movieflix-api

# Executar com Maven
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080` usando o banco H2 em memória.

### Console H2

Disponível em `http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:mem:testdb`

## Testando com Postman

O repositório inclui a collection e o environment do Postman prontos para uso:

- `Desafio Movieflix casos de uso.postman_collection.json`
- `Movieflix env.postman_environment.json`

Importe os dois arquivos no Postman e utilize as credenciais já configuradas no environment para obter o token e testar os endpoints.

## Usuários de teste (seed)

| E-mail | Senha | Perfil |
|---|---|---|
| bob@gmail.com | 123456 | VISITOR |
| ana@gmail.com | 123456 | MEMBER |

## Estrutura do projeto

```
src/main/java/com/devsuperior/movieflix/
├── config/              # Configuração do OAuth2 (Authorization e Resource Server)
│   └── customgrant/     # Implementação do Password Flow customizado
├── controllers/         # Camada REST (Movie, Genre, Review, User)
│   └── exceptions/      # Handler global de exceções
├── dto/                 # Objetos de transferência de dados
├── entities/            # Entidades JPA
├── projections/         # Interfaces de projeção para queries otimizadas
├── repositories/        # Repositórios Spring Data
└── services/            # Regras de negócio
```

## Autor

**Bruno da Silva Garbero** 
[LinkedIn](https://www.linkedin.com/in/bruno-garbero/) 
