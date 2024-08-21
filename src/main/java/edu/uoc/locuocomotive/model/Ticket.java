package edu.uoc.locuocomotive.model;

import java.time.LocalTime;

public class Ticket {


    private String passport;
    private String routeid;
    private LocalTime arriveTime;
    private LocalTime depTime;
    private double cost;
    private int originStationId;
    private int fateStationId;
    private String SelectType;
    private int carriage;
    private int seat;

    public Ticket(String passport, String routeid, LocalTime depTime, LocalTime arriveTime, double cost, int originStationId, int fateStationId, String selectType, int carriage, int seat) {
        setPassport(passport);
        setRouteid(routeid);
        setArriveTime(arriveTime);
        setDepTime(depTime);
        setCost(cost);
        setOriginStationId(originStationId);
        setFateStationId(fateStationId);
        setCarriage(carriage);
        setSeat(seat);
        setSelectType(selectType);
    }

    public int getCarriage() {
        return carriage;
    }

    private void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    public int getSeat() {
        return seat;
    }

    private void setSeat(int seat) {
        this.seat = seat;
    }

    public String getPassport() {
        return passport;
    }

    private void setPassport(String passport) {
        this.passport = passport;
    }

    public String getRouteid() {
        return routeid;
    }

    private void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    private void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalTime getDepTime() {
        return depTime;
    }

    private void setDepTime(LocalTime depTime) {
        this.depTime = depTime;
    }

    public double getCost() {
        return cost;
    }

    private void setCost(double cost) {
        this.cost = cost;
    }

    public int getOriginStationId() {
        return originStationId;
    }

    private void setOriginStationId(int originStationId) {
        this.originStationId = originStationId;
    }

    public int getFateStationId() {
        return fateStationId;
    }

    private void setFateStationId(int fateStationId) {
        this.fateStationId = fateStationId;
    }

    public String getSelectType() {
        return SelectType;
    }

    private void setSelectType(String selectType) {
        SelectType = selectType;
    }
}