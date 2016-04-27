# language: pt
# encoding: UTF-8


Funcionalidade: Incluir Colaboradores Em Lote
	Como um contador
	Eu quero incluir vários colaboradores em lote
	Para realizar o cálculo de imposto de renda posteriormente de cada colaborador
	
	Cenário: Cadastrar vários colaboradores em lote
		Dado os seguintes colaboradores:
			| Nome 	            | Numero CPF            | Data Nascimento | 
			| Fulano de tal     | 072.067.319-45 | 07/04/1945		  |
			| Beltrano da Silva | 857.543.116-13 | 11/05/1991		  |
		Quando realizo o cadastro de colaboradores em lote
		Entao o colaboradores com nome "Fulano de tal, Beltrano da Silva" será cadastrado com sucesso
		