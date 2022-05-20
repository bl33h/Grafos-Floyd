import java.io.File;  
import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Collections;

public class Waze{
    /**
    * Copyright (C), 2022-2023, FabianJuarez SaraEcheverria Jose Pablo Kiesling Melissa Perez
    * @author Fabian Juarez, Sara Echeverria, Jose Pablo Kiesling y Melissa Perez
    * FileName: Waze
    @version:
    - Creacion: 13/05/2022
    - Ultima modificacion: 16/05/2022
    Clase que posee los metodos para el correcto funcionamiento del algoritmo Floyd
    */

    //---------------------------PROPERTIES---------------------------
    private ArrayList<Street> routes = new ArrayList<Street>(); 
    private int [][] weightMatrix; 
    private String[][] distanceMatrix;
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayList<String> newCities = new ArrayList<String>();
    private int inf = 31416;
    private ArrayList<Integer> eccentricity = new ArrayList<Integer>();

    //---------------------------METHODS------------------------------
    /*****************************************************************
     * reads a file and add creates streets
     * @throws FileNotFoundException
     */
    public void read()throws FileNotFoundException{
        try{
            File file = new File("guategrafo.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String[] elements = reader.nextLine().split("[ ]");

                //Create duplicate streets for conexity graph
                newStreet(elements[0], elements[1], Integer.parseInt(elements[2]));
                newStreet(elements[1], elements[0], Integer.parseInt(elements[2]));
            }
            reader.close();
        }catch(FileNotFoundException e){
            String s =  e.getMessage();
			throw new FileNotFoundException(s);
        }
    }
    //*****************************************************************

    /******************************************************************
     * creates cities not duplicated
     */
    public void checkCities(){
        Collections.sort(cities); //Sort the cities

        //Reset the cities (if the array has elements)
        int size = newCities.size();
        if(newCities.size() > 0)
            for(int i = size -1; i >= 0; i-- )
                newCities.remove(i);
        
        //Add the cities in a new array
        if (cities.size() > 0){
            newCities.add(0,cities.get(0));
            for (int i = 1; i < cities.size(); i++){
                if (!cities.get(i).equals(cities.get(i-1)))
                    newCities.add(cities.get(i));
            }
        }
    }
    //****************************************************************

    /*****************************************************************
     * creates a new steet and adds in respective arrays
     * @param origin
     * @param destination
     * @param distance
     */
    public void newStreet(String origin, String destination, int distance){
        Street street = new Street(origin, destination, distance);
        routes.add(street);
        cities.add(origin); 
        cities.add(destination);
    }
    //****************************************************************

    /*****************************************************************
     * returns the position of a street in a group of routes
     * @param origin
     * @param destination
     * @return route position in array
     */
    private int searchStreet(String origin, String destination){
        //Supose that doesnt exist the position
        int pos = routes.size(); 
        boolean checkStreet = false; 

        //Search the index of the street in the array
        for (int i = 0; i < routes.size() && !checkStreet; i++)
            if (routes.get(i).getOrigin().equals(origin) && routes.get(i).getDestination().equals(destination)){
                pos = i;
                checkStreet = true;
            }

        return pos;
    }
    //****************************************************************

    /*****************************************************************
     * sets inf in a street that is "paused"
     * @param origin
     * @param destination
     */
    public void pauseStreet(String origin, String destination){
        int i = searchStreet(origin, destination);
        routes.get(i).setDistance(inf);
        int j = searchStreet(destination, origin);
        routes.get(j).setDistance(inf);
    }
    //****************************************************************

    /*****************************************************************
     * creates the weight and distance matrix (without the floyd algortihm)
     */
    public void createMatrix(){
        checkCities();

        //Instance the matrix with the length of the amount of cities 
        weightMatrix = new int[newCities.size()][newCities.size()];
        distanceMatrix = new String[newCities.size()][newCities.size()];

        //Determine each value of the matrix
        for (int i = 0; i < newCities.size(); i++)
            for (int j = 0; j < newCities.size(); j++){ 
                if (i == j){ //Diagonal
                    weightMatrix[i][j] = 0;
                    distanceMatrix[i][j] = "0";
                }
                else if (searchStreet(newCities.get(i), newCities.get(j)) == routes.size()){ //Doesnt exist path direct
                    weightMatrix[i][j] = inf;
                    distanceMatrix[i][j] = newCities.get(j);
                }
                else{ //Route direct
                    int k = searchStreet(newCities.get(i), newCities.get(j));
                    int distance = routes.get(k).getDistance();
                    weightMatrix[i][j] = distance;
                    distanceMatrix[i][j] = newCities.get(j);
                }
            }        
    }
    //****************************************************************

