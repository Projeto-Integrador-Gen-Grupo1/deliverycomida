# Escopo do Projeto

## Titulo

Delivery de Comida - Backend Projeto Integrador

## Modelo de negocio escolhido

Plataforma de Delivery de Alimentos.

## Descricao geral

O projeto consiste em uma API REST para cadastro e gerenciamento de produtos de um delivery de comida. A aplicacao permite cadastrar usuarios responsaveis pelos produtos, cadastrar categorias de alimentos e realizar o CRUD completo de produtos relacionados a uma categoria e a um usuario.

Tambem existe uma funcionalidade especial no service: recomendacao de produtos saudaveis usando apenas Java, considerando produtos disponiveis, marcados como saudaveis, com poucas calorias ou com palavras-chave relacionadas a alimentacao saudavel.

## Entidades e atributos

### Usuario

- `id`: identificador unico do usuario.
- `nome`: nome completo do usuario.
- `usuario`: e-mail de acesso do usuario.
- `foto`: URL da foto do usuario.
- `senha`: senha do usuario.
- `produtos`: lista de produtos cadastrados pelo usuario.

### Categoria

- `id`: identificador unico da categoria.
- `descricao`: descricao da categoria, por exemplo: Marmitas, Saladas, Bebidas ou Sobremesas.
- `produtos`: lista de produtos vinculados a categoria.

### Produto

- `id`: identificador unico do produto.
- `nome`: nome do produto.
- `descricao`: descricao do produto.
- `preco`: preco do produto.
- `calorias`: quantidade aproximada de calorias.
- `saudavel`: indica se o produto e saudavel.
- `disponivel`: indica se o produto esta disponivel para venda.
- `ingredientes`: ingredientes principais.
- `categoria`: categoria relacionada ao produto.
- `usuario`: usuario responsavel pelo cadastro do produto.

## Relacionamentos

- Uma `Categoria` pode ter muitos `Produtos`.
- Um `Produto` pertence a uma `Categoria`.
- Um `Usuario` pode cadastrar muitos `Produtos`.
- Um `Produto` pertence a um `Usuario`.

## Funcionalidades principais

- Criar, listar, buscar, atualizar e deletar categorias.
- Criar, listar, buscar, atualizar e deletar produtos.
- Cadastrar e listar usuarios.
- Buscar produtos pelo nome.
- Buscar categorias pela descricao.
- Recomendar produtos saudaveis.

## Tecnologias utilizadas

- Java 17.
- Spring Boot.
- Spring Web.
- Spring Data JPA.
- Bean Validation.
- Banco de dados H2 em memoria para desenvolvimento.
- Maven.
