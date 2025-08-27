import edu.nd.cse.paradigms.*;

public class Enemy extends PESquare
{
    private int dx, dy;
    int sz;
    private int x1, x2, y1, y2;

    public Enemy(int x, int y, int dx, int dy, int sz, int color, int x1, int x2, int y1, int y2)
    {
        this.setCenter(x, y);
        this.dx = dx;
        this.dy = dy;
        this.setSize(sz);
        this.sz = sz;
        this.setColor(color);
        this.setBounds(x1, x2, y1, y2);
    }

    public void tick()
    {
        this.setCenter(this.getX() + dx, this.getY() + dy);
        if (this.getX() - (sz/2) < x1) {
            dx *= -1;
            this.setCenter(x1 + (sz/2), y);
        } else if (this.getX() + (sz/2) > x2) {
            dx *= -1;
            this.setCenter(x2 - (sz/2), y);
        }

        if (this.getY() - (sz/2) < y1) {
            dy *= -1;
            this.setCenter(x, y1 + (sz/2));
        } else if (this.getY() + (sz/2) > y2) {
            dy *= -1;
            this.setCenter(x, y2 - (sz/2));
        }
    }

    public void setBounds(int x1, int x2, int y1, int y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getColor() {
        return this.color;
    }
}