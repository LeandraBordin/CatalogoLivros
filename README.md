# Catálogo de Livros — Documentação da API

API REST desenvolvida em **Spring Boot** para gerenciamento de **Autores**, **Categorias** e **Livros**, com documentação interativa via **Swagger / OpenAPI** e controle de acesso baseado em token **JWT**.

## Autenticação e Autorização

Os endpoints da API exigem autenticação via **Bearer Token (JWT)** no cabeçalho `Authorization`, além do cargo/permissão adequado:

```http
Authorization: Bearer <seu_token_jwt>
```

- **`401 Unauthorized`**: Token ausente, inválido ou expirado.
- **`403 Forbidden`**: O usuário autenticado não possui o cargo necessário para acessar o recurso.

---

## Endpoints

### 1. Autores (`/autores`)

Endpoints de gerenciamento de autores.

#### Cadastrar autor

- **Método:** `POST`
- **Caminho:** `/autores`
- **Corpo da requisição (`AutorRequestDTO`):**
  ```json
  {
    "nome": "Robert C. Martin",
    "nacionalidade": "Americana"
  }
  ```
- **Respostas:**
  - `201 Created` — Autor salvo com sucesso
  - `401 Unauthorized` — Não autorizado
  - `403 Forbidden` — Proibido
  - `409 Conflict` — Autor já cadastrado (já existe um autor com o mesmo nome)
  - `500 Internal Server Error` — Erro interno do servidor

#### Listar autores

- **Método:** `GET`
- **Caminho:** `/autores`
- **Respostas:**
  - `200 OK` — Autores listados com sucesso (`List<AutorResponseDTO>`), ou lista vazia se não houver nenhum
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Buscar autores por nome

- **Método:** `GET`
- **Caminho:** `/autores?nome={nome}`
- **Query Parameters:**
  - `nome` (`String`, obrigatório) — Nome do autor para pesquisa
- **Respostas:**
  - `200 OK` — Autores encontrados com sucesso (`List<AutorResponseDTO>`)
  - `404 Not Found` — Nenhum autor encontrado com o nome informado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Buscar autor por ID

- **Método:** `GET`
- **Caminho:** `/autores/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único do autor
- **Respostas:**
  - `200 OK` — Autor encontrado com sucesso
  - `404 Not Found` — Autor não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Atualizar autor

- **Método:** `PUT`
- **Caminho:** `/autores/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único do autor
- **Corpo da requisição (`AutorRequestDTO`):** Os campos enviados substituem os valores atuais.
  ```json
  {
    "nome": "Robert Cecil Martin",
    "nacionalidade": "Americana"
  }
  ```
- **Respostas:**
  - `200 OK` — Autor atualizado com sucesso (`AutorResponseDTO`)
  - `404 Not Found` — Autor não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Deletar autor por ID

- **Método:** `DELETE`
- **Caminho:** `/autores/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único do autor
- **Respostas:**
  - `204 No Content` — Autor deletado com sucesso
  - `404 Not Found` — Autor não encontrado
  - `409 Conflict` — O autor possui livros vinculados e não pode ser excluído
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

---

### 2. Categorias (`/categorias`)

Endpoints de gerenciamento de categorias.

#### Cadastrar categoria

- **Método:** `POST`
- **Caminho:** `/categorias`
- **Corpo da requisição (`CategoriaRequestDTO`):**
  ```json
  {
    "nome": "Arquitetura de Software"
  }
  ```
- **Respostas:**
  - `201 Created` — Categoria salva com sucesso (`CategoriaResponseDTO`)
  - `409 Conflict` — Categoria já cadastrada (já existe uma categoria com o mesmo nome)
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Listar categorias

- **Método:** `GET`
- **Caminho:** `/categorias`
- **Respostas:**
  - `200 OK` — Categorias listadas com sucesso (`List<CategoriaResponseDTO>`), ou lista vazia se não houver nenhuma
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Buscar categoria por ID

- **Método:** `GET`
- **Caminho:** `/categorias/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único da categoria
- **Respostas:**
  - `200 OK` — Categoria encontrada com sucesso (`CategoriaResponseDTO`)
  - `404 Not Found` — Categoria não encontrada
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Atualizar categoria

- **Método:** `PUT`
- **Caminho:** `/categorias/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único da categoria
- **Corpo da requisição (`CategoriaRequestDTO`):** Os campos enviados substituem os valores atuais.
  ```json
  {
    "nome": "Engenharia de Software"
  }
  ```
- **Respostas:**
  - `200 OK` — Categoria atualizada com sucesso (`CategoriaResponseDTO`)
  - `404 Not Found` — Categoria não encontrada
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Deletar categoria por ID

- **Método:** `DELETE`
- **Caminho:** `/categorias/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único da categoria
- **Respostas:**
  - `204 No Content` — Categoria deletada com sucesso
  - `404 Not Found` — Categoria não encontrada
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

---

### 3. Livros (`/livros`)

