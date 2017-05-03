package com.thernatfantasy.openskydemo.json;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thernatfantasy.openskydemo.State;
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



    public List<State> getStates() {
        Function<String[],State> toUsefulState = new Function<String[], State>() {

            @Override
            public State apply(String[] state) {
                return new State(state[0],state[1],state[2]);
            }
        };
        return Lists.transform(states,toUsefulState);
    }


}
