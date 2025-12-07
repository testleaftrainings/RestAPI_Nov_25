package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"@failed-scenario.txt"},
		         glue = {"step.defs", "cucumber.hooks"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "html:cucumber-report/result.html",
		        		 "rerun:failed-scenario.txt"
		         },
		         publish = false,
		         tags = ""
		        )
public class TestNGRetryRunner extends AbstractTestNGCucumberTests {

}