package apiBase;

import context.ScenarioContext;
import context.TestContext;

public class BaseTest {
	private ScenarioContext scenarioContext;

	public BaseTest(TestContext testContext) {
		scenarioContext = testContext.getScenarioContext();
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
