# language: pt
# encoding: UTF-8

@IRRF
Funcionalidade: Calcular Rubrica de Imposto de Renda
  Como um contador
  Eu quero calcular o imposto de renda de uma pessoa
  Para poder realizar o desconto do imposto de renda no seu contracheque

  Contexto:
    Dado que exista o cargo "Analista de Sistemas" com sigla "ANS"
    Dado que exista o cargo "Arquiteto Corporativo" com sigla "ARQ-C"
    E exista a tabela do IRRF com sigla "IRRF/2016" com dedução para dependentes de "189,59" e as faixas:
      | Valor inicial | Valor final | Aliquota | Deducao |
      |               | 1903,98     |          |         |
      | 1903,99       | 2826,65     | 7,5    | 142,80  |
      | 2826,66       | 3751,05     | 15     | 354,80  |
      | 3751,06       | 4664,68     | 22,5    | 636,13  |
      | 4664,69       |             | 27,5    | 869,36  |

  Cenario: Isento do IRRF
    Dado que existe um colaborador com nome "André de Lima e Silva" no cargo "ANS" com remuneração de "1.900,00"
    Quando calculo o imposto de renda do "André de Lima e Silva" usando a tabela do IRRF "IRRF/2016"
    Entao o valor deve ser "0,00"

  Cenario: Quinta faixa do IRRF
    Dado que existe um colaborador com nome "Rafael de Souza Andrade" no cargo "ARQ-C" com remuneração de "15.000,00"
    Quando calculo o imposto de renda do "Rafael de Souza Andrade" usando a tabela do IRRF "IRRF/2016"
    Entao o valor deve ser "3.255,64"

  Cenario: Dedução de dois dependentes
    Dado que existe um colaborador com nome "Adriano Orlando Campestrini" no cargo "ARQ-C" com remuneração de "8.500,00" e "2" dependentes
    Quando calculo o imposto de renda do "Adriano Orlando Campestrini" usando a tabela do IRRF "IRRF/2016"
    Entao o valor deve ser "1.363,87"