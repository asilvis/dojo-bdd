package br.com.softplan.ungp.supdev.calculo.test.configuracao;

import br.com.softplan.ungp.supdev.calculo.TestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.TestRestTemplate.HttpClientOption;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SuppressWarnings({"unchecked", "rawtypes"})
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port=0")
public abstract class SpringTest {
    
	private static final String SERVER_ADDRESS = "http://localhost:";
	private static final String SERVER_CONTEXT = "/calculo-folha";
	
	@Value("${local.server.port}")
	private String porta;
	protected String url;

	protected RestTemplate restTemplate = new TestRestTemplate("user", "password", HttpClientOption.ENABLE_COOKIES);
	protected ObjectMapper jsonMapper = new ObjectMapper();
	protected HttpMessageConverter mappingJackson2HttpMessageConverter;


	@Autowired
	protected MemoriaCenario memoriaCenario;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	
	public void configureUrl(String urlParam) {
    	this.url = getServerPath().concat(urlParam);
    }
    
    private String getServerPath() {
    	return SERVER_ADDRESS + porta + SERVER_CONTEXT;
    }
    
	public HttpEntity<String> configureHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
    
	protected String toJson(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public String getUrl() {
		return url;
	}

	public ObjectMapper getJsonMapper() {
		return jsonMapper;
	}

	public MemoriaCenario getMemoriaCenario() {
		return memoriaCenario;
	}
}
