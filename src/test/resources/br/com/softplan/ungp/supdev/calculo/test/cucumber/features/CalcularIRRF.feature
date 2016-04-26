# language: pt
# encoding: ISO-8859-1

@Pessoa
Funcionalidade: Calcular Rubrica de Imposto de Renda
  Como um contador
  Eu quero calcular o imposto de renda de uma pessoa
  Para poder realizar o desconto do imposto de renda no seu contracheque

  Contexto:
    Dado que exista o cargo "Analista de Sistemas" com sigla "ANS"
    Dado que exista o cargo "Arquiteto Corporativo" com sigla "ARQ-C"
    E exista a tabela do IRRF com sigla "IRRF/2016" com dedução para dependentes de "187,80" e as faixas:
      | Valor inicial | Valor final | Aliquota | Deducao |
      |               | 1903,98     |          |         |
      | 1903,99       | 2826,65     | 7,5    | 142,80  |
      | 2826,66       | 3751,05     | 15     | 354,80  |
      | 3751,06       | 4664,68     | 22,5    | 636,13  |
      | 4664,69       |             | 27,5    | 869,36  |

  Cenário: Isento do IRRF
    Dado que existe um colaborador com nome "André de Lima e Silva" no cargo "ANS" com remuneração de "1.900,00"
    Quando calculo o imposto de renda do "André de Lima e Silva" usando a tabela do IRRF "IRRF/2016"
    Então o valor deve ser "0,00"

  Cenário: Quinta faixa do IRRF
    Dado que existe um colaborador com nome "Rafael de Souza Andrade" no cargo "ARQ-C" com remuneração de "15.000,00"
    Quando calculo o imposto de renda do "Rafael de Souza Andrade" usando a tabela do IRRF "IRRF/2016"
    Então o valor deve ser "3.255,64"