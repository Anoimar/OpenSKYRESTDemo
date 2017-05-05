package com.thernatfantasy.openskydemo.json;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thernatfantasy.openskydemo.UsefulState;

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



    public List<UsefulState> getUsefulStates() {
        Function<String[],UsefulState> toUsefulState = new Function<String[], UsefulState>() {

            @Override
            public UsefulState apply(String[] state) {
                return new UsefulState(state[0],state[1],state[2]);
            }
        };
        return Lists.transform(states,toUsefulState);
    }


}
