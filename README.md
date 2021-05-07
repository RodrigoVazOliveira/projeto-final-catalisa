# ZUPayments
- - - 
### Objetivo:

A Zup possui vários fornecedores fixos (que recebem pagamento todo mês), hoje o processo de verificação de pagamentos de custo fixo é feito manualmente verificando planilhas.
Deste modo, o objetivo é facilitar o acompanhamento desses pagamentos e automatizar a cobrança da área responsável evitando assim que a Zup deixe de realizar algum pagamento.
Para isso, existirá uma base com dados dos fornecedores, suas respectivas categorias de custo e pedido de compra.
Cada fornecedor é de responsabilidade de uma área, com isso teremos também uma base dos responsáveis por estes custos. E a nota fiscal que, caso não seja recebida dentro do prazo, gera a cobrança que será enviada automaticamente para o e-mail do responsável.

- - -

### Funcionalidades:

* Cadastro de responsável;
* Ativar ou desativar responsável;
* Cadastrar fornecedor;
* Ativar ou desativar fornecedor;
* Cadastrar pedido de compra;
* Cancelar pedido de compra;
* Cadastrar nota fiscal;
* Cancelar nota fiscal;
* Emitir um relatório do responsável que não enviou a nota fiscal;
* Gerar relatório de pedidos com responsáveis inativos
* Enviar e-mail para os responsáveis que não enviaram a nota fiscal, fazendo a cobrança.

- - -

### Como usar: 

#### Tabela de endpoints

 ENDPOINT | HTTP METHOD | CORPO | FUNÇÃO
----------| ------------| ------ | -----
/responsaveis/ | POST | JSON | Cadastrar novo responsável
/rsponsaveis/?email=value | PATCH | NO CONTENT | desativar responsável
/fornecedores/ | POST | JSON | cadastrar um novo fornecedor
/fornecedores/?cnpjoucpf=value | PATCH | NO CONTENT | desativar fornecedor
/pedidos/ | POST | JSON | Cadastrar um novo pedido de compra
/pedidos/1/ | PATCH | NO CONTENT | cancelar um pedido de compra 
/notas-fiscais/ | POST | JSON | cadastrar uma nova nota fiscal
/notas-fiscais/1/ | PATCH | NO CONTENT | cancelar uma nota fiscal
/pedidos/responsaveis?ativo=false | GET | NO CONTENT | gerar relatório de pedidos com responsáveis inativos
/pedidos/pendentes?valorMinimo=111&dataInicial=01/05/2021&ativo=true | GET | NO CONTENT | Emitir um relatório do responsável que não enviou a nota fiscal;

#### Forma de envio de JSON de cadastros:

1. Cadastro de responsável:

    ~~~json
    {
        "email": "email@email.com",
        "nomeCompleto": "Nome do responsável",
        "nomeDoProjeto": "Setor do responsável"
    }
    ~~~
2. Cadastro de fornecedor:

    pode ser informado ou campo de CPF ou CNPJ. 

    ~~~json
    {
        "cpf":"38.551.219/0001-51",
        "cnpj":"38.551.219/0001-51",
        "RazaoSocial": "NOme do fornecedor",
        "logradouro":"Rua afonso pena",
        "numero":"25",
        "bairro":"Santa mônica",
        "cidade":"Uberlândia",
        "estado":"Minas Gerais",
        "cep":"30000-000",
        "telefone":"(34) 99999-9999",
        "email":"emailfornecedor@fornecedor.com",
        "categoriaDeCusto":"FACILITIES"
    }
    ~~~

3. Cadastro de nota fiscal:

    ~~~json
    {
        "numeroDaNota": 21,
        "cnpjOuCpfFornecedor":"38.551.219/0001-51",
        "valorAPagar": 2000.00,
        "dataDeEmissao":"03/05/2021",
        "pedidoDeCompras": [21],
        "dataDeEnvio":"07/05/2021",
        "emailDoResponsavel":"email@email.com"
    }
    ~~~

4. Cadastrar pedido de compra:

    ~~~json
    {
        "dataDeVencimento":"21/08/2021",
        "valorAproximado": 30000.00,
        "dataDePagamento": "05/05/2021",
        "emailResponsavel":"email@email.com",
        "dataLimiteEnvio": "15/05/2021",
        "formaDePagamento": "TED_DOC",
        "cnpjOuCpf":"38.551.219/0001-51"
    }
    ~~~

### DOCUMENTAÇÃO:

Swagger UI: http://localhost:8080/swagger-ui.html
