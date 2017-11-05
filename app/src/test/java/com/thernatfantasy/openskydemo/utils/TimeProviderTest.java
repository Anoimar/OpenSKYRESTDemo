package com.thernatfantasy.openskydemo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.*;
/**
 * Created by mariuszrafalski on 03.11.2017.
 */

@PrepareForTest({TimeProvider.class})
@RunWith(PowerMockRunner.class)
public class TimeProviderTest {
    private static final long CURRENT_TIME = 1509699600000L;
    private static final long ONE_HOUR_BEFORE_CURRENT_TIME = 1509696000000L;
    private long result;

    @Test
    public void shouldReturnTimeFromHourAgo() throws Exception {
        givenNowWeHaveADefaultTime();
        whenWeCallGetTimeOneHourAgo();
        thenWeGetTimeFromOneHourAgo();
    }

    private void givenNowWeHaveADefaultTime() throws Exception {
        Date date = PowerMockito.mock(Date.class);
        PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(date);
        PowerMockito.when(date.getTime()).thenReturn(CURRENT_TIME);
    }


    private void whenWeCallGetTimeOneHourAgo() {
        result = new TimeProvider().getTimeOneHourAgo();
    }

    private void thenWeGetTimeFromOneHourAgo() {
        assertEquals(ONE_HOUR_BEFORE_CURRENT_TIME,result);
    }

}