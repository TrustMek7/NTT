package com.solarwear.automation.stepdefinitions;

import com.solarwear.automation.pages.SolarWearPage;
import com.solarwear.automation.questions.SolarWearQuestions;
import com.solarwear.automation.tasks.SolarWearTasks;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.is;

public class AccountStepDefinitions {
    private String accountEmail;
    private final String accountPassword = "SolarWear2026";
    private Actor actor() { return OnStage.theActorInTheSpotlight(); }

    @Dado("que {actor} está en la página de registro de SolarWear")
    public void openRegistration(Actor actor) { actor.attemptsTo(SolarWearTasks.openRegistration()); }

    @Cuando("completo el registro con datos válidos")
    public void registerWithValidData() {
        accountEmail = "prueba@test.com";
        registerAccount(accountEmail);
    }

    private void registerAccount(String email) {
        actor().attemptsTo(SolarWearTasks.register("Usuario de Prueba", email, accountPassword, accountPassword));
    }

    @Entonces("veo el mensaje de bienvenida de cuenta creada")
    public void seeRegistrationSuccess() {
        actor().should(seeThat(SolarWearQuestions.accountCreated(), is(true)));
    }

    @Cuando("me registro, cierro sesión y vuelvo a iniciar con la misma cuenta")
    public void registerLogoutAndLogin() {
        accountEmail = "prueba2@test.com";
        registerAccount(accountEmail);
        actor().attemptsTo(SolarWearTasks.logout(), SolarWearTasks.openLogin(),
                SolarWearTasks.login(accountEmail, accountPassword), Click.on(SolarWearPage.ACCOUNT_MENU_BUTTON));
    }

    @Entonces("veo el menú de cuenta autenticada")
    public void seeAuthenticatedMenu() {
        actor().should(seeThat(SolarWearQuestions.authenticatedMenu(), is(true)));
    }

    @Cuando("envío el formulario con contraseñas distintas")
    public void submitMismatchedPasswords() {
        actor().attemptsTo(SolarWearTasks.register("Usuario de Prueba", "prueba@test.com", "SolarWear2026", "OtraClave2026"));
    }

    @Entonces("veo el mensaje de contraseñas distintas")
    public void seePasswordMismatch() {
        actor().should(seeThat(SolarWearQuestions.passwordMismatch(), is(true)));
    }

    @Dado("que {actor} está en el inicio de sesión de SolarWear")
    public void openLogin(Actor actor) { actor.attemptsTo(SolarWearTasks.openLogin()); }

    @Cuando("intento iniciar sesión con un correo sin cuenta")
    public void loginWithUnknownEmail() {
        actor().attemptsTo(SolarWearTasks.login("noexiste@test.com", "SolarWear2026"));
    }

    @Entonces("veo el mensaje de cuenta inexistente")
    public void seeUnknownAccount() {
        actor().should(seeThat(SolarWearQuestions.unknownAccount(), is(true)));
    }

    @Cuando("envío un correo con formato inválido")
    public void submitInvalidEmail() {
        actor().attemptsTo(SolarWearTasks.register("Usuario de Prueba", "correo-invalido", "SolarWear2026", "SolarWear2026"));
    }

    @Entonces("veo el mensaje de correo inválido")
    public void seeInvalidEmail() {
        actor().should(seeThat(SolarWearQuestions.invalidEmail(), is(true)));
    }
}