Endpoints de gerenciamento de livros.

#### Cadastrar livro

- **Método:** `POST`
- **Caminho:** `/livros`
- **Corpo da requisição (`LivroRequestDTO`):**
  ```json
  {
    "titulo": "Clean Code",
    "isbn": "9780132350884",
    "descricao": "A Handbook of Agile Software Craftsmanship",
    "editora": "Prentice Hall",
    "anoPublicacao": 2008,
    "categoriasIds": [1],
    "quantidadeTotal": 10,
    "autoresIds": [1]
  }
  ```
- **Respostas:**
  - `201 Created` — Livro salvo com sucesso (`LivroResponseDTO`)
  - `409 Conflict` — Livro já cadastrado (já existe um livro com o mesmo ISBN)
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Listar livros

- **Método:** `GET`
- **Caminho:** `/livros`
- **Respostas:**
  - `200 OK` — Livros listados com sucesso (`List<LivroResponseDTO>`), ou lista vazia se não houver nenhum
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Buscar livro por ISBN

- **Método:** `GET`
- **Caminho:** `/livros/{isbn}`
- **Path Variables:**
  - `isbn` (`String`) — Código ISBN do livro
- **Respostas:**
  - `200 OK` — Livro encontrado com sucesso (`LivroResponseDTO`)
  - `404 Not Found` — Livro não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Buscar livros com filtros

- **Método:** `GET`
- **Caminho:** `/livros/busca`
- **Query Parameters (todos opcionais e combináveis):**
  - `nome` (`String`) — Filtra pelo nome do livro
  - `autor` (`String`) — Filtra pelo autor
  - `categoria` (`String`) — Filtra pela categoria
- **Exemplo de requisição:**
  ```http
  GET /livros/busca?nome=Clean&autor=Martin&categoria=Arquitetura
  ```
- **Respostas:**
  - `200 OK` — Livros encontrados com sucesso (`List<LivroResponseDTO>`)
  - `404 Not Found` — Nenhum livro encontrado com os filtros informados
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Atualizar livro por ISBN

- **Método:** `PUT`
- **Caminho:** `/livros/{isbn}`
- **Path Variables:**
  - `isbn` (`String`) — Código ISBN do livro
- **Corpo da requisição (`LivroRequestDTO`):** Os campos enviados substituem os valores atuais.
  ```json
  {
    "isbn": "9780132350884",
    "nome": "Clean Code: A Handbook of Agile Software Craftsmanship",
    "autorId": 1,
    "categoriaId": 1
  }
  ```
- **Respostas:**
  - `200 OK` — Livro atualizado com sucesso (`LivroResponseDTO`)
  - `404 Not Found` — Livro não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Deletar livro por ID

- **Método:** `DELETE`
- **Caminho:** `/livros/id/{id}`
- **Path Variables:**
  - `id` (`Long`) — Identificador único do livro
- **Respostas:**
  - `204 No Content` — Livro deletado com sucesso
  - `404 Not Found` — Livro não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

#### Deletar livro por ISBN

- **Método:** `DELETE`
- **Caminho:** `/livros/isbn/{isbn}`
- **Path Variables:**
  - `isbn` (`String`) — Código ISBN do livro
- **Respostas:**
  - `204 No Content` — Livro deletado com sucesso
  - `404 Not Found` — Livro não encontrado
  - `401 Unauthorized` / `403 Forbidden` / `500 Internal Server Error`

---

## Documentação (Swagger / OpenAPI)

Com a aplicação em execução localmente, acesse:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html` _(ou `/swagger-ui/index.html`)_

## Resumo de Códigos HTTP

| Status                      | Descrição                                                                  |
| :-------------------------- | :------------------------------------------------------------------------- |
| `200 OK`                    | Operação concluída com sucesso                                             |
| `201 Created`               | Recurso criado com sucesso                                                 |
| `204 No Content`            | Recurso removido permanentemente com sucesso, sem corpo na resposta        |
| `401 Unauthorized`          | Token ausente, inválido ou expirado                                        |
| `403 Forbidden`             | O usuário não tem o cargo necessário                                       |
| `404 Not Found`             | Recurso ou resultado para os parâmetros informados não encontrado          |
| `409 Conflict`              | Conflito de dados (nome/ISBN já cadastrado ou autor com livros vinculados) |
| `500 Internal Server Error` | Erro interno do servidor                                                   |

## Controle de Acesso (RBAC)

A permissão de execução dos métodos HTTP é controlada pelo cargo (`role`) associado ao token do usuário:

| Cargo           | Métodos Permitidos             | Nível de Acesso                  |
| :-------------- | :----------------------------- | :------------------------------- |
| `PUBLICO`       | `GET`                          | Somente leitura/consulta         |
| `BIBLIOTECARIO` | `GET`, `POST`, `PUT`           | Consulta, cadastro e atualização |
| `ADMIN`         | `GET`, `POST`, `PUT`, `DELETE` | Acesso total (inclui remoção)    |
