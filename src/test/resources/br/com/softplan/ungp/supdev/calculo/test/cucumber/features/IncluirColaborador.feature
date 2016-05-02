# language: pt
# encoding: UTF-8

Funcionalidade: Incluir Colaborador
Como um contador
Eu quero cadastrar os meus colaboradores
Para realizar o cálculo da folha de pagamento dos colabores


	Contexto:
		Dado que existam os cargos:
		| sigla | descricao            |
		| AS    | Analista de Sistemas |
		| AD    | Analista Desenvolvedor|
		
	#cenario 1	
	Cenário: Incluir colaborador
		Dado que seja informado o nome "Allan"
		E que seja informado o CPF "381.725.728-75"
		E que seja informado a data de nascimento "13/06/1989"
		E que seja informado o cargo de sigla "AS"
		E que seja informado o salário "1.000"
		E que seja informado o número de dependente "0"
		Quando cadastrar um colaborador
		Então o colaborador de nome "Allan" e CPF "381.725.728-75" deve ser cadastrado
				
	#cenario 2	
	Cenário: Validar CPF 
		Dado que seja informado o nome "Rafael"
		E que seja informado o CPF "111.111.111-75"
		E que seja informado a data de nascimento "13/06/1989"
		E que seja informado o cargo de sigla "AD"
		E que seja informado o salário "37"
		E que seja informado o número de dependente "0"
		Quando cadastrar um colaborador
		Então o sistema tem que retornar erro informando que o "O número do CPF é inválido"
		
	#cenario 3
	Cenário: Incluir colaborador com nome gigante
		Dado que seja informado o nome "João da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira"
		E que seja informado o CPF "381.725.728-75"
		E que seja informado a data de nascimento "13/06/1989"
		E que seja informado o cargo de sigla "AS"
		E que seja informado o salário "1.000"
		E que seja informado o número de dependente "0"
		Quando cadastrar um colaborador
		Então o colaborador de nome "João da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira da Silva Souza Pereira" e CPF "381.725.728-75" deve ser cadastrado
	
	#cenario 4
	Cenário: Incluir colaborador com data de nascimento incorreta
		Dado que seja informado o nome "João da Silva"
		E que seja informado o CPF "381.725.728-75"
		E que seja informado a data de nascimento "13/06/0089"
		E que seja informado o cargo de sigla "AS"
		E que seja informado o salário "1.000"
		E que seja informado o número de dependente "0"
		Quando cadastrar um colaborador
		Então deve retornar erro "O ano de nascimento esta incorreto"
	