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
    int[][] matrix = new int[inf][inf];

    public void read()throws FileNotFoundException{
        try{
            File file = new File("guategrafo.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String[] elements = reader.nextLine().split("[ ]");
                Street street = newStreet(elements[0], elements[1], Integer.parseInt(elements[2]));
                routes.add(street);
                cities.add(elements[0]); cities.add(elements[1]);
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

    public Street newStreet(String origin, String destination, int distance){
        Street street = new Street(origin, destination, distance);
        return street;
    }

    private int searchStreet(String origin, String destination){
        int i;
        boolean checkStreet = false;

        for (i = 0; i < routes.size() && !checkStreet; i++)  
            if (routes.get(i).getOrigin().equals(origin) && routes.get(i).getDestination().equals(destination))
                checkStreet = true;

        return i;
    }

    public void pauseStreet(String origin, String destination){
        int i = searchStreet(origin, destination);
        routes.get(i).setDistance(inf);
    }

    private void fillMatrix (int i, int j, int n){
        int x = 2;

        for (int k = i + 1; k < n; k++){
            matrix[k][j] = x++;
        }
     
        for (int k = 0; k < i; k++){
            matrix[k][j] = x++;
        }
    }

    private void createMatrix(int n){
        int right = n - 1, left = 0;
        for (int i = 0; i < n; i++){
            if (i % 2 == 0){
                matrix[i][right] = 1;
                fillMatrix(i, right, n);
                right--;
            }
             
            else{
                matrix[i][left] = 1;
                fillMatrix(i, left, n);
                left++;
            }
        }
    }

    private void Floyd(){

    }

    private boolean verifyGraph(){

    }

    public String getRoute (String origin, String distance){

    }

    public String showMatrix(){
        
    }

    public String getCenter(){

    }
}