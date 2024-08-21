package edu.uoc.locuocomotive.controller;

import edu.uoc.locuocomotive.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class LocUOComotiveController {

    //TODO: Define the attributes
    List <Station> stations;
    Map<String, Passenger> passengers;

    List<Train> trains;
    List<Route> routesd;

    List<Ticket> tickets;

    int stationId;
     public LocUOComotiveController(String stationsFile, String routesFile, String trainsFile) {
        //TODO: Implement the constructor
         routesd=new ArrayList<>();
        trains=new ArrayList<>();
        passengers=new HashMap<>();
        stations=new ArrayList<>();
        tickets = new ArrayList<>();
        stationId = 1;
        loadStations(stationsFile);
        loadRoutes(routesFile);
        loadTrains(trainsFile);
    }

    private void loadStations(String stationsFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + stationsFile);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + stationsFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Create the station and add it to the list of stations
                addStation(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRoutes(String routesFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + routesFile);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + routesFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split by character :
                String[] parts = line.split("=");
                String[] parts2 = parts[0].split("\\|");

                // Create the route and add it to the list of routes
                addRoute(parts2[0], Integer.parseInt(parts2[1]), parts[1].split("\\|"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTrains(String trainsFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + trainsFile);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + trainsFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                int[] seatsPerCar = new int[parts.length - 2];

                for (int i = 2; i < parts.length; i++) {
                    seatsPerCar[i - 2] = Integer.parseInt(parts[i]);
                }

                addTrain(Integer.parseInt(parts[0]), parts[1], seatsPerCar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStation(int id, String name, String city, int openingYear, String type, String image, int positionX, int positionY) {
        //TODO

        Station nS= new Station(id, name, city,openingYear, type, image, positionX, positionY);
        stations.add(nS);
    }

    public void addRoute(String id, int trainId, String... stationsAndTimes) {
        //TODO


        Route rt= new Route(id, trainId, stationsAndTimes);
        routesd.add(rt);
    }

    public void addTrain(int id, String model, int... cars) {
        //TODO
        Train nTrain= new Train(id, model, cars);

        trains.add(nTrain);

    }

    public List<String> getStationsInfo() {
        //TODO

        return stations.stream()
                .map(station -> String.join("|",
                        String.valueOf(station.getId()),
                        station.getStation_name(),
                        station.getCity(),
                        station.getImage(),
                        String.valueOf(station.getX()),
                        String.valueOf(station.getY())))
                .collect(Collectors.toList());
    }

    public String[] getSeatTypes() {
        //TODO
        CarriageType[] types = CarriageType.values();
        String[] typeStrings = new String[types.length];

        for (int i = 0; i < types.length; i++) {
            typeStrings[i] = types[i].name();
        }

        return typeStrings;

    }


    public List<String> getRoutesByStation(int stationId) {
        //TODO
        String departureTime1, departureTime2, arrivalTime1, arrivalTime2;
        String departureName = null, arrivalName = null;
        List<String> routes = new ArrayList<>();
        Integer ticketCost;
        // Name of the destiny station
        for(Station s : stations){
            if(s.getId() == stationId){
                arrivalName = s.getStation_name(); // we take the station name
            }
        }

        for(Route r : routesd){
            for(int i = 1; i < r.getStations().length; i++){
                if(stationId == r.getStations()[i]){
                    int posicion = -1;
                    for (int j = i-1; j >= 0; j--) {
                        if(r.getStations()[j] == this.stationId){
                            posicion = j;
                        }
                    }

                    if(posicion != -1){
                        // I search for arrival and departure time

                        arrivalTime1 = r.getTimes()[i][0];
                        arrivalTime2   = r.getTimes()[i][1];

                        departureTime1 = r.getTimes()[posicion][0];
                        departureTime2 = r.getTimes()[posicion][1];

                        // I search destiny station
                        for(Station s : stations){
                            if(s.getId() == r.getStations()[posicion]){
                                departureName = s.getStation_name();
                            }
                        }

                        // First Train

                        Integer hora1 =Integer.parseInt(departureTime1.split(":")[0]);
                        Integer hora2 =Integer.parseInt(arrivalTime1.split(":")[0]);
                        Integer minutos1 =Integer.parseInt(departureTime1.split(":")[1]);
                        Integer minutos2 =Integer.parseInt(arrivalTime1.split(":")[1]);

                        int tiempoTotal1 = hora1 * 60 + minutos1;
                        int tiempoTotal2 = hora2 * 60 + minutos2;

                        int diferencia = tiempoTotal2 - tiempoTotal1;

                        if (diferencia < 0) {
                            diferencia += 24 * 60;
                        }

                        ticketCost = (int) (diferencia/60.0)*30;

                        routes.add(
                                departureTime1 + "-" + arrivalTime1 + "|" + r.getId() + "|" +
                                        ticketCost + "|" + r.getStations()[posicion] + "|" + stationId + "|" +
                                        departureName + "|" + arrivalName
                        );

                        // Second Train
                        hora1 =Integer.parseInt(departureTime2.split(":")[0]);
                        hora2 =Integer.parseInt(arrivalTime2.split(":")[0]);
                        minutos1 =Integer.parseInt(departureTime2.split(":")[1]);
                        minutos2 =Integer.parseInt(arrivalTime2.split(":")[1]);

                        tiempoTotal1 = hora1 * 60 + minutos1;
                        tiempoTotal2 = hora2 * 60 + minutos2;

                        diferencia = tiempoTotal2 - tiempoTotal1;

                        if (diferencia <= 0) {
                            diferencia += 24 * 60;
                        }

                        ticketCost = (int) (diferencia/60.0)*30;
                        routes.add(
                                departureTime2 + "-" + arrivalTime2 + "|" + r.getId() + "|" +
                                        ticketCost + "|" + r.getStations()[posicion] + "|" + stationId + "|" +
                                        departureName + "|" + arrivalName
                        );
                    }
                }
            }
        }
        routes.sort(null);
        return routes;
    }

    public void addPassenger(String passport, String name, String surname, LocalDate birthdate, String email) throws Exception {
        //TODO
        if (passengers.containsKey(passport)) {

            Passenger existingPassenger = passengers.get(passport);
            existingPassenger.setName(name);
            existingPassenger.setSurname(surname);
            existingPassenger.setBirth(birthdate);
            existingPassenger.setEmail(email)
            ;
        } else {

            Passenger newPassenger = new Passenger(passport, name, surname, birthdate, email);
            passengers.put(passport, newPassenger);
        }
    }

    public void createTicket(String passport, String routeId, LocalTime departureTime, LocalTime arrivalTime, double cost, int originStationId, int destinationStationId, String selectedSeatType) throws Exception {
        //TODO

        int train = -1;
        int carriage = -1;
        int seat = 1;
        if (passengers.containsKey(passport)) { // Passenger must exist.
            // Since the person exists, I will see which train they need to take.

            // I search for the route to see which train needs to be taken.
            for (Route r2 : routesd) {
                if (r2.getId().equals(routeId)) {
                    train = r2.trainId;
                }
            }

            if(train == -1){
                throw new Exception("Route does not exist");
            }

            // Calculate Carriage
            for(Train t : trains){
                if(t.getId() == train){
                    for(Carriage car : t.carriages){
                        if(car.getType() != null && car.getType().equals(CarriageType.valueOf(selectedSeatType))){
                            carriage = Integer.parseInt(car.getId().replace("C", ""));
                            break;
                        }
                    }
                    if(carriage != -1){
                        break;
                    }
                }
            }

            if(carriage == -1){
                throw new Exception("There are no carriages of that class on the train.");
            }




            tickets.add(new Ticket(passport, routeId, departureTime, arrivalTime, cost, originStationId,
                    destinationStationId, selectedSeatType, carriage, seat));
            stationId = destinationStationId;
        }

    }
    public void buyTicket(String passport, String name, String surname, LocalDate birthdate, String email,
                            String routeId, LocalTime departureTime, LocalTime arrivalTime, double cost, int originStationId, int destinationStationId, String selectedSeatType) throws Exception {
        //TODO
        if(!passengers.containsKey(passport)){
            // Create passenger if he does not exist.
            addPassenger(passport, name,surname,birthdate,email);
        }
        createTicket(passport,routeId,departureTime,arrivalTime,cost,originStationId,destinationStationId,selectedSeatType);
    }

    public List<String> getAllTickets() {
        //TODO
        List<String> ticketStrings = new ArrayList<>();
        String departureStationName = null;
        String destinationStationName = null;
        for (Ticket ticket : tickets) {

            // I search for the station name.
            for(Station st : stations){
                if(st.id == ticket.getOriginStationId()){
                    departureStationName = st.station_name;
                }
                if(st.id == ticket.getFateStationId()){
                    destinationStationName = st.station_name;
                }
            }
            //I take the values and add it to the arraylist
            String ticketInfo = ticket.getRouteid() + "|" +
                    ticket.getDepTime() + "|" +
                    departureStationName + "|" +
                    ticket.getArriveTime() + "|" +
                    destinationStationName + "|C" +
                    ticket.getCarriage() + "-" +
                    ticket.getSeat() + "|" +
                    ticket.getCost();

            ticketStrings.add(ticketInfo);
        }
        return ticketStrings;
    }

    public String getPassengerInfo(String passport) {
        //TODO
        //Check if the passenger exists
        if (passengers.containsKey(passport)) {
            Passenger pasi = passengers.get(passport);
            return pasi.getPassport() + "|" + pasi.getName() + "|" + pasi.getSurname() + "|" + pasi.getBirth() + "|" + pasi.getEmail();
        }
        // If the passenger is not found, return an empty string
        return "";

    }

    public String getTrainInfo(int trainId) {
        //TODO
        for (Train t:trains){
            if (Objects.equals(t.getId(), trainId)){
                return t.getId()+"|"+t.getModel()+"|"+ t.getCarriages().length ;
            }
        }
        return "";
    }

    public List<String> getPassengerTickets(String passport) {
        //TODO
        List<String> ticketStrings = new ArrayList<>();
        String departureStationName = null;
        String destinationStationName = null;
        // I search in the tickets
        for (Ticket ticket : tickets) {

            // I search for the station name.
            for(Station st : stations){
                if(st.id == ticket.getOriginStationId()){
                    departureStationName = st.station_name;
                }
                if(st.id == ticket.getFateStationId()){
                    destinationStationName = st.station_name;
                }
            }
            //If ticket Passport has the same parameter number, I take the values from ticket.
            if(ticket.getPassport().equals(passport)) {
                String ticketInfo = ticket.getRouteid() + "|" +
                        ticket.getDepTime() + "|" +
                        departureStationName + "|" +
                        ticket.getArriveTime() + "|" +
                        destinationStationName + "|C" +
                        ticket.getCarriage() + "-" +
                        ticket.getSeat() + "|" +
                        ticket.getCost();

                ticketStrings.add(ticketInfo);
            }
        }
        return ticketStrings;
    }

    public List<String> getRouteDeparturesInfo(String routeId) {
        //TODO
        List<String> departuresInfo = new ArrayList<>();
        for (Route r : routesd) {
            if (r.getId().equals(routeId)) {
                for (int i = 0; i < r.getStations().length; i++) {
                    departuresInfo.add(r.getStations()[i] + "|[" + r.getTimes()[i][0] + ", " +  r.getTimes()[i][1] + "]" );
                }
                break;
            }
        }
        return departuresInfo;

    }
    public int getCurrentStationId() {
        //TODO

        return stationId;
    }

}
