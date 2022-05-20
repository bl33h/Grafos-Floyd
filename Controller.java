import java.io.FileNotFoundException;

public class Controller {
    /**
    * Copyright (C), 2022-2023, Fabian Juarez Sara Echeverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: Controller
    @version:
    - Creacion: 13/05/2022
    - Ultima modificacion: 20/05/2022
    Class that controls the functions of the program.
    */

    public static void main(String[] args) throws FileNotFoundException {
        Waze waze = new Waze();
        int numMenu = 0;
        View miVista = new View();
            miVista.welcome(); // Welcome message with instructions
            try{
                while(numMenu != 6){ // It shows the menu
                    numMenu = miVista.menu();
                    switch(numMenu){
                        case 1: // It allows the input of a new city
                            String origin = miVista.newOrigin();
                            String destination = miVista.newDestination();
                            String route ="";
                            if(waze.verifyGraph())
                                route += waze.getPath(origin, destination);
                                route += origin + ", " +waze.getRoute(origin, destination);
                            miVista.output(route);
                            break;
                        case 2:// It shows the location of the center of the graph
                            String center = "";
                            if (waze.verifyGraph())
                                center = waze.getCenter();
                            miVista.output(center);
                            break;
                        case 3: // Interruption between cities
                            String origin2 = miVista.newOrigin();
                            String destination2 = miVista.newDestination();
                            waze.pauseStreet(origin2,destination2);
                            break;
                        case 4: // New connection between cities
                            String origin3 = miVista.newOrigin();
                            String destination3 = miVista.newDestination();
                            Integer distance3 = miVista.newDistance();
                            waze.newStreet(origin3, destination3, distance3);
                            break;
                        case 5: // It shows the matrix
                                waze.verifyGraph();
                                String matrix = waze.showMatrix();
                                miVista.output(matrix);
                            break;
                        case 6: // Exit the menu
                            miVista.end();
                            break;
                    }
                }
            } catch (Exception e) {
                String s = "ERROR: " + e.getMessage(); // Error handling message
                miVista.error(s);
            }
    }
}