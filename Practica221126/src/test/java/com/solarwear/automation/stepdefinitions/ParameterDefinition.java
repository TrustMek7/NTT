package com.solarwear.automation.stepdefinitions;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

public class ParameterDefinition {
    @ParameterType("Visitante")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }
}
