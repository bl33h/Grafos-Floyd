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

    private ArrayList<Street> routes = new ArrayList<Street>();
    private Integer[][] weightMatrix;
    private String[][] distanceMatrix;
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayList<String> newCities = new ArrayList<String>();
    private int inf = 31416;
    private ArrayList<Integer> eccentricity = new ArrayList<Integer>();

    public Waze() throws FileNotFoundException{
        read();
    }

    private void read()throws FileNotFoundException{
        try{
            File file = new File("guategrafo.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String[] elements = reader.nextLine().split("[ ]");
                newStreet(elements[0], elements[1], Integer.parseInt(elements[2]));
                newStreet(elements[1], elements[0], Integer.parseInt(elements[2]));
            }
            reader.close();
        }catch(FileNotFoundException e){
            String s =  e.getMessage();
			throw new FileNotFoundException(s);
        }
    }

    public void checkCities(){
        Collections.sort(cities);
        int size = newCities.size();

        if(newCities.size() > 0){
            for(int i = size -1; i >= 0; i-- )
                newCities.remove(i);
        }
        

        if (cities.size() > 0){
            newCities.add(0,cities.get(0));
            for (int i = 1; i < cities.size(); i++){
                if (!cities.get(i).equals(cities.get(i-1)))
                    newCities.add(cities.get(i));
            }
        }

    }

    public void newStreet(String origin, String destination, int distance){
        Street street = new Street(origin, destination, distance);
        Street sstreet = new Street(destination, origin, distance);
        routes.add(street);
        routes.add(sstreet);
        cities.add(origin); 
        cities.add(destination);
    }

    /****************************************************************
     * return the position of a street in a group of routes
     * @param origin
     * @param destination
     * @return route position in array
     */
    private int searchStreet(String origin, String destination){
        int pos = routes.size();
        boolean checkStreet = false;

        for (int i = 0; i < routes.size() && !checkStreet; i++)
            if (routes.get(i).getOrigin().equals(origin) && routes.get(i).getDestination().equals(destination)){
                pos = i;
                checkStreet = true;
            }
        return pos;
    }
    //***************************************************************

    public void pauseStreet(String origin, String destination){
        int i = searchStreet(origin, destination);
        routes.get(i).setDistance(inf);
        int j = searchStreet(destination, origin);
        routes.get(j).setDistance(inf);
    }

    public void createMatrix(){
        checkCities();

        weightMatrix = new Integer[newCities.size()][newCities.size()];
        distanceMatrix = new String[newCities.size()][newCities.size()];

        for (int i = 0; i < newCities.size(); i++){
            for (int j = 0; j < newCities.size(); j++){
                if (i == j){
                    weightMatrix[i][j] = 0;
                    distanceMatrix[i][j] = "0";
                }
                else if (searchStreet(newCities.get(i), newCities.get(j)) == routes.size()){
                    weightMatrix[i][j] = inf;
                    distanceMatrix[i][j] = newCities.get(j);
                }
                else{
                    int k = searchStreet(newCities.get(i), newCities.get(j));
                    int distance = routes.get(k).getDistance();
                    weightMatrix[i][j] = distance;
                    distanceMatrix[i][j] = newCities.get(j);
                }
                
            }
        }        
    }

    public void Floyd(){
        createMatrix();
        int n = newCities.size();

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

    public boolean verifyGraph(){
        Floyd();

        boolean conexo = true;
        for (int i =0 ; i< newCities.size() && conexo;i++){
            for (int j =0 ; j< newCities.size() && conexo;j++){
                if(i!=j && weightMatrix[i][j] == inf){
                    conexo = false;
                }
            }
        }
        return conexo;
    }

    public String getPath(String origin, String destination){
        String path = "La distancia más corta es: ";
        int i = searchCity(origin);
        int j = searchCity(destination);
        path += weightMatrix[i-1][j-1] + " km \n";
        return path;
    }

    public String getRoute (String origin, String destination){
        String route = "";
        int i = searchCity(origin);
        int j = searchCity(destination);
        String city = distanceMatrix[i-1][j-1];
        if (city.equals(destination)){
            route += destination;
        }
        else{
            route += city + ", " + getRoute(city, destination);
        }
        return route;
    }

    public String showMatrix(){
        String wm = "";
        String dm = "";
        String impresion = "\t";

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
        System.out.println(wm);
        System.out.println(dm);


        return impresion;
    }

    private int searchCity(String city){
        int numero;
        boolean flag =false;
        for (numero = 0; numero < newCities.size() && !flag ; numero++)
            if(newCities.get(numero).equals(city))
                flag = true;
                if (flag)
                    return numero;
                else return newCities.size();
    }

    private void generateEccentricity(){
        int size = eccentricity.size();

        if(eccentricity.size() > 0){
            for(int i = size -1; i >= 0; i-- )
                eccentricity.remove(i);
        }

        for(int i=0; i < newCities.size();i++)
            eccentricity.add(i, 0);
        for(int i=0; i< newCities.size(); i++ ){
            for(int j=0; j< newCities.size();j++){
                if(weightMatrix[i][j] > eccentricity.get(j))
                    eccentricity.set(j, weightMatrix[i][j]);
            }
        }
    }

    public String getCenter(){
        generateEccentricity();
        System.out.println(eccentricity);
        int center = inf;
        int index = 0;
        boolean flag = false;
        for (int i =0; i < eccentricity.size(); i++)
            if(center>eccentricity.get(i))
                center = eccentricity.get(i);
        
        for(int i =0; i < eccentricity.size() && !flag; i++)
            if(center == eccentricity.get(i) ){
                index = i;
                flag = true;
            }
        return newCities.get(index) ;
    }
}