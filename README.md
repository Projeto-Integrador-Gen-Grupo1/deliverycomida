# 🍔 DeliveryFood - API de Delivery de Comida - GoFood

API Backend RESTful para sistema de delivery de comida, desenvolvida com **Spring Boot 4.0.6** e **Java 21**.

## 📋 Documentação do Projeto

Documentação completa disponível no link abaixo:
[📄 Documento de Prototipação das Classes - DeliveryFood](https://drive.google.com/file/d/1O7PggPQ8odpVRxYqI_8qoN3QSVphi62q/view?usp=sharing)

---

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.0.6**
- Spring Data JPA / Hibernate
- Spring Validation
- Spring Web MVC
- **MySQL**
- **Maven**
- **Postman** (testes de API)

---

## 📁 Estrutura do Projeto

```
DeliveryFood/
├── src/main/java/com/generation/delliveryfood/
│   ├── DeliveryFoodApplication.java
│   ├── controller/
│   │   ├── CategoriaController.java
│   │   ├── ProdutoController.java
│   │   └── UsuarioController.java
│   ├── model/
│   │   ├── Categoria.java
│   │   ├── Produto.java
│   │   └── Usuario.java
│   ├── repository/
│   │   ├── CategoriaRepository.java
│   │   ├── ProdutoRepository.java
│   │   └── UsuarioRepository.java
│   └── service/
│       ├── CategoriaService.java
│       ├── ProdutoService.java
│       └── UsuarioService.java
├── src/main/resources/
│   └── application.properties
├── src/test/java/
├── pom.xml
└── DOCUMENTACAO.md
```

---

## 🧩 Entidades

### Usuario
| Campo    | Tipo   | Descrição              |
|----------|--------|------------------------|
| id       | Long   | ID único               |
| nome     | String | Nome completo          |
| cpf      | String | CPF do usuário         |
| usuario  | String | Login                  |
| senha    | String | Senha de acesso        |
| foto     | String | URL da foto            |

### Categoria
| Campo     | Tipo   | Descrição                |
|-----------|--------|--------------------------|
| id        | Long   | ID único                 |
| descricao | String | Nome da categoria        |

### Produto
| Campo        | Tipo       | Descrição              |
|--------------|------------|------------------------|
| id           | Long       | ID único               |
| nome         | String     | Nome do produto        |
| descricao    | String     | Descrição detalhada    |
| preco        | BigDecimal | Preço                  |
| saudavel     | Boolean    | É saudável?            |
| disponivel   | Boolean    | Está disponível?       |
| ingredientes | String     | Ingredientes           |
| categoria    | Categoria  | Categoria (N:1)        |
| usuario      | Usuario    | Usuário (N:1)          |

---

## 🔌 Endpoints da API

### Usuarios — `/usuarios`
| Método | Rota              | Descrição              |
|--------|-------------------|------------------------|
| GET    | `/usuarios`       | Listar todos           |
| GET    | `/usuarios/{id}`  | Buscar por ID          |
| POST   | `/usuarios`       | Cadastrar              |
| PUT    | `/usuarios`       | Atualizar              |
| DELETE | `/usuarios/{id}`  | Excluir                |

### Categorias — `/categorias`
| Método | Rota                                      | Descrição              |
|--------|-------------------------------------------|------------------------|
| GET    | `/categorias`                             | Listar todas           |
| GET    | `/categorias/{id}`                        | Buscar por ID          |
| GET    | `/categorias/descricao/{descricao}`       | Buscar por descrição   |
| POST   | `/categorias`                             | Cadastrar              |
| PUT    | `/categorias`                             | Atualizar              |
| DELETE | `/categorias/{id}`                        | Excluir                |

### Produtos — `/produtos`
| Método | Rota                         | Descrição              |
|--------|------------------------------|------------------------|
| GET    | `/produtos`                  | Listar todos           |
| GET    | `/produtos/{id}`             | Buscar por ID          |
| GET    | `/produtos/nome/{nome}`      | Buscar por nome        |
| POST   | `/produtos`                  | Cadastrar              |
| PUT    | `/produtos`                  | Atualizar              |
| DELETE | `/produtos/{id}`             | Excluir                |

---

## ⚙️ Configuração

1. **Pré-requisitos:** Java 21, MySQL, Maven
2. **Banco de dados:** Crie um schema MySQL ou deixe o Spring criar automaticamente
3. **Configurar** `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_food?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

4. **Executar:**

```bash
./mvnw spring-boot:run
```

---

## 👥 Grupo

- Ana Karoline Costa De Sousa
- Danielle Karen Mendes Caricati
- Felipe Marques
- Paulo Gustavo Pereira de Souza Brito
- Riane Toscano
- Talita Oliveira Santos

---

## 📄 Licença

Projeto acadêmico — Generation Brasil.
