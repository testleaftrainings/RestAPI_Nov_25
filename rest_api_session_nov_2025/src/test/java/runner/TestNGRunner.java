package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"src/test/java/features/incident.feature"},
		         glue = {"step.defs", "cucumber.hooks"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "html:cucumber-report/result.html"
		         },
		         publish = false,
		         tags = ""
		        )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}