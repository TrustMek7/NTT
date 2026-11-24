package org.example.StepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import org.example.Tasks.GetAllProducts;
import org.example.Tasks.GetProductById;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductStepDefinition {
    private static final String BASE_URL = "https://dummyjson.com";

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} se conecta con la API de productos")
    public void elActorSeConectaConLaApiDeProductos(Actor actor) {
        actor.whoCan(CallAnApi.at(BASE_URL));
    }

    @When("el {actor} consulta todos los productos")
    public void elActorConsultaTodosLosProductos(Actor actor) {
        actor.attemptsTo(GetAllProducts.fromEndpoint("/products"));
    }

    @When("el {actor} consulta los primeros cinco productos")
    public void elActorConsultaLosPrimerosCincoProductos(Actor actor) {
        actor.attemptsTo(GetAllProducts.fromEndpoint("/products?limit=5"));
    }

    @When("el {actor} consulta el producto con ID {string}")
    public void elActorConsultaElProductoConId(Actor actor, String productId) {
        actor.attemptsTo(GetProductById.withId(productId));
    }

    @Then("el código de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("el código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @Then("la respuesta incluye una lista de productos")
    public void laRespuestaIncluyeUnaListaDeProductos() {
        List<?> products = SerenityRest.lastResponse().jsonPath().getList("products");
        assertNotNull("La respuesta debe incluir el arreglo products", products);
        assertFalse("El catálogo debe devolver productos", products.isEmpty());
        assertTrue("La respuesta debe informar el total de productos",
                SerenityRest.lastResponse().jsonPath().getInt("total") > 0);
    }

    @Then("la respuesta respeta el límite de cinco resultados")
    public void laRespuestaRespetaElLimiteDeCincoResultados() {
        List<?> products = SerenityRest.lastResponse().jsonPath().getList("products");
        assertEquals("Se solicitaron cinco productos", 5, products.size());
        assertEquals("El servidor debe reflejar el límite solicitado", 5,
                SerenityRest.lastResponse().jsonPath().getInt("limit"));
    }

    @Then("la respuesta contiene los datos básicos del producto")
    public void laRespuestaContieneLosDatosBasicosDelProducto() {
        Map<String, Object> product = SerenityRest.lastResponse().jsonPath().getMap("$");
        assertNotNull("La respuesta debe ser un objeto JSON", product);
        assertEquals("El producto consultado debe conservar el ID", 1,
                SerenityRest.lastResponse().jsonPath().getInt("id"));
        assertFalse("El título no debe estar vacío",
                SerenityRest.lastResponse().jsonPath().getString("title").isBlank());
        assertTrue("El precio debe ser positivo", SerenityRest.lastResponse().jsonPath().getDouble("price") > 0);
    }

    @Then("la API informa que el producto no existe")
    public void laApiInformaQueElProductoNoExiste() {
        assertTrue("El caso negativo debe devolver 404",
                SerenityRest.lastResponse().statusLine().contains("404"));
    }
}
