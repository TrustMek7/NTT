# language: es
Característica: Registro e inicio de sesión en SolarWear
  Como visitante de SolarWear
  Quiero crear una cuenta e iniciar sesión
  Para acceder a las funciones de cliente

  @registro @positivo
  Escenario: Crear una cuenta con datos válidos
    Dado que Visitante está en la página de registro de SolarWear
    Cuando completo el registro con datos válidos
    Entonces veo el mensaje de bienvenida de cuenta creada

  @login @positivo
  Escenario: Iniciar sesión después de registrar una cuenta
    Dado que Visitante está en la página de registro de SolarWear
    Cuando me registro, cierro sesión y vuelvo a iniciar con la misma cuenta
    Entonces veo el menú de cuenta autenticada

  @registro @negativo
  Escenario: Rechazar contraseñas que no coinciden
    Dado que Visitante está en la página de registro de SolarWear
    Cuando envío el formulario con contraseñas distintas
    Entonces veo el mensaje de contraseñas distintas

  @login @negativo
  Escenario: Rechazar el inicio de sesión de una cuenta inexistente
    Dado que Visitante está en el inicio de sesión de SolarWear
    Cuando intento iniciar sesión con un correo sin cuenta
    Entonces veo el mensaje de cuenta inexistente

  @registro @negativo
  Escenario: Validar el formato de correo electrónico
    Dado que Visitante está en la página de registro de SolarWear
    Cuando envío un correo con formato inválido
    Entonces veo el mensaje de correo inválido
