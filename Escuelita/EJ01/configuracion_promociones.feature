@promociones @admin
Feature: Configuración de promociones por tiempo limitado
  Como administrador del restaurante
  Quiero crear y publicar promociones de descuento por tiempo limitado en platos específicos
  Para aumentar mis ventas en horas de baja demanda y atraer nuevos clientes

  Background:
    Given que el administrador inició sesión en el panel del restaurante
    And que el menú tiene los siguientes platos:
      | plato           | precio |
      | Pizza Margarita | 32.00  |
      | Ceviche Mixto   | 38.00  |
      | Lomo Saltado    | 30.00  |

  @crear @smoke
  Scenario Outline: Crear una promoción con descuento válido
    Given que el reloj del sistema marca las "<hora_actual>"
    When crea una promoción con los siguientes datos:
      | plato   | descuento   | inicio   | fin   |
      | <plato> | <descuento> | <inicio> | <fin> |
    Then la promoción queda publicada con estado "Activa"
    And el precio final del plato es "S/ <precio_final>"

    Examples:
      | plato           | descuento | inicio | fin   | hora_actual | precio_final |
      | Pizza Margarita | 20%       | 18:00  | 20:00 | 18:30       | 25.60        |
      | Ceviche Mixto   | 10%       | 15:00  | 17:00 | 15:30       | 34.20        |
      | Lomo Saltado    | 50%       | 14:00  | 16:00 | 14:30       | 15.00        |

  @desactivacion @regresion
  Scenario: Desactivación automática al cumplirse la hora de fin
    Given que existe una promoción activa del "20%" para "Pizza Margarita" que termina a las "20:00"
    When el reloj del sistema llega a las "20:00"
    Then el plato vuelve a su precio original de "S/ 32.00" en la app del cliente
    And la etiqueta "Oferta" deja de mostrarse
    And la promoción cambia automáticamente al estado "Finalizada"