# language: pt
# encoding: ISO-8859-1

@Pessoa
Funcionalidade: Incluir colaborador
  Como um contador
  Eu quero incluir um nova colaborador
  Para disponibilizá-lo no cálculo da folha de pagamento

  Contexto:
    Dado que exista o cargo "Analista de sistemas" com sigla "ANS"

  Cenário: Incluir um novo colaborador
    Dado uma pessoa com nome "André de Lima e Silva"
    E a data de nascimento é "07/05/1989"
    E o número do CPF é "871.934.776-68"
    E o cargo é "ANS"
    E a remuneração é "2500,00"
    Quando realizo o cadastro do colaborador
    Então o colaborador com nome "André de Lima e Silva" será cadastrado com sucesso

  Cenário: Incluir uma nova pessoa sem CPF
    Dado uma pessoa com nome "André de Lima e Silva"
    E a data de nascimento é "07/05/1989"
    E o número do CPF não é informado
    E o cargo é "ANS"
    E a remuneração é "2500,00"
    Quando realizo o cadastro do colaborador
    Então a pessoa com nome "André de Lima e Silva" não será cadastrada
    E será apresentada uma mensagem:
      """
      O número do CPF é obrigatório
      """

  Cenário: Incluir uma nova pessoa sem data de nascimento
    Dado uma pessoa com nome "André de Lima e Silva"
    E a data de nascimento não é informada
    E o número do CPF é "871.934.776-68"
    E o cargo é "ANS"
    E a remuneração é "2500,00"
    Quando realizo o cadastro do colaborador
    Então a pessoa com nome "André de Lima e Silva" não será cadastrada
    E será apresentada uma mensagem:
      """
      A data de nascimento é obrigatória
      """

  Cenário: Incluir uma nova pessoa com o número do CPF inválido
    Dado uma pessoa com nome "André de Lima e Silva"
    E a data de nascimento é "07/05/1989"
    E o número do CPF é "871.934.776-44"
    E o cargo é "ANS"
    E a remuneração é "2500,00"
    Quando realizo o cadastro do colaborador
    Então a pessoa com nome "André de Lima e Silva" não será cadastrada
    E será apresentada uma mensagem:
      """
      O número do CPF é inválido
      """