package edu.nd.cse.paradigms;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class PEEngine extends Frame implements KeyListener
{
    protected PEGame game;
    protected PEScreen screen;
    private List<PEWorldObject> worldObjects;
    private PECentralClock clock;
    private int tickCounter;
    private int checkRate;
    private Queue<PEEvent> eventQueue;

    protected int width = 640;
    protected int height = 480;
    protected int titlebarHeight = 0; // varies by OS
	
    public PEEngine(PEGame game)
    {
        // your code here!
        this.game = game;
        this.screen = new PEScreen(width, height);

        screen.clear();

        setSize(width, height + titlebarHeight);
        setVisible(true);
        clock = new PECentralClock(25, this);
        worldObjects = new ArrayList<>();
        eventQueue = new LinkedList<>();
        this.addKeyListener(this);
        tickCounter = 0;
        checkRate   = 5;
    }
	
    public void paint(Graphics g)
    {
        g.drawImage(this.screen.render(), 0, titlebarHeight, width, height, Color.BLACK, null);
    }

    public void update(Graphics g)
    {
        this.paint(g);
    }

    public void add(PEWorldObject wo)
    {
        this.worldObjects.add(wo);
    }

    public void remove(PEWorldObject wo)
    {
        this.worldObjects.remove(wo);
    }

    public void tick() 
    {
        this.game.tick();
        this.screen.clear();

        if (tickCounter % checkRate == 0)
        {
            List<PEWorldObject> copyOfWorldObjects = new ArrayList<>(worldObjects);
            for (PEWorldObject wo1 : copyOfWorldObjects)
            {
                for (PEWorldObject wo2 : worldObjects)
                {
                    if (wo1 != wo2)
                    {
                        if (detectCollision(wo1, wo2)) 
                        {
                            PECollisionEvent c = new PECollisionEvent(wo1, wo2);
                            eventQueue.offer(c);
                        }
                    }
                }
            }
        }
        eventLoopIterate();

        for (PEWorldObject wo : worldObjects)
        {
            wo.tick();
            wo.render(this.screen);
        }

        this.repaint();
        tickCounter++;
    }
    
    public boolean detectCollision(PEWorldObject wo1, PEWorldObject wo2)
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (wo1.inObjectBoundary(i, j) && wo2.inObjectBoundary(i, j))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void keyPressed(KeyEvent evt)
    {
        PEKeyEvent kevt = new PEKeyEvent(evt);
        eventQueue.offer(kevt);
    }

    public void keyReleased(KeyEvent evt)
    {

    }

    public void keyTyped(KeyEvent evt)
    {
        
    }

    private void processEvent(PEKeyEvent evt)
    {
        int keycode = evt.getKeyCode();
        game.keyPressed(keycode);
    }
    
    private void processEvent(PECollisionEvent evt)
    {
        game.collisionDetected(evt.getWorldObjects());
    }
    
    private void eventLoopIterate()
    {
        while (eventQueue.size() > 0)
        {
            PEEvent evt = eventQueue.poll();

            if (evt instanceof PECollisionEvent)
            {
                processEvent((PECollisionEvent)evt);
                continue;
            } 
            else if (evt instanceof PEKeyEvent)
            {
                processEvent((PEKeyEvent)evt);
            }
        }
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }
    
}
