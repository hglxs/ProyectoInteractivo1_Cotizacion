# PROYECTO INTEGRATIVO UNO | COTIZACIÓN PRELIMINAR
## AUTORES

- Becerril Avila Hugo Alexis
- Blanco Paulo

## INSTRUCCIONES DEL CÓDIGO
1. Se debe crear un programa en JAVA que reciba desde teclado los parámetros de una RFQ *(Request for Quotation)* y que calcule el costo estimado del lote de PCB's, considerando:
    * **DATOS GENERALES:** cliente (texto), ID de cotización (texto o entero), cantidad de tarjetas.
    * **PARÁMETROS DE PCB:** largo y ancho (cm), número de capas (2, 4 o 6), tipo de acabado (HASL o ENIG).
    * **PARÁMETROS DE ENSAMLE:** tipo de ensamble (SMT, THT o MIXTO) y número estimado de componentes.

2. El sistema debe calcular como mínimo:
    * Área de la tarjeta (cm^2).
    * Subtotal de fabricación de PCB.
    * Subtotal de ensamble.
    * Total de la cotización (fabricación + ensamble).

3. El sistema deberá mostrar en pantalla un *Resumen Ejecutivo de Cotización*, con estructura clara y legible, que incluya el desglose y el total final.