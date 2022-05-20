import java.io.FileNotFoundException;

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
        Waze waze = new Waze();
        int numMenu = 0;
        View miVista = new View();
            miVista.welcome(); // Bienvenida al programa
            try{
                while(numMenu != 6){ // Imprime el menu principal
                    numMenu = miVista.menu();
                    switch(numMenu){
                        case 1: // Ingreso de Ciudad
                            String origin = miVista.newOrigin();
                            String destination = miVista.newDestination();
                            String route ="";
                            if(waze.verifyGraph())
                                route = waze.getRoute(origin, destination);
                            miVista.output(route);
                            break;
                        case 2:// Ubicacion centro del grafo
                            String center = "";
                            if (waze.verifyGraph())
                                center = waze.getCenter();
                            miVista.output(center);
                            break;
                        case 3: // Interrupcion entre ciudades
                            String origin2 = miVista.newOrigin();
                            String destination2 = miVista.newDestination();
                            waze.pauseStreet(origin2,destination2);
                            break;
                        case 4: // Nueva conexion entre ciudades
                            String origin3 = miVista.newOrigin();
                            String destination3 = miVista.newDestination();
                            Integer distance3 = miVista.newDistance();
                            waze.newStreet(origin3, destination3, distance3);
                            break;
                        case 5: //Mostrar matrices
                                waze.verifyGraph();
                                String matrix = waze.showMatrix();
                                miVista.output(matrix);
                            break;
                        case 6: // Salir
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