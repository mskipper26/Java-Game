package edu.nd.cse.paradigms;

import edu.nd.cse.paradigms.PEScreen;
import edu.nd.cse.paradigms.PESquare;
import edu.nd.cse.paradigms.PEWorldObject;

public class PECircle extends PEWorldObject
{
    protected int radius;

    public PECircle()
    {

    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public void tick()
    {

    }

    public boolean inObjectBoundary(int qx, int qy)
    {
        int x = this.x;
        int y = this.y;

        int di = qx - x;
        int dj = qy - y;

        if (di * di + dj * dj <= radius * radius) {
            return true;
        }

        return false;
    }

    public void render(PEScreen screen)
    {
        int x = this.x;
        int y = this.y;

        // iterate through pixels in a square around the circle to render
        for (int i = x - radius; i < x + radius; i++) {
            for (int j = y - radius; j < y + radius; j++) {
                // get point's distance from center in x and y directions
                int di = i - x;
                int dj = j - y;

                // check if point is within circle using circle eq, if so setPixel
                if (di * di + dj * dj <= radius * radius) {
                    screen.setPixel(i, j, this.color);
                }
            }
        }
    }
}