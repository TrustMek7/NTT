package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class ResponseCode {
    private ResponseCode() {
    }

    public static Question<Integer> getStatus() {
        return actor -> SerenityRest.lastResponse().statusCode();
    }
}
