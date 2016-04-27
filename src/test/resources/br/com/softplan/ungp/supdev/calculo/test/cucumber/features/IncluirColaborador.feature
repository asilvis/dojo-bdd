# language: pt
# encoding: UTF-8

Funcionalidade: Incluir Colaborador
	Como um contador 
	Eu quero incluir um novo colaborador
	Para realizar o cálculo de imposto de renda posteriormente

	Contexto:
		Dado que existe o cargo com sigla "ANS" e descricao "Analista de Sistemas"
	
	Cenario: Incluir um novo colaborador com sucesso
		Dado um colaborador com nome "André de Lima e Silva"
		E que a data de nascimento é "07/05/1989"
		E que o CPF é "881.539.870-89"
		Quando cadastro o colaborador
		Entao o colaborador com nome "André de Lima e Silva" será cadastrado com sucesso
		
#	Cenario: Incluir um novo colaborador com CPF incorreto
	Cenario: Permitir incluir somente colaboradores com CPF válido
		Dado um colaborador com nome "Tiago Mussi"
		E que a data de nascimento é "22/08/1988"
		E que o CPF é "111.111.111-11"
		Quando cadastro o colaborador
		Entao o colaborador "Tiago Mussi" não será cadastrado
		E deverá apresentar a seguinte mensagem "Atenção - CPF Inválido"
