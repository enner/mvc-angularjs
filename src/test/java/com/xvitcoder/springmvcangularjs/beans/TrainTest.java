package com.xvitcoder.springmvcangularjs.beans;
import static org.junit.Assert.*;

import org.junit.Test;

import com.xvitcoder.springmvcangularjs.beans.Train;

public class TrainTest {

    @Test
    public void testTrainSpeed() {
        Train myTrain = new Train();
        myTrain.setSpeed(32);
        assertEquals(myTrain.getSpeed().toString(), "32");
    }

}
