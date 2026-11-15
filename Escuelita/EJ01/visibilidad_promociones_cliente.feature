@promociones @cliente
Feature: Visibilidad de promociones en la app del cliente
  Como cliente
  Quiero ver claramente qué platos están en oferta
  Para elegir mi pedido ahorrando dinero

  Background:
    Given que el cliente abrió la app
    And que está en el catálogo del restaurante "Sabor Criollo"

  @visibilidad @regresion
  Scenario Outline: Mostrar el precio según el estado de la promoción
    Given que el plato "<plato>" tiene un precio original de "S/ <precio_original>"
    And tiene una promoción del "<descuento>" con estado "<estado>"
    When el cliente ve el catálogo
    Then ve el precio final "S/ <precio_final>" <color>
    And el precio original tachado <tachado>
    And la etiqueta "Oferta" <etiqueta>

    @activa
    Examples: Promoción activa
      | plato           | precio_original | descuento | estado | precio_final | color              | tachado    | etiqueta   |
      | Pizza Margarita | 32.00           | 20%       | Activa | 25.60        | en color llamativo | se muestra | se muestra |

    @inactiva
    Examples: Promoción programada o finalizada
      | plato               | precio_original | descuento | estado     | precio_final | color           | tachado       | etiqueta      |
      | Hamburguesa Clásica | 24.00           | 25%       | Programada | 24.00        | en color normal | no se muestra | no se muestra |
      | Ceviche Mixto       | 38.00           | 10%       | Finalizada | 38.00        | en color normal | no se muestra | no se muestra |