package edu.uoc.locuocomotive.model;

import java.util.*;

public class Train {

    private int id;
    private String model;
    public Carriage[] carriages;

    public Train(int id, String model, int... vag){
        setId(id);
        setModel(model);
        carriages = new Carriage[vag.length];

        for(int i = 0; i < vag.length; i++){ // each means seats of each carriage
            if(vag[i] == 0){
                carriages[i] = new Carriage("C" + (i+1), null, 0);
            } else if (vag[i] < 20) {
                carriages[i] = new Carriage("C" + (i+1), CarriageType.FIRST_CLASS, vag[i]);
            } else if (vag[i] < 50) {
                carriages[i] = new Carriage("C" + (i+1), CarriageType.SECOND_CLASS, vag[i]);
            }
            else{
                carriages[i] = new Carriage("C" + (i+1), CarriageType.THIRD_CLASS, vag[i]);
            }
        }
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setCarriages(Carriage[] carriages) {
        this.carriages = carriages;
    }

    public Carriage[] getCarriages() {
        return carriages;
    }

}
