package edu.nd.cse.paradigms;

import java.awt.image.BufferedImage;

public class PEScreen
{
    private int width, height;
    private int bg;
	
    private BufferedImage image;
    private int[][] pixels;
	
    public PEScreen(int width, int height)
    {
        this.width = width;
        this.height = height;
		
        this.bg = 0x22CC11; // default background color is green
		
        // your code here
        this.pixels = new int[width][height];
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

	// set pixel at px, py to color
    public void setPixel(int px, int py, int color)
    {
        // your code here
        if (inBounds(px, py)) {
            this.pixels[px][py] = color;
        }
    }
	// set every pixel to background color
    public void clear()
    {
        // your code here
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.setPixel(i, j, bg);
            }
        }
    }
	// check if point px, py is in bounds
    public boolean inBounds(int px, int py)
    {
        // your code here
        if (px < 0 || px >= this.width) {
            return false;
        }
        if (py < 0 || py >= this.height) {
            return false;
        }
        return true;
    }
	// return BufferedImage form of pixels array
    public BufferedImage render()
    {
        // your code here
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.image.setRGB(i, j, this.pixels[i][j]);
            }
        }

        return image;
    }
    public void renderWorldObject(PEWorldObject wo)
    {
        wo.render(this);
    }
}
