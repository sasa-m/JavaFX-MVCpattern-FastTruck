
package model;

public class Route {
    
    private String id;
    private String truck;
    private String driver;
    private String route_number;
    private String start;
    private String coming;

    public Route(String id, String truck, String driver, String route_number, String start, String coming) {
        this.id = id;
        this.truck = truck;
        this.driver = driver;
        this.route_number = route_number;
        this.start = start;
        this.coming = coming;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getTruck() {
        return truck;
    }


    public void setTruck(String truck) {
        this.truck = truck;
    }


    public String getDriver() {
        return driver;
    }


    public void setDriver(String driver) {
        this.driver = driver;
    }


    public String getRoute_number() {
        return route_number;
    }


    public void setRoute_number(String route_number) {
        this.route_number = route_number;
    }


    public String getStart() {
        return start;
    }


    public void setStart(String start) {
        this.start = start;
    }


    public String getComing() {
        return coming;
    }

 
    public void setComing(String coming) {
        this.coming = coming;
    }
     
    
}
