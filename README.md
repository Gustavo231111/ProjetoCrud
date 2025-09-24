
# ☺ Autor: Gustavo Accadrolli Farias

# ✎ Projeto CRUD em Java (Cliente + Usuário)

Este projeto é um exemplo didático de aplicação **Java Desktop (Swing)** com **2 CRUDs**:
- **Usuário** (com autenticação via BCrypt)
- **Cliente**

O fluxo principal do sistema é:
1. **LoginView** → autenticação de usuário.
2. **MenuPrincipalView** → menu com acesso aos módulos.
3. **UsuarioView** → cadastro e gerenciamento de usuários.
4. **ClienteView** → cadastro e gerenciamento de clientes.

---

## 🚀 Tecnologias utilizadas
- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- ![Swing](https://img.shields.io/badge/Swing-0081CB?style=for-the-badge&logo=java&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
- ![JDBC](https://img.shields.io/badge/JDBC-07405E?style=for-the-badge&logo=java&logoColor=white)
- [jBCrypt](https://mvnrepository.com/artifact/org.mindrot/jbcrypt) (hash seguro de senhas)

---

## 📂 Estrutura do projeto
```text
src/br/ulbra/
 ├─ dao/         → Classes DAO (AbstractDAO, UsuarioDAO, ClienteDAO)
 ├─ controller/  → Lógica de controle (UsuarioController, ClienteController)
 ├─ model/       → Modelos (Usuario.java, Cliente.java)
 ├─ view/        → Interfaces gráficas (LoginView, MenuPrincipalView, UsuarioView, ClienteView)
 └─ img/         → Ícones
```

---

## ⚙️ Configuração do ambiente
1. Instale o **Java JDK 8+**.
2. Instale o **MySQL** e configure um banco de dados chamado `cruddb1`.
3. Importe o projeto na sua IDE (NetBeans recomendado).
4. Adicione o **MySQL Connector/J** e o **jBCrypt** ao classpath do projeto.
   - Baixar `jbcrypt-0.4.jar`: [Maven Repository](https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.4)
   - Adicionar via **Project Properties → Libraries**.

---

## 🗄️ Banco de dados
Crie o schema e tabelas:
```sql
CREATE DATABASE IF NOT EXISTS cruddb1;
USE cruddb1;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) NOT NULL UNIQUE,
  senha VARCHAR(100) NOT NULL,
  nome VARCHAR(150),
  ativo TINYINT(1) DEFAULT 1
);

CREATE TABLE cliente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(150),
  telefone VARCHAR(30)
);
```

---

## 👤 Criando o primeiro usuário (ADM)

### Opção 1 — Gerar hash manual
Use esta classe para gerar o hash:
```java
import org.mindrot.jbcrypt.BCrypt;
public class HashGenerator {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("admin123", BCrypt.gensalt()));
    }
}
```
Depois insira no banco:
```sql
INSERT INTO usuario (login, senha, nome, ativo)
VALUES ('adm', '$2a$10$HASHGERADO...', 'Administrador', 1);
```

### Opção 2 — Criar automaticamente no código
No `LoginView`, antes de abrir a tela de login, verifique se há usuários e crie o **adm/admin123** caso não exista.

---

## ▶️ Execução
1. Rode o projeto (classe `LoginView` é a principal).
2. Faça login:
   - Usuário: `adm`
   - Senha: `admin123`
3. Após autenticação, o sistema abre o **MenuPrincipalView**.

---

## 🔒 Segurança
- Senhas são armazenadas com **BCrypt**, nunca em texto puro.
- Recomenda-se usar um usuário MySQL dedicado em produção:
```sql
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'senhaSegura';
GRANT ALL PRIVILEGES ON cruddb1.* TO 'appuser'@'localhost';
```

---

## 📖 Próximos Passos / Atividades
- Implementar alteração de senha.
- Adicionar roles (ADMIN / USER) com permissões diferentes.
- Melhorar validações de email/telefone.
- Criar relatórios/exportação de dados (ex.: CSV).

---

## 👨‍🏫 Sobre
Este projeto foi desenvolvido para fins **educacionais**, como exemplo de CRUD com **Java + MySQL + Swing**, servindo de base para práticas de programação fullstack
