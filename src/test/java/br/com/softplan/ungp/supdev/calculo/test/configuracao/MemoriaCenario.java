package br.com.softplan.ungp.supdev.calculo.test.configuracao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoriaCenario {

	private Map<String, Object> mapObjetosCompartilhados = new HashMap<String, Object>();
	
	public void clean() {
		mapObjetosCompartilhados.clear();
	}

    public <T> void guardar(String key, T obj) {
        mapObjetosCompartilhados.put(key, obj);
    }

    @SuppressWarnings("unchecked")
	public <T> T recuperar(String key, Class<T> obj) {
       return (T) mapObjetosCompartilhados.get(key);
    }
}
