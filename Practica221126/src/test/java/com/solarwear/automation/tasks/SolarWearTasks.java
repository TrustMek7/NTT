package com.solarwear.automation.tasks;

import com.solarwear.automation.pages.LoginPage;
import com.solarwear.automation.pages.RegistrationPage;
import com.solarwear.automation.pages.SolarWearPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;

public final class SolarWearTasks {
    private SolarWearTasks() { }

    public static Task openRegistration() {
        return Task.where("open the SolarWear registration page", Open.browserOn().the(RegistrationPage.class));
    }

    public static Task openLogin() {
        return Task.where("open the SolarWear login page", Open.browserOn().the(LoginPage.class));
    }

    public static Task register(String name, String email, String password, String confirmation) {
        return Task.where("register a SolarWear customer",
                Enter.theValue(name).into(SolarWearPage.REGISTRATION_NAME),
                Enter.theValue(email).into(SolarWearPage.REGISTRATION_EMAIL),
                Enter.theValue(password).into(SolarWearPage.REGISTRATION_PASSWORD),
                Enter.theValue(confirmation).into(SolarWearPage.REGISTRATION_CONFIRMATION),
                Click.on(SolarWearPage.REGISTRATION_SUBMIT));
    }

    public static Task logout() {
        return Task.where("close the SolarWear customer session",
                Click.on(SolarWearPage.ACCOUNT_MENU_BUTTON),
                Click.on(SolarWearPage.LOGOUT_BUTTON));
    }

    public static Task login(String email, String password) {
        return Task.where("attempt to sign in to SolarWear",
                Enter.theValue(email).into(SolarWearPage.LOGIN_EMAIL),
                Enter.theValue(password).into(SolarWearPage.LOGIN_PASSWORD),
                Click.on(SolarWearPage.LOGIN_SUBMIT));
    }
}
