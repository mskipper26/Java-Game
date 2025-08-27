package edu.nd.cse.paradigms;

import java.util.List;

public abstract class PEGame
{
    public abstract void start();
    
    public abstract void tick();

    public abstract void keyPressed(int keycode);
    
    public abstract void collisionDetected(List<PEWorldObject> worldObjects);
}