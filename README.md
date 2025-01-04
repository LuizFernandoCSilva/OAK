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

- **React**: Para construir a interface de usuário.
  - Utiliza **React Hooks** para gerenciar o estado e efeitos colaterais de forma eficiente.
  - Componentes funcionais são usados para estruturar a lógica da interface.

- **Axios**: Para realizar requisições HTTP ao backend, buscando dados dos produtos e enviando novos cadastros.


- **Tailwind CSS**: Framework CSS utilitário que permite uma estilização rápida e responsiva. Utilizado para:
  - Criar layouts flexíveis e responsivos.
  - Aplicar estilos como margens, espaçamentos, fontes e cores de forma eficiente.
  - Customização fácil sem a necessidade de escrever CSS complexo.

- **JSON**: Usado para a troca de dados entre o frontend e o backend, principalmente na exibição e envio de informações de produtos.

- **Formulários de Cadastro de Produto**: A interface permite a entrada de dados como nome, preço, descrição e disponibilidade do produto. Esses dados são enviados para o backend para cadastro através de uma requisição POST.

- **Listagem de Produtos**: A interface exibe uma lista de produtos com nome e preço, que são recuperados do backend através de uma requisição GET. Os produtos são apresentados em uma tabela ou lista ordenada por preço, proporcionando uma visão clara dos itens disponíveis. A interação com a interface permite uma visualização simples e eficiente dos produtos, com a possibilidade de navegação fluida entre as páginas, utilizando as funcionalidades do React e do Tailwind CSS para garantir responsividade e boa experiência do usuário.
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
### Frontend:
```plaintext
OAK_FRONTEND
├── public
│   ├── index.html                     # Arquivo principal HTML
│   └── favicon.ico                    # Ícone do site
├── src
│   ├── assets
│   ├── components
│   │   ├── ProductCard.jsx             # Componente de exibição de produto
│   │   └── ProductForm.jsx             # Componente para formulário de cadastro de produto
│   ├── services
│   │   └── api.js                     # Arquivo para definir as requisições HTTP com o backend
│   ├── App.jsx                         # Componente principal do React
│   └── index.jsx                       # Arquivo de entrada do React
│   └── index.css                       # Arquivo de estilos principais
├── package.json                       # Arquivo de dependências do projeto
├── tailwind.config.js                 # Configuração do Tailwind CSS
└── postcss.config.js                  # Configuração do PostCSS

---

## Instalação e Execução

### Pré-requisitos:

1. **Java 17** ou superior.
2. **Maven** para gerenciamento de dependências.
3. **PostgreSQL** configurado com as credenciais necessárias.
4. **Postman** (opcional, para testes dos endpoints).
5. **Node.js** (você pode verificar se o Node.js está instalado com o comando `node -v`).
6. **npm** (gerenciador de pacotes do Node.js).

### Passos - BACKEND:

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

### Passos - FRONTEND:

1. Clone o repositório:

   ```bash
   git clone https://github.com/LuizFernandoCSilva/OAK.git

2. Navegue até a pasta do frontend:
    ```bash
    cd OAK_FRONTEND
3. Inicie o servidor de desenvolvimento do React:
    ```bash
   npm install
   npm run dev


4. Acesse o frontend no seu navegador:
    ```bash
   http://localhost:5173/

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

# Funcionalidades do Frontend

O frontend do projeto foi desenvolvido utilizando React e Tailwind CSS. Ele se comunica com o backend para realizar operações de cadastro e listagem de produtos.

## Funcionalidades principais:

### 1. **Cadastro de Produto**
   - **Formulário de Cadastro**: Permite ao usuário cadastrar um produto informando o nome, preço, descrição e disponibilidade.
   - **Envio de Dados**: O formulário envia os dados para o backend via uma requisição POST para o endpoint de cadastro, onde o produto será armazenado no banco de dados.

### 2. **Listagem de Produtos**
   - **Exibição de Produtos**: A interface exibe todos os produtos cadastrados, mostrando o nome, preço e disponibilidade.
   - **Busca de Produtos**: Os produtos são listados em uma página onde é possível buscar por nome ou outros critérios, permitindo ao usuário visualizar facilmente os produtos cadastrados.

### 3. **Design Responsivo**
   - O frontend foi desenvolvido com **Tailwind CSS**, garantindo um design responsivo que se adapta a diferentes tamanhos de tela (desktop, tablet e mobile).

### 4. **Integração com o Backend**
   - As requisições HTTP para cadastro e listagem de produtos são feitas através da biblioteca `axios` para conectar o frontend ao backend.
   - O sistema exibe dados dinâmicos que são atualizados automaticamente conforme o backend responde às requisições.

### 5. **Componentes Reutilizáveis**
   - O projeto utiliza **componentes reutilizáveis** para o formulário de cadastro e para a exibição de produtos, tornando o código mais modular e fácil de manter.

---

Para mais detalhes sobre a implementação e configuração do frontend, consulte a documentação do projeto ou o código-fonte.


## Contato

- **Nome**: Luiz Fernando Costa Silva
- **E-mail**: luiz.fernandocsilva17@gmail.com
- **LinkedIn**: [http://www.linkedin.com/in/lfcsilva](http://www.linkedin.com/in/lfcsilva)
- **GitHub**: [https://github.com/LuizFernandoCSilva](https://github.com/LuizFernandoCSilva)

---

Espero que este projeto demonstre minhas habilidades técnicas e minha capacidade de organização para desenvolvimento de software. Agradeço pela oportunidade de compartilhar este trabalho!
