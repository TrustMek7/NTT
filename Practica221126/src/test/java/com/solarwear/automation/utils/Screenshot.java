package com.solarwear.automation.utils;

import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class Screenshot {
    private Screenshot() { }

    public static void attachToReport(Actor actor, Scenario scenario) {
        byte[] image = ((TakesScreenshot) BrowseTheWeb.as(actor).getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(image, Parameters.SCREENSHOT_MIME_TYPE, scenario.getName());
    }
}
