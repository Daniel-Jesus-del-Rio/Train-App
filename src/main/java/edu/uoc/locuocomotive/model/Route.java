package edu.uoc.locuocomotive.model;

public class Route {
    public String id;
    public int trainId;
    private int[] stations;
    private String[][] times;

    public Route(String id, int trainId, String... StationandTime){

        setId(id);
        setTrainId(trainId);

        stations = new int[StationandTime.length];
        times = new String[StationandTime.length][2];

        for(int i = 0; i < StationandTime.length; i++){
            String[] parts = StationandTime[i].split("\\[");
            stations[i] = Integer.parseInt(parts[0]);
            times[i][0] = parts[1].split(",")[0];
            times[i][1] = parts[1].split(",")[1].replace("]", "");
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getTrainId() {
        return trainId;
    }

    public int[] getStations() {
        return stations;
    }

    public void setStations(int[] stations) {
        this.stations = stations;
    }

    public String[][] getTimes() {
        return times;
    }

    public void setTimes(String[][] times) {
        this.times = times;
    }
}
