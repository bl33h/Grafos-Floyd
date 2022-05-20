
public class Street {
    /**
    * Copyright (C), 2022-2023, FabianJuarez SaraEcheverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: Street
    @version:
    - Creation: 13/05/2022
    - Last modification: 20/05/2022
    Class that establishes the characteristics of the incerpection points.
    */

    //---------------------------PROPERTIES---------------------------
    private String origin;
    private String destination;
    private int distance;

    //---------------------------METHODS------------------------------
    /****************************************************************
     * constructor for the class street
     * @param origin
     * @param destination
     * @param distance
     */
    public Street(String origin, String destination, int distance){
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }
    //***************************************************************

    /****************************************************************
     * get origin
     * @return origin of the street
     */
    public String getOrigin(){
        return this.origin;
    }
    //***************************************************************

    /****************************************************************
     * get destination
     * @return destination of the street
     */
    public String getDestination(){
        return this.destination;
    }
    //***************************************************************

    /****************************************************************
     * get distance
     * @return distance of the street
     */
    public int getDistance(){
        return this.distance;
    }
    //***************************************************************

    /****************************************************************
     * set distance
     * @param distance
     */
    public void setDistance(int distance){
        this.distance = distance;
    }
    //***************************************************************
}
