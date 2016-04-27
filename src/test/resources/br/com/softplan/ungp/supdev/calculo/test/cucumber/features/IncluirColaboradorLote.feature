# language: pt
# encoding: UTF-8

@ColaboradorLote
Funcionalidade: Incluir colaborador
  Como um contador
  Eu quero incluir um nova colaborador
  Para disponibilizá-lo no cálculo da folha de pagamento

  Contexto:
    Dado que exista o cargo "Analista de sistemas" com sigla "ANS"

  Cenario: Incluir diversos colaboradores
    Dado os colaboradores:
      | Nome    | Data nascimento | Numero CPF     |
      | André   | 07/05/1989      | 871.934.776-68 |
      | Marcelo | 12/08/1984      | 058.079.710-43 |
      | Júlio   | 01/02/1965      | 139.169.967-03 |
    Quando realizo o cadastro do colaborador em lote
    Então os colaboradores com nome "André, Júlio, Marcelo" serão cadastrado com sucesso


  Cenario: Incluir diversos colaboradores com 1 cpf inválido
    Dado os colaboradores:
      | Nome    | Data nascimento | Numero CPF     |
      | André   | 07/05/1989      | 871.934.776-68 |
      | Marcelo | 12/08/1984      | 058.079.710-43 |
      | Júlio   | 01/02/1965      | 139.169.967-00 |
    Quando realizo o cadastro do colaborador em lote
    Então os colaboradores com nome "André, Marcelo" serão cadastrado com sucesso
    E será apresentada uma mensagem:
      """
      Júlio - O número do CPF é inválido
      """

  Cenario: Incluir diversos colaboradores com 1 cpf inválido e sem data de nascimento
    Dado os colaboradores:
      | Nome    | Data nascimento | Numero CPF     |
      | André   | 07/05/1989      | 871.934.776-68 |
      | Marcelo |                 | 058.079.710-43 |
      | Júlio   | 01/02/1965      | 139.169.967-00 |
    Quando realizo o cadastro do colaborador em lote
    Então os colaboradores com nome "André" serão cadastrado com sucesso
    E será apresentada uma mensagem:
      """
      Marcelo - A data de nascimento é obrigatória
      Júlio - O número do CPF é inválido
      """