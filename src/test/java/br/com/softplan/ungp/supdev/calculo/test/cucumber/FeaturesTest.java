package br.com.softplan.ungp.supdev.calculo.test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber",
                "json:target/cucumber/cucumber.json"
        },
        glue = "br.com.softplan.ungp.supdev.calculo.test",
        snippets = SnippetType.CAMELCASE
)
public class FeaturesTest {

}
