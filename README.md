# Projeto: Sistema de Busca de Produtos

Este projeto foi desenvolvido como parte de um desafio técnico e tem como objetivo demonstrar habilidades práticas no desenvolvimento de sistemas backend e frontend, integrando bancos de dados e utilizando boas práticas de desenvolvimento.

## Visão Geral

O sistema consiste em um backend implementado em **Spring Boot** e um frontend conectado ao backend para exibir os dados. A aplicação realiza a busca de produtos em um banco de dados, ordenados pelo preço, e retorna os resultados em um formato estruturado.

---

## Funcionalidades

- **Endpoint para listar produtos**:

  - Retorna uma lista de produtos com nome e preço ordenados pelo preço (do menor para o maior).
  - Responde com códigos HTTP adequados, como `200`, `204` (no content), e `500` (erro interno).

- **Endpoint para cadastrar produto**:

  - Permite a adição de novos produtos ao banco de dados.
  - Recebe um objeto JSON com os detalhes do produto (nome, preço, descrição e disponibilidade).
  - Responde com códigos HTTP adequados, como `201` (created), `400` (bad request), e `500` (erro interno).

- **Documentação com Swagger**:
  - Interface para interação e teste dos endpoints.
  - Documentação clara e detalhada dos parâmetros e respostas.

---

## Tecnologias Utilizadas

### Backend:

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
- **PostgreSQL** para persistência de dados
- **Lombok** para simplificar código repetitivo
- **Swagger (SpringDoc OpenAPI)** para documentação

### Frontend:

- Conexão HTTP com backend para exibir resultados (detalhes podem variar dependendo do objetivo específico).

---

## Estrutura do Projeto

### Backend:

```plaintext
OAKTecnologia.estagio
├── controllers
│   ├── FindProductsController.java    # Controlador para o endpoint de busca
│   └── CadastroController.java        # Controlador para o endpoint de cadastro
├── DTO
│   └── ProdutoResponseDTO.java        # Classe DTO para a resposta do endpoint
├── entities
│   └── ProdutoEntity.java             # Entidade JPA para o produto
├── repository
│   └── CadastroAndFindRepository.java # Interface JPA para cadastro e busca de dados
├── service
│   ├── FindProductService.java        # Camada de serviço para lógica de negócios de busca
│   └── CadastroService.java           # Camada de serviço para lógica de negócios de cadastro
├── security
│   ├── SecurityConfig.java            # Configurações de segurança
│   └── WebConfig.java                 # Configurações web
├── Application.java                   # Classe principal para rodar a aplicação
```

---

## Instalação e Execução

### Pré-requisitos:

1. **Java 17** ou superior.
2. **Maven** para gerenciamento de dependências.
3. **PostgreSQL** configurado com as credenciais necessárias.
4. **Postman** (opcional, para testes dos endpoints).

### Passos:

1. Clone este repositório:

   ```bash
   git clone https://github.com/LuizFernandoCSilva/OAK.git
   ```

2. Configure o banco de dados no arquivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compile e execute o projeto:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a documentação do Swagger:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Endpoints

### GET `/find`

- **Descrição**: Retorna uma lista de produtos com nome e preço ordenados pelo menor preço.

#### Respostas:

- **200 OK**: Lista de produtos.

  ```json
  [
    {
      "produtoName": "Notebook Dell Inspiron",
      "preco": 3500.5
    },
    {
      "produtoName": "Monitor Samsung",
      "preco": 899.9
    }
  ]
  ```

- **204 No Content**: Nenhum produto encontrado.

- **500 Internal Server Error**: Erro interno ao buscar os produtos.

### POST `/cadastro/produto`

- **Descrição**: Adiciona um novo produto ao banco de dados.

#### Requisição:

- **Corpo**: Objeto JSON com os detalhes do produto.

  ```json
  {
    "nome": "Nome do Produto",
    "preco": 100.0,
    "descricao": "Descrição do Produto",
    "disponibilidade": true
  }
  ```

#### Respostas:

- **201 Created**: Produto criado com sucesso.

  ```json
  {
    "nome": "Nome do Produto",
    "preco": 100.0
  }
  ```

- **400 Bad Request**: Requisição inválida.

- **500 Internal Server Error**: Erro interno ao cadastrar o produto.

---

## Exemplo de Fluxo do Código

1. O **FindProductsController** recebe uma requisição GET no endpoint `/find`.
2. A requisição é passada para o **FindProductService**, que acessa o **CadastroAndFindRepository**.
3. O **repository** executa uma consulta no banco de dados, buscando os produtos ordenados pelo preço.
4. Os resultados são convertidos para objetos `ProdutoResponseDTO` e retornados ao cliente.

5. O **CadastroController** recebe uma requisição POST no endpoint `/cadastro/produto`.
6. A requisição é passada para o **CadastroService**, que valida e processa os dados.
7. O **CadastroAndFindRepository** salva o novo produto no banco de dados.
8. O produto criado é retornado ao cliente como resposta.

---

## Contato

- **Nome**: Luiz Fernando Costa Silva
- **E-mail**: luiz.fernandocsilva17@gmail.com
- **LinkedIn**: [http://www.linkedin.com/in/lfcsilva](http://www.linkedin.com/in/lfcsilva)
- **GitHub**: [https://github.com/LuizFernandoCSilva](https://github.com/LuizFernandoCSilva)

---

Espero que este projeto demonstre minhas habilidades técnicas e minha capacidade de organização para desenvolvimento de software. Agradeço pela oportunidade de compartilhar este trabalho!
