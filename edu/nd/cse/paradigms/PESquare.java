package edu.nd.cse.paradigms;

public class PESquare extends PEWorldObject
{
    protected int size;

    public PESquare()
    {

    }

    public void tick()
    {
        
    }

    public void render(PEScreen screen)
    {
        // draw square on screen (set pixel)
        int x = this.x;
        int y = this.y;

        for (int i = x - (size/2); i < x + (size/2); i++) {
            for (int j = y - (size/2); j < y + (size/2); j++) {
                screen.setPixel(i, j, this.color);
            }
        }
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public boolean inObjectBoundary(int qx, int qy)
    {
        int x = this.x;
        int y = this.y;

        
        if (qx >= x-(size/2) && qx <= x+(size/2) && qy >= y-(size/2) && qy <= y+(size/2)) {
            return true;
        }

        return false;
    }
}