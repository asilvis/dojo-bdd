package br.com.softplan.ungp.supdev.calculo.test.configuracao;

public enum Script {

	CREATE_FUNCTIONS("script/create-function.sql"), 
	SHUTDOWN_CUCUMBER("script/shutdown-cucumber.sql");
	
	private String file;

	Script(String file) {
		this.file = file;
	}

	public String getFile() {
		return file;
	}
}
