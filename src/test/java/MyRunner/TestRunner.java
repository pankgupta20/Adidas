package MyRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features", glue = { "stepDefinitions" }, plugin = { "pretty",
		"html:test-outout", "json:json_output/cucumber.json",
		"junit:junit_xml/cucumber.xml" }, monochrome = true, dryRun = false, publish = true,
		// tags = "@UI",
		stepNotifications = true)

public class TestRunner {

}