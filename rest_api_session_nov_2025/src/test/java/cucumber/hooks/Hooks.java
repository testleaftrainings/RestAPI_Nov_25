package cucumber.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

public class Hooks {
	
	@BeforeAll // Before All the scenarios it'll run one time
	public static void runBeforeAllScenarios() {
		
	}
	
	@AfterAll // After All the scenarios it'll run one time
	public static void runAfterAllScenarios() {
		
	}
	
	@Before // This annotation will run depends on how many scenarios you have in the feature file
	public void runBeforeEachScenario() {
		
	}
	
	@After
	public void runAfterEachScenario() {
		
	}
	
	@BeforeStep // This annotation will run depends on how many scenario steps you have in the feature file
	public void runBeforeEachStepInAScenario() {
		
	}
	
	@AfterStep
	public void runAfterEachStepInAScenario() {
		
	}
 
}