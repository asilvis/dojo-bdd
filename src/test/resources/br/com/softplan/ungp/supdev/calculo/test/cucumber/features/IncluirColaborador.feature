# language: pt
# encoding: ISO-8859-1

@Pessoa
Funcionalidade: Incluir colaborador
  Como um contador
  Eu quero incluir um nova colaborador
  Para disponibiliz�-lo no c�lculo da folha de pagamento

  Contexto:
    Dado que exista o cargo "Analista de sistemas" com sigla "ANS"

  Cen�rio: Incluir um novo colaborador
    Dado uma pessoa com nome "Andr� de Lima e Silva"
    E a data de nascimento � "07/05/1989"
    E o n�mero do CPF � "871.934.776-68"
    E o cargo � "ANS"
    E a remunera��o � "2500,00"
    Quando realizo o cadastro do colaborador
    Ent�o o colaborador com nome "Andr� de Lima e Silva" ser� cadastrado com sucesso

  Cen�rio: Incluir uma nova pessoa sem CPF
    Dado uma pessoa com nome "Andr� de Lima e Silva"
    E a data de nascimento � "07/05/1989"
    E o n�mero do CPF n�o � informado
    E o cargo � "ANS"
    E a remunera��o � "2500,00"
    Quando realizo o cadastro do colaborador
    Ent�o a pessoa com nome "Andr� de Lima e Silva" n�o ser� cadastrada
    E ser� apresentada uma mensagem:
      """
      O n�mero do CPF � obrigat�rio
      """

  Cen�rio: Incluir uma nova pessoa sem data de nascimento
    Dado uma pessoa com nome "Andr� de Lima e Silva"
    E a data de nascimento n�o � informada
    E o n�mero do CPF � "871.934.776-68"
    E o cargo � "ANS"
    E a remunera��o � "2500,00"
    Quando realizo o cadastro do colaborador
    Ent�o a pessoa com nome "Andr� de Lima e Silva" n�o ser� cadastrada
    E ser� apresentada uma mensagem:
      """
      A data de nascimento � obrigat�ria
      """

  Cen�rio: Incluir uma nova pessoa com o n�mero do CPF inv�lido
    Dado uma pessoa com nome "Andr� de Lima e Silva"
    E a data de nascimento � "07/05/1989"
    E o n�mero do CPF � "871.934.776-44"
    E o cargo � "ANS"
    E a remunera��o � "2500,00"
    Quando realizo o cadastro do colaborador
    Ent�o a pessoa com nome "Andr� de Lima e Silva" n�o ser� cadastrada
    E ser� apresentada uma mensagem:
      """
      O n�mero do CPF � inv�lido
      """