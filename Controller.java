import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
    /**
    * Copyright (C), 2022-2023, FabianJuarez SaraEcheverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: Controller
    @version:
    - Creacion: 13/05/2022
    - Ultima modificacion:13/05/2022
    Clase que controla las funciones del programa.
    
    */

    public static void main(String[] args) throws FileNotFoundException {
        Waze waze;
        int numMenu;
        View miVista = new View();
        String origin;
        String destination;
        int distance;
        Scanner scan = new Scanner(System.in);
    
        miVista.menu();
        miVista.welcome(); // Bienvenida al programa
        try{
            boolean flag = true;
            while(numMenu != 5 && flag){ // Imprime el menu principal
                numMenu = miVista.menu();
                switch(numMenu){
                    case 1: // Ingreso de Ciudad
                        miVista.output("Ingresar el origen del que comenzara la nueva ruta");
                        origin = scan.nextLine();
                        miVista.output("Ingresar el destino del que finalizara la nueva ruta");
                        destination = scan.nextLine();
                        miVista.output("Ingresar la distancia que tendra la ruta del destino");
                        distance = scan.nextInt();
                        waze.newStreet(origin, destination, distance);
                        flag = false;
                        break;
                    case 2:// Ubicacion centro del grafo
                        waze = new Waze(numMenu, 0);
                        flag = false;
                        break;
                    case 3: // Interrupcion entre ciudades
                        miVista.output("Ingresar el origen del que comenzara la interrupcion");
                        origin = scan.nextLine();
                        miVista.output("Ingresar el destino del que finalizara la interrupcion");
                        destination = scan.nextLine();
                        miVista.output(" ");
                        waze.pauseStreet(origin,destination);
                        flag = false;
                        break;
                    case 4: // Nueva conexion entre ciudades
                        waze = new Waze(numMenu, 0);
                        flag = false;
                        break;
                    case 5: // Salir

                        miVista.end();
                        break;
                }
            }
        } catch (Exception e) {
            String s = "ERROR: " + e.getMessage();
            miVista.error(s);
        }
    }
}