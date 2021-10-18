# Desafio Módulo 3 - Bootcamp Java Alura
## Projeto: Api Livraria Online

**Requisitos mínimos**

Deve ser adicionado o módulo do Spring Data JPA no projeto, bem como o driver de algum banco de dados de sua preferência, como por exemplo o MySQL.

Será necessário realizar as configurações do banco de dados via propriedades no arquivo application.properties`, bem como realizar o mapeamento das entidades JPA da API.

Você deve utilizar também o Flyway como ferramenta de migration, para o controle da evolução do schema do banco de dados da API.

Lembre-se também de utilizar o recurso de paginação e ordenação nas funcionalidades de listagem de autores e de livros.

Por fim, considere utilizar o Git/GitHub para versionar o código fonte da sua aplicação, conforme foi demonstrado durante as aulas.

**Nova funcionalidade**

Além de implementar a camada de persistência com o Spring Data JPA, você também deverá implementar uma nova funcionalidade, que será um relatório de quantidade de livros publicados por autor.

Essa funcionalidade deverá retornar um JSON com os dados do relatório para que a aplicação frontend consiga fazer a geração de um gráfico.



A seguir um exemplo de como poderá ser o JSON devolvido pela API nessa funcionalidade:

```
[{
    “autor” : "André da Silva”,
    “quantidadeLivros” : 2,
    “percentual” : 28.57
},
{
    “autor” : "Juliana Carvalho”,
    “quantidadeLivros” : 1,
    “percentual” : 14.29
}]
```

**Testes com Postman**

Utilize a ferramenta Postman para realizar os testes das funcionalidades da API, conforme demonstramos durante as aulas.
