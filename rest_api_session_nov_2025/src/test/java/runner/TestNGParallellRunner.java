package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		         features = {"src/test/java/features/incident.feature"},
		         glue = {"step.defs"},
		         dryRun = false,
		         plugin = {
		        		 "pretty",
		        		 "html:cucumber-report/result.html"
		         },
		         publish = false,
		         tags = ""
		        )
public class TestNGParallellRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {		
		return super.scenarios();
	}
	
}