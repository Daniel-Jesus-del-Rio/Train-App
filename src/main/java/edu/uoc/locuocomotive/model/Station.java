package edu.uoc.locuocomotive.model;

public class Station {
    public int id;
    public String station_name;
    public String city;
    public int opening_year;
    public String type;
    public String image;
    public int X;
    public int Y;

    public Station(int id, String name,String city, int year, String type, String image, int X, int Y){
            setId(id);
            setCity(city);
            setStation_name(name);
            setOpening_year(year);
            setType(type);
            setImage(image);
            setX(X);
            setY(Y);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_name() {
        return station_name;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setOpening_year(int opening_year) {
        this.opening_year = opening_year;
    }

    public int getOpening_year() {
        return opening_year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
