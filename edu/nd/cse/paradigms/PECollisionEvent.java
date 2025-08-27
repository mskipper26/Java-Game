package edu.nd.cse.paradigms;

import java.util.List;
import java.util.ArrayList;

public class PECollisionEvent extends PEEvent
{
    PEWorldObject wo1, wo2;
    
    public PECollisionEvent(PEWorldObject wo1, PEWorldObject wo2)
    {
        this.wo1 = wo1;
        this.wo2 = wo2;
    }

    public List<PEWorldObject> getWorldObjects()
    {
        List<PEWorldObject> objects = new ArrayList<>();

        objects.add(this.wo1);
        objects.add(this.wo2);

        return objects;
    }
}