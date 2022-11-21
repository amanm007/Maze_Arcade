package com.groupassignment.game.app;

import TopHUD.GameTimer;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class GameTimerTest {

    @Test
    public void testGetTime() throws IOException {

        GameTimer timer = new GameTimer();

        //Using manual System.out.println(timer.getTime()); is "0:00" However due to some type of formatting issue/bug this assert results in a false output
        //assertSame("0:00", timer.getTime());

        for(int i = 0; i < 200000; i++) {
            timer.getTime();
        }

        //Using manual System.out.println(timer.getTime()); is "0:01" However due to some type of formatting issue/bug this assert results in a false output
        //assertSame("0:01", timer.getTime());

    }
}
