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

    public void read()throws FileNotFoundException{
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

    private void Floyd(int weightMatrix){
        createMatrix();
        for (int source = 1; source <= routes.size(); source ++){
            for (int destination = 1; destination <= routes.size(); destination ++){
                distanceMatrix.get(source).get(destination) = weightMatrix.get(source).get(destination);
            }
        }

        for (int intermediate = 1; intermediate <= routes.size(); intermediate++){
            for (int source = 1; source <= routes.size(); source++){
                for (int destination = 1; destination <= routes.size(); destination++){
                    if(distanceMatrix.get(source).get(intermediate) + distanceMatrix.get(intermediate).get(destination) < distanceMatrix.get(source).get(destination)){
                        distanceMatrix.get(source).get(destination) = distanceMatrix.get(intermediate).get(destination);
                    }
                }
            }
        }
    }

    private boolean verifyGraph(){
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

    public String getRoute (String origin, String distance){
        

    }

    public String showMatrix(){
    String impresion = "\t";
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
           if(y!=distanceMatrix.get(x).size()-1){
               impresion += "\t";
           }
       }
   }
   return impresion;
}

    public String getCenter(){

    }
}