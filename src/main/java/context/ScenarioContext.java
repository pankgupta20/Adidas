package context;

import java.util.HashMap;
import java.util.Map;

import enums.RestEnums;

public class ScenarioContext {
	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<String, Object>();
	}

	public void setContext(RestEnums key, Object value) {
		scenarioContext.put(key.toString(), value);
	}

	public Object getContext(RestEnums key) {
		return scenarioContext.get(key.toString());
	}

	public Boolean isContains(RestEnums key) {
		return scenarioContext.containsKey(key.toString());
	}
}
