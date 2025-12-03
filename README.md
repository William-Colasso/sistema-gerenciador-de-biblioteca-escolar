# 📚 Sistema Gerenciador de Biblioteca Escolar

Este projeto é um sistema completo para gerenciamento de bibliotecas escolares, desenvolvido em **Java Swing** integrado ao **Spring Boot** com **JPA** para persistência. Cada funcionalidade foi desenvolvida em uma branch própria, e cada membro da equipe ficou responsável por um módulo completo (banco, backend e interface Swing).

---

## 👥 Equipe & Responsabilidades

### **William-Colasso**
- Implementação da **feature/emprestimos**
- Desenvolvimento das regras de negócio de:
  - Empréstimo e devolução de livros
  - Controle de disponibilidade
- Integração completa entre Swing, Services e JPA

### **Eike Teodoro**
- Implementação da **feature/cadastro-alunos**
- Modelagem da entidade Aluno
- Desenvolvimento da tela Swing de cadastro/edição
- Criação dos serviços e repositórios relacionados

### **Luciano Zumach**
- Implementação da **feature/cadastro-livros**
- Modelagem da entidade Livro
- Tela Swing de gerenciamento do acervo
- Upload de imagens convertido para Base64
- Integração com o backend via Spring + JPA

### **kalebehsilva-tech**
- Contribuições gerais e suporte ao desenvolvimento
- Participação na estruturação inicial e testes
- Auxílio na integração entre camadas

### **Vinícius Pedro Andreazza**
- Implementação da **feature/administracao**
- Telas administrativas
- Controle de configurações internas e gerenciamento de operadores

---

## 🌿 Estrutura do Projeto (Branches)

| Branch | Responsável | Descrição |
|--------|-------------|-----------|
| **main** | Todos | Versão final estável do projeto |
| **feature/administracao** | Vinícius Pedro Andreazza | Módulo administrativo |
| **feature/cadastro-alunos** | Eike Teodoro | Cadastro e gestão de alunos |
| **feature/cadastro-livros** | Luciano Zumach | Cadastro e gestão de livros |
| **feature/emprestimos** | William-Colasso | Empréstimos e devoluções |
| **feature/relatorios** | (não informado) | Início da geração de relatórios |

---

## 🛠️ Tecnologias Utilizadas

- **Java Swing** — Interface gráfica desktop  
- **Spring Boot** — Backend e APIs  
- **Spring Data JPA / Hibernate** — Persistência  
- **MySQL** (ou outro banco configurado)  
- **Maven** — Build e dependências  

---

## 📦 Funcionalidades

### ✔️ Cadastros
- Cadastro de alunos  
- Cadastro de livros (com capa em Base64)

### ✔️ Operações da Biblioteca
- Empréstimo de livros  
- Devolução  
- Controle automático de disponibilidade

### ✔️ Administração
- Controle interno  
- Gerenciamento de usuários administradores

### ✔️ Relatórios
- Estrutura inicial para relatórios de movimentação e acervo

---

## 🚀 Como Executar o Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/William-Colasso/sistema-gerenciador-de-biblioteca-escolar.git
cd sistema-gerenciador-de-biblioteca-escolar
