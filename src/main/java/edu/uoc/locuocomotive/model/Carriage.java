package edu.uoc.locuocomotive.model;

public class Carriage {
    private String id;
    private CarriageType type;
    private int seats;



    public Carriage(String id, CarriageType type, int seats)  {
        setId(id);
        setType(type);
        setSeats(seats);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public CarriageType getType() {
        return type;
    }

    private void setType(CarriageType type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    private void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Carriage{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", currentSeats=" + seats +
                '}';
    }
}
