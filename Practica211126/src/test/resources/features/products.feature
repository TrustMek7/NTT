@productos
Feature: Consulta de productos en la API
  Como consumidor del catálogo
  Quiero consultar productos y sus detalles
  Para validar la información que entrega el servicio

  @positivo @catalogo
  Scenario: Consultar el catálogo completo
    Given el actor se conecta con la API de productos
    When el actor consulta todos los productos
    Then el código de respuesta debe ser 200
    And la respuesta incluye una lista de productos

  @positivo @catalogo
  Scenario: Consultar productos con límite de resultados
    Given el actor se conecta con la API de productos
    When el actor consulta los primeros cinco productos
    Then el código de respuesta debe ser 200
    And la respuesta respeta el límite de cinco resultados

  @positivo @detalle
  Scenario: Consultar un producto existente
    Given el actor se conecta con la API de productos
    When el actor consulta el producto con ID "1"
    Then el código de respuesta debe ser 200
    And la respuesta contiene los datos básicos del producto

  @negativo @detalle
  Scenario: Consultar un producto inexistente
    Given el actor se conecta con la API de productos
    When el actor consulta el producto con ID "999999"
    Then el código de respuesta debe ser 404
    And la API informa que el producto no existe
