
package model;

public class Truck {
    
    private String id;
    private String model;
    private String year;
    private String registration;
    private String capacity;
    private String size;

    public Truck(String id, String model, String year, String registration, String capacity, String size) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.registration = registration;
        this.capacity = capacity;
        this.size = size;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        this.model = model;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public String getRegistration() {
        return registration;
    }


    public void setRegistration(String registration) {
        this.registration = registration;
    }


    public String getCapacity() {
        return capacity;
    }


    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }
    
    
    
}