    /*****************************************************************
     * does the Floyd Warshall Algorithm in both arrays
     */
    public void Floyd(){
        createMatrix();
        int n = newCities.size(); //Amount of cities

        //Algorithm. Source: https://uvg.instructure.com/courses/23918/files/3721618?module_item_id=477568
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (weightMatrix[i][j] > weightMatrix[i][k] + weightMatrix[k][j]){
                        weightMatrix[i][j] = weightMatrix[i][k] + weightMatrix[k][j];
                        distanceMatrix[i][j] = newCities.get(k);
                    }
                }
            }
        }
    }
    //****************************************************************

    /*****************************************************************
     * verify if the graph is conexo
     * @return
     */
    public boolean verifyGraph(){
        Floyd();
        boolean conexo = true;

        //Search for any inf value in the matrix
        for (int i =0 ; i< newCities.size() && conexo;i++)
            for (int j =0 ; j< newCities.size() && conexo;j++)
                if(i!=j && weightMatrix[i][j] == inf){
                    conexo = false;
                }
            
        return conexo;
    }
    //****************************************************************

    /*****************************************************************
     * returns the shortest distance 
     * @param origin
     * @param destination
     * @return
     */
    public String getPath(String origin, String destination) throws Exception{
        try{
            String path = "La distancia más corta es: ";

            //Searchs the value in pos [i-1] [j-1]
            int i = searchCity(origin);
            int j = searchCity(destination);
            path += weightMatrix[i-1][j-1] + " km \n";

            return path;
        }
        catch (Exception e){
            String s =  "No existe la(s) ciudad(es) ingresada(s)";
			throw new Exception(s);
        }

    }
    //****************************************************************

    /*****************************************************************
     * get all the cities between the origin and destination
     * @param origin
     * @param destination
     * @return
     */
    public String getRoute (String origin, String destination) throws Exception{
        try{
            String route = "";

            //Obtains the city between
            int i = searchCity(origin);
            int j = searchCity(destination);
            String city = distanceMatrix[i-1][j-1];

            //Verify if the city searched is the same that the destination
            if (city.equals(destination)){
                route += "";
            }
            else{ //If not, do recursive function with the new city as origin
                route += getRoute(origin, city) + ", " + city;
            }
            
            return route;
        }
        catch (Exception e){
            String s =  "No existe la ciudad ingresada";
			throw new Exception(s);
        }
    }
    //****************************************************************

    /*****************************************************************
     * returns both matrix
     * @return
     */
    public String showMatrix(){
        String wm = "";
        String dm = "";
        String impresion = "";

        //Headers of matrix
        for (int i = 0; i < newCities.size(); i++){
            wm += "\t | \t" + newCities.get(i).charAt(0);
            dm += "\t | \t" + newCities.get(i).charAt(0);
        }
        wm += "\n";
        dm += "\n";

        for (int i = 0; i < newCities.size() + 1; i++){
            wm += "---------------";
            dm += "---------------";
        }
        wm += "\n";
        dm += "\n";

        //Gets each value and concanates
        for (int i = 0; i < newCities.size(); i++){
            wm += newCities.get(i).charAt(0);
            dm += newCities.get(i).charAt(0);
            for (int j = 0; j < newCities.size(); j++){
                wm += "\t | \t" + weightMatrix[i][j];
                dm += "\t | \t" + distanceMatrix[i][j].charAt(0);
            }
            wm += "\n";
            dm += "\n";
        }
        
        impresion += "Matriz de Adyacencia: \n\n" + wm + "\n\n Matriz de dirección: \n\n" + dm;

        return impresion;
    }
    //****************************************************************

    /*****************************************************************
     * searchs city in yhe array
     * @param city
     * @return
     * @throws Exception
     */
    private Integer searchCity(String city) throws Exception{
        try{
            int numero;
            boolean flag =false;

            for (numero = 0; numero < newCities.size() && !flag ; numero++)
                if(newCities.get(numero).equals(city))
                    flag = true;

            if (flag)
                return numero;
            else 
                return null;
        }
        catch (Exception e){
            String s =  "No existe la ciudad ingresada";
			throw new Exception(s);
        }
    }
    //****************************************************************

    /*****************************************************************
     * generates eccentricity of each city
     */
    private void generateEccentricity(){

        //Reset eccentricity 
        int size = eccentricity.size();
        if(eccentricity.size() > 0){
            for(int i = size -1; i >= 0; i-- )
                eccentricity.remove(i);
        }

        //Set the eccentricy as 0
        for(int i=0; i < newCities.size();i++)
            eccentricity.add(i, 0);
        
        //Search the biggest value
        for(int i=0; i< newCities.size(); i++ )
            for(int j=0; j< newCities.size();j++)
                if(weightMatrix[i][j] > eccentricity.get(j))
                    eccentricity.set(j, weightMatrix[i][j]);
    }
    //****************************************************************

    /*****************************************************************
     * returns the center of the matrix
     * @return
     */
    public String getCenter(){
        generateEccentricity();

        int center = inf;
        int index = 0;
        boolean flag = false;

        //Generate the shortest value in eccentricity array
        for (int i =0; i < eccentricity.size(); i++)
            if(center>eccentricity.get(i))
                center = eccentricity.get(i);
        
        //Search the city that is on the center
        for(int i =0; i < eccentricity.size() && !flag; i++)
            if(center == eccentricity.get(i) ){
                index = i;
                flag = true;
            }

        return newCities.get(index) + " con valor de: " + eccentricity.get(index);
    }
    //****************************************************************

    public int[][] getWM(){
        return this.weightMatrix;
    }

    public String[][] getdM(){
        return this.distanceMatrix;
    }

    public ArrayList<String> getCities(){
        return this.newCities;
    }

    public ArrayList<Street> getRoutes(){
        return this.routes;
    }
}