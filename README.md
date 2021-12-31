# Cliente/Empréstimo-API (v1.0)

## Resumo

Aplicação feita para Cadastrar clientes soliticitantes de empréstimos, empréstimos e permitir consultas no banco de dados utilizando o banco relacional H2. Para executar a aplicação no terminal, basta usar a linha de comando: mvn spring-boot:run

## Descrição da implementação

A API foi desenvolvida em java 11 e possui dois endpoints:
 - http://localhost:8080/api/v1/clientes
 - http://localhost:8080/api/v1/emprestimos

Vale ressaltar que em nenhum momento da entrada ou saída de dados da implementação, o usuário tem acesso à entidade do banco de dados propriamente dito. Tudo é feito através de DTOs.

Desenvolvi um tratamento de erros personalizado através de annotation @ControlerAdvice, permitindo melhor compreensão dos erros mostrados na tela do usuário.

Para acessar a documentação swagger da aplicação basta utilizar a seguinte url:

 - http://localhost:8080/swagger-cliente-emprestimo-api.html
 
 Além disso, tomei a liberdade de realizar alguns testes unitários para a classe responsável pela regra de negócio e os endpoints
