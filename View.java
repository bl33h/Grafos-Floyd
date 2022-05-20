import java.util.Scanner;

public class View{
    /**
    * Copyright (C), 2022-2023, Fabian Juarez Sara Echeverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: View
    @version:
    - Creation: 13/05/2022
    - Last modification: 20/05/2022
    Class that works as a view to the user.
    */

    //---------------------------PROPIEDADES--------------------------
    private Scanner scan;

    //---------------------------METODOS------------------------------
    /*****************************************************************
     * Print the introduction of the program
     */
    public void welcome() {
        System.out.println("------------- Bienvenido/bienvenida a el nuevo Waze el cual calculara la ruta mas corta ---------------- "); // Titulo
        System.out.println("------------- Estamos abriendo su archivo guategrafo.txt ---------------- "); // mensaje
    }
    //****************************************************************

    /*****************************************************************
     * Constructor for the view
     */
    public View(){
        scan = new Scanner(System.in); // Scanner
    }
    //****************************************************************

    /*****************************************************************
     * Return the value for the element selected for the user
     * @return
     */
    public int menu(){ //Principal Menu for the users that choose an option
        String salir = "";
        int numMenu = 0;
        String tmpTexto = "";
        boolean isNumeric = false;
        while(!salir.equalsIgnoreCase("si")){
            //Print the menu on screen and  the user is prompted for a number from the menu
            System.out.println("\n************************* Escojala opcion que desea utilizar ************************************\n");
            System.out.println("1. Ingresar la ciudad de origen y ciudad de destino");
            System.out.println("2. Ver la ciudad que se encuentra en el centro del grafo");
            System.out.println("3. Interrupcion entre un par de ciudades");
            System.out.println("4. Nueva conexion entre cuidades");
            System.out.println("5. Mostrar matrices");
            System.out.println("6. Salir");
            System.out.println("\n************ Por, favor elija la opcion que desea ejecutar **********\n");
            tmpTexto = scan.nextLine();

            // Verify that the number given by the user was valid.
            isNumeric = tmpTexto.chars().allMatch( Character::isDigit );
            if (isNumeric && !tmpTexto.isEmpty()){
                numMenu = Integer.parseInt(tmpTexto);
                salir = "si";
            }
            while (tmpTexto.isEmpty()||!isNumeric || numMenu < 1 || numMenu > 6) {
                System.out.println("ERROR, ingresar una opcion de menu mayor que 0" + " o menor que 6");
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
     * Message to be given at the end of the program
     */
    public void end(){
        System.out.println(" ------------ Gracias por utilizar nuestro programa ------------ \n"); // Mensaje que se mostrara al cerrar el programa
        System.exit(0);
        scan.close();
    }
    //****************************************************************

    /*****************************************************************
     * Assigns a passed value as a parameter
     * @param e
     */
    public void error(String e){
        System.out.println("Ha ocurrido un error : -- " + e);
    }
    //****************************************************************

    /*****************************************************************
     * Assigns a message to be displayed on the screen
     * @param s
     */
    public void output(String s){
        System.out.println(s); //Message to be printed to the user
    }
    //****************************************************************
    
    /*****************************************************************
    *  asks the user for the origin printing a message on the screen
    * @return the origin 
    */
    public String newOrigin(){
        String origin = "";
        output("Ingresar la ciudad de origen");
        origin = scan.nextLine();
        return origin;
    }
    //****************************************************************

    /*****************************************************************
    *  asks the user for the destination printing a message on the screen
    * @return the destination 
    */
    public String newDestination(){
        String destination ="";
        output("Ingresar la ciudad destino");
        destination = scan.nextLine();
        return destination;
    }
    //****************************************************************

    /*****************************************************************
    *  asks the user for the distance printing a message on the screen
    * @return the distance
    */
    public Integer newDistance(){
        Integer distance = 0;
        output("Ingresar la distancia entre las ciudades");
        distance = Integer.parseInt(scan.nextLine());
        return distance;
    }
    //****************************************************************
}
