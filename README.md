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


