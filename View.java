import java.util.Scanner;

public class View{
    /**
    * Copyright (C), 2022-2023, FabianJuarez SaraEcheverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: View
    @version:
    - Creacion: 13/05/2022
    - Ultima modificacion:13/05/2022
    Clase que sirve como vista hacia el usuario la cual imprimira a pantalla
    
    */

    //---------------------------PROPIEDADES--------------------------
    private Scanner scan;

    //---------------------------METODOS------------------------------
    /*****************************************************************
     *
     */
    public void welcome() {
        System.out.println("------------- Bienvenido/bienvenida a el nuevo Waze el cual calculara la ruta mas corta ---------------- "); // Titulo
        System.out.println("------------- Estamos abriendo su archivo guategrafo.txt ---------------- "); // mensaje
    }
    //****************************************************************

    /*****************************************************************
     *
     */
    public View(){
        scan = new Scanner(System.in); // Scanner
    }
    //****************************************************************

    /*****************************************************************
     * retorna el valor del elemento seleccionado por el usuario
     * @return
     */
    public int menu(){ //Menu principal para que el usuario eliga la opcion a realizar
        String salir = "";
        int numMenu = 0;
        String tmpTexto = "";
        boolean isNumeric = false;
        while(!salir.equalsIgnoreCase("si")){
            //Se imprime el menu en pantalla y se le pide al usuario un numero del menu
            System.out.println("\n************************* Escojala opcion que desea utilizar ************************************\n");
            System.out.println("1. Ingresar la ciudad de origen y ciudad de destino");
            System.out.println("2. Ver la ciudad que se encuentra en el centro del grafo");
            System.out.println("3. Interrupcion entre un par de ciudades");
            System.out.println("4. Nueva conexion entre cuidades");
            System.out.println("5. Salir");
            System.out.println("\n************ Por, favor elija la opcion que desea ejecutar **********\n");
            tmpTexto = scan.nextLine();

            // Se verifica que el numero que dio el usuario fue valido
            isNumeric = tmpTexto.chars().allMatch( Character::isDigit );
            if (isNumeric && !tmpTexto.isEmpty()){
                numMenu = Integer.parseInt(tmpTexto);
                salir = "si";
            }
            while (tmpTexto.isEmpty()||!isNumeric || numMenu < 1 || numMenu > 5) {
                System.out.println("ERROR, ingresar una opcion de menu mayor que 0" + " o menor que 5");
                tmpTexto = scan.nextLine();
                isNumeric = tmpTexto.chars().allMatch( Character::isDigit );
                numMenu = 0;
                if (isNumeric&&!tmpTexto.isEmpty()){
                    numMenu = Integer.parseInt(tmpTexto);
                    salir = "si";
                    }
                }
            }
        return numMenu;
    }
    //****************************************************************

    /*****************************************************************
     * Mensaje que dara al finalizar el programa
     */
    public void end(){
        System.out.println(" ------------ Gracias por utilizar nuestro programa ------------ \n"); // Mensaje que se mostrara al cerrar el programa
        System.exit(0);
        scan.close();
    }
    //****************************************************************

    /*****************************************************************
     * Asigna un valor pasado como parametro
     * @param e
     */
    public void error(String e){
        System.out.println("Ha ocurrido un error : -- " + e);
    }
    //****************************************************************

    /*****************************************************************
     * Asigna un mensaje a mostrar en pantalla
     *@param s
     */
    public void output(String s){
        System.out.println(s); //mensaje que se le imprimira al usuario
    }
    //****************************************************************
}
