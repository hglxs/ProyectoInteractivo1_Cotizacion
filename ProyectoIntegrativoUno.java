import java.util.Scanner;

/** 
 * ========== PROBLEMÁTICA UNO: COTIZACIÓN PRELIMINAR ==========
 * 
 * Desarrollar un sistema que permita capturar desde la consola:
 * 
 *      1. Los datos mínimos de una solicitud de cotización
 *      2. El reporte estructura con el costo estimado del lote, mostrando el desglose por fabricación de PCB y por ensamble
 * 
 *  La solución debe de operar como una herramienta interna de uso rápido, enfocada en estandarizar el cálculo preliminar y reducir
 *  los errores por captura manual.
 * 
*/

public class ProyectoIntegrativoUno{

    // Declarar como variable global Scanner "sc"
    public static Scanner sc = new Scanner(System.in);
    
    //  Método principal (MAIN)
    public static void main(String[] args){

        /**
         * Declarar las variables para capturar los datos generales del usuario:
         *      1. Nombre del cliente (string)
         *      2. ID de cotización (integer)
         *      3. Cantidad de tarjetas (integer)
         */
        String cliente;
        int idCot, cantidad;

        /**
         * Declarar los parámetros de las PCBs:
         *      1. Largo en cm (double)
         *      2. Ancho en cm (double)
         *      3. Número de capas (integer)
         *      4. Tipo de acabado - HASL / ENIG (string)
         *      5. Tipo de ensamble - SMT / THT / Mixto (string)
         *      6. Número estimado de componentes (integer)
         */
        double largo, ancho;
        int numCapas, numComponentes;
        String tipoAcabado, tipoEnsamble;
        
        /**
         * Declarar las variables de cotización:
         *      1. Costo por cm^2 (double)
         *      2. Costo por número de capas (integer)
         *      4. Costo por tipo de acabado (integer)
         *      5. Costo por ensamble (integer)
         *      6. Costo por componente habituales (integer):
         *          6.1 Resistencias
         *          6.2 Diodos
         *          6.3 Varistor
         *          6.4 Cristales
         *          6.5 Transistores
        */
       final double CM_CUADRADO=0.69;       //  1
       final int CAPA_UNO=10, CAPA_DOS=18, MULTICAPAS=86;       //  2
       int capasTotales;
       final int ACABADO_HASL=18, ACABADO_ENIG=35;      //  4 
       int acabadoTotal;
       int ensamble;        //  5
       String ens;
       final int RESISTENCIA=25, DIODOS=5, VARISTOR=10, CRISTALES=10, TRANSISTORES=10;      //  6
       int resistencia, diodos, varistor, cristales, transistores;                          //  6

        /**
         * Declarar los resultados que van a ser mostrados al ususario:
         *      1. Área de la tarjeta en cm^2 (double)
         *      2. Subtotal por fabricación de PCB (double)
         *      3. Subtotal por ensamble (double)
         *      4. Total estimado de la cotización - fabricación + ensamble (double)
         *      5. Texto para mostrar toda la información (String)
         */
        double areaTotal, subFabricacion, subEnsamble, totalCotizacion;
        String resultados;


        //  Pedir al usuario que ingrese sus datos generales
        System.out.println("\n===== REGISTRO DEL USUARIO =====");
        System.out.print("Ingrese el nombre del cliente: ");
        cliente = sc.nextLine().toUpperCase().trim();

        System.out.print("\nID de Cotización: ");
        idCot = sc.nextInt();

        System.out.print("\nCantidad de PCBs requeridas: ");
        cantidad = sc.nextInt();

        //  Pedir al usuario los datos de las PCBs
        System.out.println("\n===== DATOS DE LAS PCBs =====");
        System.out.print("Ingresa el largo de la PCB en centimetros : ");
        largo = sc.nextDouble();

        System.out.print("\nIngresa el ancho de la PCB en centimetros : ");
        ancho = sc.nextDouble();

        areaTotal = largo*ancho;            // Con los datos de "largo" y "ancho", se inicializa la variable "area"

        System.out.print("\nIngresa el numero de capas (2, 4 o 6): ");
        numCapas = sc.nextInt();
        sc.nextLine();

        //  Determinar si las capas que ingresó el usuario están dentro del rango de 2, 4 o 6
        while (!(numCapas==2) && !(numCapas==4) && !(numCapas==6)){
            System.err.println("ERROR...\nSOLO SE PUEDEN ESCOGER 2, 4 o 6 CAPAS\nPOR FAVOR INGRESA UNO DE ESOS VALORES");
            System.out.print("Numero de capas: ");
            numCapas = sc.nextInt();
            sc.nextLine();
        }

        //  Determinar el costo de las capas dependiendo de cuántas capas tienen
        if (numCapas == 2){
            capasTotales = numCapas * CAPA_UNO;
            System.out.println(capasTotales);
        } else if (numCapas == 4){
            capasTotales = numCapas * CAPA_DOS;
            System.out.println(capasTotales);
        } else {
            capasTotales = numCapas * MULTICAPAS;
            System.out.println(capasTotales);
        }

        System.out.print("\nEscribe el tipo de acabado (HASL o ENIG): ");
        tipoAcabado = sc.nextLine().trim().toUpperCase();

        //  Iniciar un ciclo "while" para que ingrese el usuario una opción correspondiente (HASL o ENIG)
        while(!tipoAcabado.equals("ENIG") && !tipoAcabado.equals("HASL") && !tipoAcabado.equals("EXIT")){
            System.err.println("\nOPCION INGRESADA INCORRECTA...\nSOLO PUEDES ESCRIBIR \"HASL\" o \"ENIG\".\nSI DESEAS SALIR DEL PROGRAMA, ESCRIBE \"EXIT\"");
            tipoAcabado = sc.nextLine().trim().toUpperCase();
        }

        //  Iniciar un "switch case" dependiendo del tipo de acabado que haya elegido el usuario
        switch(tipoAcabado){
            case "HASL":
                System.out.println("INGRESA EL TIPO DE ENSAMBLE (SMT, THT O MIXTO)");
                ens = sc.nextLine().trim().toUpperCase();
                ensamble = tipoEnsamble(tipoAcabado);               //  Mandar llamar al método "TIPO DE ENSAMBLE"

                System.out.println("\nINGRESA EL NUMERO ESTIMADO DE COMPONENTES:");
                System.out.print("RESISTENCIAS: ");
                resistencia = sc.nextInt();
                resistencia *= RESISTENCIA;

                System.out.print("DIODOS: ");
                diodos = sc.nextInt();
                diodos *= DIODOS;

                System.out.print("VARISTOR: ");
                varistor = sc.nextInt();
                varistor *= VARISTOR;

                System.out.print("CRISTALES: ");
                cristales = sc.nextInt();
                cristales *= CRISTALES;

                System.out.print("TRANSISTORES: ");
                transistores = sc.nextInt();
                transistores *= TRANSISTORES;

                //LLAMAR LOS MÉTODOS PARA LAS VARIABLES DE SUBTOTAL
                subFabricacion = SUBFABRICACION(resistencia, diodos, varistor, cristales, transistores);
                subEnsamble = capasTotales;
                totalCotizacion = subFabricacion + subEnsamble;

                //  Mostrar al usuario la información final de la cotización
                System.out.println("===== RESÚMEN DE COTIZACIÓN =====");
                resultados = resumenEjecutivo(areaTotal, subFabricacion, subEnsamble, totalCotizacion);

                break;      //  Aquí termina el programa

            case "ENIG":
                System.out.println("INGRESA EL TIPO DE ENSAMBLE (SMT, THT O MIXTO)");
                ens = sc.nextLine().trim().toUpperCase();            
                ensamble = tipoEnsamble(ens);               //  Mandar llamar al método "TIPO DE ENSAMBLE"

                System.out.println("\nINGRESA EL NUMERO ESTIMADO DE COMPONENTES:");
                System.out.print("RESISTENCIAS: ");
                resistencia = sc.nextInt();

                System.out.print("DIODOS: ");
                diodos = sc.nextInt();

                System.out.print("VARISTOR: ");
                varistor = sc.nextInt();
                
                System.out.print("CRISTALES: ");
                cristales = sc.nextInt();

                System.out.print("TRANSISTORES: ");
                transistores = sc.nextInt();

                //LLAMAR LOS MÉTODOS PARA LAS VARIABLES DE SUBTOTAL
                subFabricacion = SUBFABRICACION(resistencia, diodos, varistor, cristales, transistores);
                subEnsamble = 1;
                totalCotizacion = subFabricacion + subEnsamble;

                //  Mostrar al usuario la información finaL de la cotización
                System.out.println();
                resultados = resumenEjecutivo(areaTotal, subFabricacion, subEnsamble, totalCotizacion);

                break;      //  Aquí termina el programa

            case "EXIT":
                System.out.println("USTED HA SALIDO DEL PROGRAMA.\nGRACIAS POR SU VISITA");

                break;      //  Aquí termina el programa
        }

    }

