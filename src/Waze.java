package src;

import java.io.File;  
import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Waze {
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
    private ArrayList<ArrayList<Integer>> weightMatrix = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<String>> distanceMatrix = new ArrayList<ArrayList<String>>();
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayList<String> newCities = new ArrayList<String>();
    private int inf = 314159265;
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
            }
            reader.close();
        }catch(FileNotFoundException e){
            String s =  e.getMessage();
			throw new FileNotFoundException(s);
        }
        
    }

    private void checkCities(){
        if (cities.size() > 0){
            newCities.add(cities.get(0)); 
            for (int i = 1; i < cities.size(); i++){
                if (!cities.get(i).equals(cities.get(i-1)))
                    newCities.add(cities.get(i));
            }
        }
    }

    public void newStreet(String origin, String destination, int distance){
        Street street = new Street(origin, destination, distance);
        routes.add(street);
        cities.add(origin); 
        cities.add(destination);
    }

    private int searchStreet(String origin, String destination){
        int i;
        boolean checkStreet = false;

        for (i = 0; i < routes.size() || !checkStreet; i++)
            if (routes.get(i).getOrigin().equals(origin) && routes.get(i).getDestination().equals(destination))
                checkStreet = true;
        if (checkStreet)
            return i;
        else return routes.size();
    }

    public void pauseStreet(String origin, String destination){
        int i = searchStreet(origin, destination);
        routes.get(i).setDistance(inf);
    }

    private void createMatrix(){
        checkCities();

        ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
        ArrayList<String> tempStringArray = new ArrayList<String>();

        for (int i = 0; i < routes.size(); i++){
            for (int j = 0; j < routes.size(); j++){
                if (i == j){
                    tempIntArray.set(j, 0);
                    tempStringArray.set(j, "0");
                }
                else if (searchStreet(newCities.get(i), newCities.get(j)) == routes.size()){
                    tempIntArray.set(j, inf);
                }
                else{
                    int k = searchStreet(newCities.get(i), newCities.get(j));
                    int distance = routes.get(k).getDistance();
                    tempIntArray.set(j, distance);
                    tempStringArray.set(j, newCities.get(j));
                }
            }
            weightMatrix.set(i, tempIntArray);
            distanceMatrix.set(i, tempStringArray);
        }
    }

    private void Floyd(){
        createMatrix();
        int n = 0;
        int i, j, k;
        ArrayList<Integer> tempIntArray = new ArrayList<Integer>();
        ArrayList<String> tempStringArray = new ArrayList<String>();

        for (k = 1; k < n; k++) {
            for (i = 0; i < n; i++){
                for (j = 0; j < n; j++){
                    if (weightMatrix.get(i).get(j) > weightMatrix.get(i).get(k) + weightMatrix.get(k).get(j)){
                        tempIntArray.set(j, weightMatrix.get(i).get(k) + weightMatrix.get(k).get(j));
                        tempStringArray.set(j, newCities.get(i));
                    }
                    else{
                        tempIntArray.set(j, weightMatrix.get(i).get(j));
                        tempStringArray.set(j, newCities.get(j));
                    }
                }
                weightMatrix.set(i, tempIntArray);
                distanceMatrix.set(i, tempStringArray);
            }
        }
    }

    public boolean verifyGraph(){
        Floyd();
        boolean conexo = true;
        for (int i =0 ; i<weightMatrix.size() && conexo;i++){
            for (int j =0 ; j<weightMatrix.size() && conexo;j++){
                if(i!=j && weightMatrix.get(i).get(j)==inf){
                    conexo = false;
                }
            }
        }
        return conexo;
    }

    public String getRoute (String origin, String destination){
        String route = "";
            int i = searchCity(origin);
            int j = searchCity(destination);
            String city = distanceMatrix.get(i).get(j);
            if (city.equals(destination)){
                route += destination;
            }
            else{
                route += city + getRoute(city, destination);
            }
            return route;
    }

    public String showMatrix(){
        String impresion = "\t";

        if (verifyGraph()){
            for (int x=0; x < newCities.size(); x++){
               impresion += newCities.get(x);
           }
           
           for (int x=0; x < weightMatrix.size(); x++){
               impresion += newCities.get(x);
               for(int y=0; y < weightMatrix.get(x).size();y++){
                   impresion += weightMatrix.get(x).get(y);
                   if(y!=weightMatrix.get(x).size()-1){
                       impresion += "\t";
                   }
               }
           }
           for (int x=0; x < newCities.size(); x++){
               impresion += newCities.get(x);
            }
           
           for (int x=0; x < distanceMatrix.size(); x++){
               impresion += newCities.get(x);
               for(int y=0; y < distanceMatrix.get(x).size();y++){
                   impresion += distanceMatrix.get(x).get(y);
                   if (y!=distanceMatrix.get(x).size()-1){
                       impresion += "\t";
                    }
                }
            }
        }
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
        for(int i=0; i < newCities.size();i++)
            eccentricity.set(i, 0);
        for(int i=0; i< weightMatrix.size(); i++ ){
            for(int j=0; j<weightMatrix.size();j++){
                if(weightMatrix.get(i).get(j)>eccentricity.get(j))
                    eccentricity.set(j, weightMatrix.get(i).get(j));
            }
        }
    }

    public String getCenter(){
        generateEccentricity();
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