# Backend - Sistema de Recadastramento CCM

Backend da aplicação de gestão de funcionários municipais desenvolvido com Spring Boot 3 e PostgreSQL.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.2.1**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL 16**
- **Lombok** - Redução de boilerplate
- **ModelMapper** - Conversão DTOs ↔ Entidades
- **Maven** - Gerenciamento de dependências

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose (para banco de dados)

## 🛠️ Configuração e Execução

### 1. Banco de Dados com Docker

Inicie o PostgreSQL e pgAdmin:

```bash
cd backend
docker-compose up -d
```

**Acessos:**
- PostgreSQL: `localhost:5432`
  - Database: `recadastramento_db`
  - User: `postgres`
  - Password: `postgres`
- pgAdmin: [http://localhost:5050](http://localhost:5050)
  - Email: `admin@admin.com`
  - Password: `admin`

### 2. Executar a Aplicação

```bash
cd backend
mvn spring-boot:run
```

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

### 3. Build da Aplicação

```bash
mvn clean package
java -jar target/recadastramento-1.0.0.jar
```

## 📂 Estrutura do Projeto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/ccm/recadastramento/
│   │   │   ├── config/           # Configurações (CORS, Beans)
│   │   │   ├── controller/       # Controllers REST
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── entity/           # Entidades JPA
│   │   │   ├── exception/        # Exceptions e Handlers
│   │   │   ├── repository/       # Repositories JPA
│   │   │   ├── service/          # Serviços de negócio
│   │   │   └── RecadastramentoApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-dev.properties
│   └── test/                     # Testes unitários
├── docker-compose.yml            # PostgreSQL + pgAdmin
├── pom.xml                       # Dependências Maven
└── README.md                     # Este arquivo
```

## 🔌 API Endpoints

### Funcionários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/funcionarios` | Lista todos os funcionários |
| GET | `/api/funcionarios/{id}` | Busca funcionário por ID |
| GET | `/api/funcionarios/cpf/{cpf}` | Busca funcionário por CPF |
| POST | `/api/funcionarios` | Cria novo funcionário |
| PUT | `/api/funcionarios/{id}` | Atualiza funcionário |
| DELETE | `/api/funcionarios/{id}` | Remove funcionário |

### Contatos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/contatos/funcionario/{funcionarioId}` | Lista contatos de um funcionário |
| GET | `/api/contatos/{id}` | Busca contato por ID |
| POST | `/api/contatos` | Cria novo contato |
| PUT | `/api/contatos/{id}` | Atualiza contato |
| DELETE | `/api/contatos/{id}` | Remove contato |

## 📝 Exemplos de Requisição

### Criar Funcionário

```json
POST /api/funcionarios
Content-Type: application/json

{
  "cpf": "123.456.789-00",
  "nome": "João da Silva",
  "nomeSocial": "",
  "dataNascimento": "1990-05-15",
  "racaCor": "parda",
  "sexo": "masculino",
  "nacionalidade": "brasileiro",
  "estadoNascimento": "RO",
  "cidadeNascimento": "Porto Velho",
  "telefone": "(69) 99999-9999"
}
```

### Criar Contato

```json
POST /api/contatos
Content-Type: application/json

{
  "funcionarioId": 1,
  "tipo": "email",
  "valor": "joao.silva@email.com",
  "descricao": "Email pessoal",
  "principal": true
}
```

## 🔒 Validações

A API implementa validações robustas:

- **CPF**: Formato `000.000.000-00` e unicidade
- **Telefone**: Formato `(00) 00000-0000`
- **Data de Nascimento**: Deve ser no passado
- **Campos obrigatórios**: Nome, CPF, sexo, nacionalidade, etc.
- **Tipos de Contato**: email, celular ou telefone

## 🧪 Testes

```bash
mvn test
```

## 📦 Profiles

- **default**: Configuração padrão
- **dev**: Profile de desenvolvimento (logs detalhados)

Ativar profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🔧 Configurações Importantes

### application.properties

- Porta: `8080`
- Database URL: `jdbc:postgresql://localhost:5432/recadastramento_db`
- Hibernate DDL: `update` (cria/atualiza tabelas automaticamente)
- Timezone: `America/Sao_Paulo`

## 🐛 Troubleshooting

**Erro de conexão com PostgreSQL:**
```bash
# Verificar se o container está rodando
docker ps

# Reiniciar containers
docker-compose restart
```

**Porta 8080 já em uso:**
```properties
# Alterar em application.properties
server.port=8081
```

## 📄 Licença

Este projeto está sob a licença MIT.

---

🤖 Desenvolvido com auxílio de [Claude Code](https://claude.com/claude-code)
