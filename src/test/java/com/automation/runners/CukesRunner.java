package com.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
       glue = "com/automation/step_definitions" ,
        features = "src/test/resources/features",
        plugin = "json:target/cucumber.json",
        dryRun = false,
        strict = false,
        tags = "@ui"

)
public class CukesRunner {


}
