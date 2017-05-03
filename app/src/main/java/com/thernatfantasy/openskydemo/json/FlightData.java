package com.thernatfantasy.openskydemo.json;

import java.util.List;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */

public class FlightData {

    private int time;

    private List<String[]> states;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String[]> getStates() {
        return states;
    }

    public void setStates(List<String[]> states) {
        this.states = states;
    }
}
