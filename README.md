# Voll.med API

API Rest da aplicação Voll.med, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas.

## 📋 Sobre o Projeto

Este projeto é uma API backend desenvolvida em **Java** utilizando o framework **Spring Boot**. A aplicação serve para gerir o funcionamento de uma clínica médica, permitindo o cadastro de médicos e pacientes, bem como o agendamento de consultas com diversas regras de validação de negócio.

## 🚀 Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias e bibliotecas:

![Java 21](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot 3.5.3](https://img.shields.io/badge/Spring_Boot-3.5.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-blue?style=for-the-badge&logo=spring&logoColor=white) 
![Spring Security](https://img.shields.io/badge/Spring_Security-Auth-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)


![MySQL](https://img.shields.io/badge/MySQL-Database-005C84?style=for-the-badge&logo=mysql&logoColor=white) ![Flyway](https://img.shields.io/badge/Flyway-Migration-CC0200?style=for-the-badge&logo=flyway&logoColor=white)

![Auth0 JWT](https://img.shields.io/badge/Auth0-JWT-EB5424?style=for-the-badge&logo=auth0&logoColor=white)

![SpringDoc](https://img.shields.io/badge/SpringDoc-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

![Lombok](https://img.shields.io/badge/Project-Lombok-BC0200?style=for-the-badge&logo=lombok&logoColor=white)

![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

![Mockito](https://img.shields.io/badge/Mockito-Test-informational?style=for-the-badge&logo=mockito&logoColor=white)

## ✨ Funcionalidades

### 🔐 Autenticação

  * Login de utilizadores com devolução de Token JWT (Bearer Token).

### 👨‍⚕️ Médicos (`/medicos`) ou 🤒 Pacientes (`/pacientes`):

  * Cadastro;
  * Listagem paginada;
  * Atualização de dados cadastrais;
  * Exclusão lógica (inativação do registro).

### 📅 Consultas (`/consultas`)

  * **Agendamento de Consultas**: Inclui validações complexas:
      * Horário de funcionamento da clínica (07:00 às 19:00, seg-sáb);
      * Antecedência mínima de 30 minutos;
      * Verificação se médico e paciente estão ativos;
      * Verificação de conflito de horários para o médico;
      * Restrição de uma consulta por dia para o paciente;
      * Escolha aleatória de médico disponível se não for especificado um ID.
  * **Cancelamento de Consultas**:
      * Requer motivo obrigatório;
      * Antecedência mínima de 24 horas.

## ⚙️ Configuração e Execução

### Pré-requisitos

  * Java JDK 21 instalado;
  * MySQL instalado.

## 🗺️ Estrutura do Projeto

A arquitetura segue boas práticas de organização, separando responsabilidades entre Controladores, Domínio e Infraestrutura.

```
src/main/java/med/voll/api/
├── controller/            # Controladores REST (Endpoints)
├── domain/                # Lógica de negócio e persistência
│   ├── consulta/          # Regras de agendamento e cancelamento
│   ├── endereco/          # Objeto de valor para endereços
│   ├── medico/            # Entidade Médico e repositório
│   ├── paciente/          # Entidade Paciente e repositório
│   └── usuario/           # Autenticação de utilizadores
└── infra/                 # Configurações transversais
    ├── exception/         # Tratamento global de erros
    ├── security/          # Filtros e configurações de segurança JWT
    └── springdoc/         # Configuração do Swagger/OpenAPI
```

-----

### Variáveis de Ambiente

A aplicação utiliza variáveis de ambiente para configurações sensíveis, definidas no ficheiro `application.properties`. Deve configurar as seguintes variáveis no seu sistema ou IDE antes de executar:

| Variável | Descrição |
| :--- | :--- |
| `DB_NAME_VOLL` | Nome da base de dados MySQL. |
| `USERNAME_VOLL` | Utilizador da base de dados. |
| `DB_PASSWORD` | Senha da base de dados. |
| `JWT_SECRET` | Segredo para assinatura do Token JWT. |
| `DATASOURCE_URL` | (Opcional - usado em prod) URL JDBC completa. |

### Executar a Aplicação

1.  **Clone o repositório**;
2.  **Compile e execute (via Maven Wrapper)**.

A aplicação estará disponível em `http://localhost:8080` (configuração padrão do Tomcat).

## 📚 Documentação da API

Após iniciar a aplicação, pode aceder à documentação interativa (Swagger UI) para testar os *endpoints*:

  * **Swagger UI**: `http://localhost:8080/swagger-ui.html`
  * **JSON Docs**: `http://localhost:8080/v3/api-docs`

## 📦 Schemas (Modelos de Dados)

Principais objetos utilizados para envio e receção de dados na API.

### Médicos
* **`DadosCadastroMedico`**: Utilizado para criação. Requer: nome, email, telefone, crm, especialidade e endereço.
* **`DadosAtualizacaoMedico`**: Utilizado para atualização. Campos: id, nome, telefone, endereço.
* **`DadosListagemMedico`**: Dados resumidos para listagem (id, nome, email, crm, especialidade).

### Pacientes
* **`DadosCadastroPaciente`**: Utilizado para criação. Requer: nome, email, cpf, telefone e endereço.
* **`DadosAtualizacaoPaciente`**: Utilizado para atualização. Campos: id, nome, telefone, endereço.
* **`DadosListagemPaciente`**: Dados resumidos para listagem (id, nome, email, cpf).

### Consultas
* **`DadosAgendamentoConsulta`**: Requer `idPaciente`, `data` e opcionalmente `idMedico` ou `especialidade`.
* **`DadosCancelamentoConsulta`**: Requer `idConsulta` e `motivoCancelamento`.

### Outros
* **`DadosEndereco`**: Estrutura comum para médicos e pacientes (logradouro, bairro, cep, cidade, uf, complemento, número).
* **`DadosAutenticacao`**: Login e senha para o endpoint de login.
* **`Pageable`**: Estrutura padrão do Spring para controlo de paginação (page, size, sort).

## 🧪 Testes

O projeto inclui testes unitários e de integração utilizando JUnit e Mockito. Para correr os testes, certifique-se de configurar a variável de ambiente `${DB_NAME_VOLL_TEST}` definida em `application-test.properties`.
## Autor

| [<img loading="lazy" src="https://github.com/user-attachments/assets/b2131622-e32c-40ef-a5b5-1794c019d0c5" width=115><br><sub>Cauan Henrique</sub>](https://github.com/Cauan77) |
| :---: |

## Licença

Este projeto está sob a licença [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0).
