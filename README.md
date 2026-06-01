# Delivery de Comida Backend

API REST em Java com Spring Boot para o desafio de backend do Projeto Integrador.

## Como implementar passo a passo

1. Instale o Java 17 e o Maven.
2. Abra esta pasta em uma IDE, como IntelliJ IDEA, Eclipse ou VS Code.
3. Execute o projeto pela classe `DeliveryApplication`.
4. A API ficara disponivel em `http://localhost:8080`.
5. Acesse o banco H2 em `http://localhost:8080/h2-console`.
6. Use a URL JDBC `jdbc:h2:mem:deliverydb`, usuario `sa` e senha vazia.
7. Cadastre primeiro um usuario.
8. Cadastre uma categoria.
9. Cadastre produtos informando o `id` da categoria e o `id` do usuario.
10. Teste o CRUD usando Postman, Insomnia ou Thunder Client.

## Como rodar pelo terminal

```bash
mvn spring-boot:run
```

## Estrutura do projeto

```text
src/main/java/com/projetointegrador/delivery
  controller
    CategoriaController.java
    ProdutoController.java
    UsuarioController.java
  model
    Categoria.java
    Produto.java
    Usuario.java
  repository
    CategoriaRepository.java
    ProdutoRepository.java
    UsuarioRepository.java
  service
    CategoriaService.java
    ProdutoService.java
    UsuarioService.java
  DeliveryApplication.java
src/main/resources
  application.properties
```

## Rotas de usuario

### Cadastrar usuario

`POST /usuarios`

```json
{
  "nome": "Restaurante Vida Leve",
  "usuario": "contato@vidaleve.com",
  "foto": "https://exemplo.com/foto.png",
  "senha": "123456"
}
```

### Listar usuarios

`GET /usuarios`

### Buscar usuario por id

`GET /usuarios/1`

## Rotas de categoria

### Cadastrar categoria

`POST /categorias`

```json
{
  "descricao": "Marmitas saudaveis"
}
```

### Listar categorias

`GET /categorias`

### Buscar categoria por id

`GET /categorias/1`

### Buscar categoria por descricao

`GET /categorias/descricao/marmita`

### Atualizar categoria

`PUT /categorias/1`

```json
{
  "descricao": "Marmitas fitness"
}
```

### Deletar categoria

`DELETE /categorias/1`

## Rotas de produto

### Cadastrar produto

`POST /produtos`

```json
{
  "nome": "Bowl de frango grelhado",
  "descricao": "Arroz integral, legumes e frango grelhado",
  "preco": 29.90,
  "calorias": 430,
  "saudavel": true,
  "disponivel": true,
  "ingredientes": "arroz integral, frango, cenoura, brocolis",
  "categoria": {
    "id": 1
  },
  "usuario": {
    "id": 1
  }
}
```

### Listar produtos

`GET /produtos`

### Buscar produto por id

`GET /produtos/1`

### Buscar produto por nome

`GET /produtos/nome/frango`

### Atualizar produto

`PUT /produtos/1`

```json
{
  "nome": "Bowl de frango grelhado especial",
  "descricao": "Arroz integral, legumes, frango grelhado e molho natural",
  "preco": 32.90,
  "calorias": 460,
  "saudavel": true,
  "disponivel": true,
  "ingredientes": "arroz integral, frango, legumes, molho natural",
  "categoria": {
    "id": 1
  },
  "usuario": {
    "id": 1
  }
}
```

### Deletar produto

`DELETE /produtos/1`

### Recomendacoes saudaveis

`GET /produtos/recomendacoes-saudaveis`

Essa rota usa uma regra especial no `ProdutoService`: recomenda produtos disponiveis que estejam marcados como saudaveis, tenham ate 500 calorias ou possuam palavras-chave como salada, integral, grelhado, natural, fruta, legume ou proteina.

## Tabelas criadas no banco

- `tb_usuarios`
- `tb_categorias`
- `tb_produtos`

## Ordem recomendada para testar

1. `POST /usuarios`
2. `POST /categorias`
3. `POST /produtos`
4. `GET /produtos`
5. `GET /produtos/recomendacoes-saudaveis`
6. `PUT /produtos/1`
7. `DELETE /produtos/1`
