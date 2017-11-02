package com.thernatfantasy.openskydemo.json;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thernatfantasy.openskydemo.FlightInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by mariuszrafalski on 03.05.2017.
 */

public class FlightData {

    private int time;

    private List<String[]> states;

    public long getTime() {
        return time;
    }



    public List<FlightInfo> getFlightInfoList() {
        Function<String[],FlightInfo> toFlightInfo = new Function<String[], FlightInfo>() {

            @Override
            public FlightInfo apply(String[] state) {
                return new FlightInfo(state[0],state[1],state[2],new Date(time*1000L));
            }
        };
        return Lists.transform(states,toFlightInfo);
    }

    @Override
    public String toString() {
        return "FlightData{" +
                "time=" + time +
                ", states=" + states +
                '}';
    }
}
