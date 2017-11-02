package com.thernatfantasy.openskydemo.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mariuszrafalski on 02.11.2017.
 */

public class TimeProvider {

    public long getTimeOneHourAgo(){
        Date now = new Date();
        return now.getTime() - TimeUnit.HOURS.toMillis(1);
    }
}
