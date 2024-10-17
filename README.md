# Agregador de Investimentos

API RESTful para gerenciar contas de investimentos. Ele permite criar usuários, associar contas com diferentes tipos de investimentos, adicionar investimentos às contas e saber o valor total do investimento.

## Funcionalidades

- **Criar Usuário**: Cadastre um novo usuário.
  ```json
  POST: localhost:8080/v1/users
  
  {
    "username" : "exemplo",
    "email" : "exemplo@email.com",
    "password" : "exemplo"
  }


- **Criar Conta de Investimentos**: Crie uma conta de investimentos associada a um usuário.
  ```json
  POST: localhost:8080/v1/users/{userId}/accounts

  {
    "description" : "exemplo de descrição",
    "street" : "exemplo de nome de rua",
    "number" : 999
  }

- **Criar Ação**:
  ```json
  POST: localhost:8080/v1/stocks

  {
    "stockId" : "EXPL0",
    "description" : "Exemplo"
  }

  
- **Adicionar Investimentos**: Adicione diferentes tipos de investimentos a uma conta.
  ```json
  POST: localhost:8080/v1/accounts/{accountId}/stocks

  {
    "stockId" : "EXPL0",
    "quantity" : 50
  }

  
- **Operações CRUD**: Além das funcionalidades específicas acima, a API implementa as operações CRUD (Create, Read, Update, Delete).

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **[Brapi Api](https://brapi.dev/)**




