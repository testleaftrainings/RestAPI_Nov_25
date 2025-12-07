package com.testleaf.matschie.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/com/testleaf/matschie/features/incident.feature"},
        glue = {"com.testleaf.matschie.step.definitions"},
        dryRun = false,
        plugin = {
       		 "pretty",
       		 "html:cucumber-report/result.html",
       		 "rerun:failed-scenario.txt",
       		 "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        publish = false,
        tags = ""
       )
public class TestNGRunner extends AbstractTestNGCucumberTests {

}