public class Street {
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
