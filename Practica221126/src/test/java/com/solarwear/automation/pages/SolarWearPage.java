package com.solarwear.automation.pages;

import com.solarwear.automation.utils.Parameters;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

@DefaultUrl(Parameters.BASE_URL)
public class SolarWearPage extends PageObject {
    public static final String BASE_URL = Parameters.BASE_URL;
    public static final Target REGISTRATION_NAME = Target.the("nombre completo").locatedBy("//form//input[@type='text'][1]");
    public static final Target REGISTRATION_EMAIL = Target.the("correo de registro").locatedBy("//form//input[@type='email'][1]");
    public static final Target REGISTRATION_PASSWORD = Target.the("contraseña de registro").locatedBy("//form//input[@type='password'][1]");
    public static final Target REGISTRATION_CONFIRMATION = Target.the("confirmación de contraseña").locatedBy("(//form//input[@type='password'])[2]");
    public static final Target REGISTRATION_SUBMIT = Target.the("botón crear cuenta").locatedBy("//form//button[@type='submit']");
    public static final Target LOGIN_EMAIL = Target.the("correo de inicio de sesión").locatedBy("//form//input[@type='email'][1]");
    public static final Target LOGIN_PASSWORD = Target.the("contraseña de inicio de sesión").locatedBy("//form//input[@type='password'][1]");
    public static final Target LOGIN_SUBMIT = Target.the("botón iniciar sesión").locatedBy("//form//button[@type='submit']");
    public static final Target ACCOUNT_MENU_BUTTON = Target.the("botón de cuenta del cliente").locatedBy("//header//button[contains(@class,'rounded-full')]");
    public static final Target LOGOUT_BUTTON = Target.the("botón cerrar sesión").locatedBy("//button[normalize-space()='Cerrar Sesión']");
    public static final Target REGISTRATION_MISMATCH_ERROR = Target.the("error de contraseñas distintas").locatedBy("//*[normalize-space(text())='Las contraseñas no coinciden.']");
    public static final Target REGISTRATION_EMAIL_ERROR = Target.the("error de correo inválido").locatedBy("//*[normalize-space(text())='Ingresa un correo válido.']");
    public static final Target LOGIN_UNKNOWN_ACCOUNT_ERROR = Target.the("error de cuenta inexistente").locatedBy("//*[contains(normalize-space(text()),'No existe una cuenta con este correo.')]");
}
