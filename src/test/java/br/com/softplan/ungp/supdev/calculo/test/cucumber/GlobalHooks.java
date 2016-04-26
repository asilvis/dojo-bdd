package br.com.softplan.ungp.supdev.calculo.test.cucumber;

import br.com.softplan.ungp.supdev.calculo.test.configuracao.Script;
import br.com.softplan.ungp.supdev.calculo.test.configuracao.SpringTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

public class GlobalHooks extends SpringTest {

	 private static boolean dunit = false;
	
	@Autowired
	private DataSource dataSource;
	
	public void runScript(Script script) throws FileNotFoundException, SQLException {
		RunScript.execute(dataSource.getConnection(), new FileReader(Thread.currentThread().getContextClassLoader().getResource(script.getFile()).getFile()));
	}
	
	@Before
    public void globalBefore() throws FileNotFoundException, SQLException {
		if(!dunit) {
			runScript(Script.CREATE_FUNCTIONS);
			dunit = true;
		}
	}
	
	@After
    public void globalAfter() throws FileNotFoundException, SQLException {
        runScript(Script.SHUTDOWN_CUCUMBER);
    }
}
