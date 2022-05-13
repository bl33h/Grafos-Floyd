import java.util.Map;
import java.util.LinkedHashMap;

public class Street {
    private Map<String, String> cities = new LinkedHashMap<String, String>();
    private int distance;

    public Street(String origin, String destination, int distance){
        this.cities.put(origin, destination);
        this.distance = distance;
    }

    public String getDestination(String origin){
        return this.cities.get(origin);
    }

    public int getDistance(){
        return this.distance;
    }
}
