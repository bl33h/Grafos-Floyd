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
        miVista.menu();
    
        miVista.Welcome(); // Bienvenida al programa
        try{
            boolean flag = true;
            while(numMenu != 5 && flag){ // Imprime el menu principal
                numMenu = miVista.menu();
                switch(numMenu){
                    case 1: // Ingreso de Ciudad
                        waze = new Waze(numMenu);
                        flag = false;
                        break;
                    case 2:// Ubicacion centro del grafo
                        waze = new Waze(numMenu, 0);
                        flag = false;
                        break;
                    case 3: // Interrupcion entre ciudades
                        waze = new Waze(numMenu, 0);
                        flag = false;
                        break;
                    case 4: // Nueva conexion entre ciudades
                        waze = new Waze(numMenu, 0);
                        flag = false;
                        break;
                    case 5: // Salir
                        miVista.end();
                        waze.finalize();
                        break;
                }
            }
        } catch (Exception e) {
            String s = "ERROR: " + e.getMessage();
            miVista.error(s);
        }
    }
}