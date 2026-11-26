package com.solarwear.automation.utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class PreAndPostCondition {
    @Before
    public void prepareScenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @AfterStep
    public void captureStepEvidence(Scenario scenario) {
        Actor actor = OnStage.theActorInTheSpotlight();
        Screenshot.attachToReport(actor, scenario);
    }

    @After
    public void finishScenario() {
        OnStage.drawTheCurtain();
    }
}
