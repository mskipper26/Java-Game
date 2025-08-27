package edu.nd.cse.paradigms;

import java.util.TimerTask;
import java.util.Timer;

public class PECentralClock extends TimerTask
{
    PEEngine engine;
    Timer timer;

    public PECentralClock(int rate, PEEngine engine) 
    {
        this.engine = engine;
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(this, 0, rate);
    }

    public void run()
    {
        // run tick()
        this.engine.tick();
    }
}