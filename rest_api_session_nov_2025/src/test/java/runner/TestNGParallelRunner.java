package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"src/test/java/features/incident.feature:9"},
		         glue = {"step.defs"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "html:cucumber-report/result.html"
		         },
		         publish = false,
		         tags = ""
		        )
public class TestNGParallelRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {		
		return super.scenarios();
	}
	
}