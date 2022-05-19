package src;

public class Street {
    /**
    * Copyright (C), 2022-2023, FabianJuarez SaraEcheverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: Street
    @version:
    - Creacion: 13/05/2022
    - Ultima modificacion: 16/05/2022
    Clase que establece caracteristicas de los puntos de intercepcion.
    */

    private String origin;
    private String destination;
    private int distance;

    public Street(String origin, String destination, int distance){
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public String getOrigin(){
        return this.origin;
    }

    public String getDestination(){
        return this.destination;
    }

    public int getDistance(){
        return this.distance;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }
}
