[![Build Status](https://travis-ci.org/maveco69/dojo-bdd.svg?branch=seed)](https://travis-ci.org/maveco69/dojo-bdd)

# Apresentação do Problema #

### Descrição da necessidade enviada pelo cliente para os analistas ###

Preciso de um sistema para cálculo de folha de pagamento dos meus colaboradores. Gostaria que pudesse cadastrar todos meus colaboradores, informando nome, cpf, data de nascimento, cargo e salário.
Como possuo uma empresa com muitos colaboradores, gostaria de uma forma simples de cadastrar todos os usuários de uma única vez.

Gostaria de poder realizar o cálculo da folha dos meus funcionários inicialmente calculando os valores de Imposto de Renda de cada um deles.
O cálculo deve seguir a regra de faixas de imposto conforme previsto pela lei, que diz é formada da seguinte forma:

- Salário de até 1903,98  deve ser classificado como isento
- Salário de até 2826,65 dever ter o percentual de IR de 7,5%
- Salário de até 3751,05  dever ter o percentual de IR de 15%
- Salário de até 4664,68 dever ter o percentual de IR de 22,5%
- Salário acima de 4664,68 dever ter o percentual de IR de 27,5%

A dedução por dependente é de R$ 189,59

Gostaria de poder verificar mensalmente o valor do IR de cada funcionário.


----------
Regras para adicionar caso sobre tempo:

- Gostaria de poder verificar relatório de todos meus colaboradores com seus devidos impostos.
- Foi verificado que na lei, colaboradores que nasceram depois de 1980 devem ser declarados como isentos independente do seu salário.
- Foi identificado também que, caso o colaborador possua dependentes, seu calculo é diferenciado
- Gostaria de poder informar os salários dos meus colaboradores de acordo com o cargo, facilitando o cadastro para precisar informar somente dos colaboradores que possuírem salários diferenciados

## Funcionalidades identificadas ##

- Cadastro de colaboradores
- Cadastro de colaboradores em lote
- Cálculo da folha
- Cálculo de IR
- Cadastro de cargos com salário

## Regras de negócio para cada funcionalidade ##

- Cadastro de colaboradores
	- RN01 - O sistema deve permitir o cadastro de colaboradores, informando nome, cpf, data de nascimento, cargo e salário, sendo todos os campos obrigatórios
- Cadastro de colabores em lote
	- RN02 - O sistema deve permitir o cadastro de colaboradores em lote, permitindo uma forma simplificada de cadastrar vários colaboradores de uma vez
		- RN02.01 - Caso algum dos colaboradores possua dados iconsistentes, os demais devem ser cadastrados e depois informado ao usuário quais colaboradores estavam inconsistentes.

- Cálculo de IR
	- RN03 - O sistema deve permitir o cálculo de IR de um determinado colaborador, sendo possível discriminar de forma fácil qual o valor de IR será deduzido daquele colaborador