    /**
     * ========================================================
     *              DECLARACIÓN DE LOS MÉTODOS
     * ========================================================
    */

    /**
     * INICIAR EL MÉTODO "TIPO DE ENSAMBLE"
     *  Dependiendo si elige "SMT", "THT" o "MIXTO" será la cotización diferente
     */
    public static int tipoEnsamble(String acabado){
        if (acabado.equals("SMT")){
            return 10;
        }
        if (acabado.equals("THT")){
            return 8;
        }
        if (acabado.equals("MIXTO")){
            return 28;
        } else {
            return 0;
        }
    }
    /**
     * INICIAR EL MÉTODO "SUBFABRIZACIÓN"
     *  El método sumará los precios de:
     *      1. Componentes
     *      2. Tipo de acabado
     * 
    */
    public static int SUBFABRICACION(int resistencia, int diodo, int varistor, int cristal, int transistor){
        return resistencia+diodo+varistor+cristal+transistor;
    }

    /**
     * INICIAR EL MÉTODO "DATOS DEL CLIENTE"
     *  Mostrará los datos iniciales que ingresó el usuario
    */
    public static void ejercicio1(){

    }

    /**
     * INICIAR EL MÉTODO "RESUMEN EJECUTIVO DE COTIZACIÓN"
     *  Una vez recabados los datos de las PCB's, el método arrojará al usuario un resúmen de su cotización, el cual mostrará:
     *      1. Área total de la tarjeta: 
     *      2. Subtotal por cotización
     *      3. Subtotal por ensamble
     *      4. Total estimado
     */
    public static String resumenEjecutivo(double areaTotal, double subtotalFab, double subtotalEns, double totalCot){
        totalCot = subtotalFab + subtotalEns;
        
        return "AREA DE LA TARJETA: "+areaTotal+"\nSUBTOTAL POR FABRICACION: "+subtotalFab+"\nSUBTOTAL POR ENSAMBLE: "+subtotalEns+"\n= = = = = ="+"TOTAL ESTIMADO: "+totalCot;
    }
}
