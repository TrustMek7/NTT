package com.solarwear.automation.questions;

import com.solarwear.automation.pages.SolarWearPage;
import com.solarwear.automation.utils.Parameters;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class SolarWearQuestions {
    private SolarWearQuestions() { }

    public static Question<Boolean> visible(Target target) {
        return actor -> {
            actor.attemptsTo(WaitUntil.the(target, isVisible()).forNoMoreThan(Parameters.WAIT_SECONDS).seconds());
            return target.resolveFor(actor).isVisible();
        };
    }

    public static Question<Boolean> accountCreated() {
        return actor -> BrowseTheWeb.as(actor).getDriver().getCurrentUrl().equals(SolarWearPage.BASE_URL + "/");
    }

    public static Question<Boolean> authenticatedMenu() { return visible(SolarWearPage.LOGOUT_BUTTON); }
    public static Question<Boolean> passwordMismatch() { return visible(SolarWearPage.REGISTRATION_MISMATCH_ERROR); }
    public static Question<Boolean> invalidEmail() { return visible(SolarWearPage.REGISTRATION_EMAIL_ERROR); }
    public static Question<Boolean> unknownAccount() { return visible(SolarWearPage.LOGIN_UNKNOWN_ACCOUNT_ERROR); }
}
