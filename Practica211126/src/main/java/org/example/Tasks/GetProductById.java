package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetProductById implements Task {
    private final String productId;

    public GetProductById(String productId) {
        this.productId = productId;
    }

    public static Performable withId(String productId) {
        return instrumented(GetProductById.class, productId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/products/" + productId)
                .with(request -> request.contentType(ContentType.JSON).accept(ContentType.JSON).log().all()));
    }
}
