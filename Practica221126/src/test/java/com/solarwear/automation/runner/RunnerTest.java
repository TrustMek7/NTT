package com.solarwear.automation.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.solarwear.automation.stepdefinitions", "com.solarwear.automation.utils"},
        plugin = {"pretty"},
        tags = "not @ignore"
)
public class RunnerTest { }
