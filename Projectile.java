import edu.nd.cse.paradigms.*;

public class Projectile extends PESquare
{
    private PESquare target;
    private int speed;

    public Projectile(PECircle source, PESquare target, int speed)
    {
        this.target = target;
        this.speed  = speed;
        this.setCenter(source.getX(), source.getY());
        this.setSize(15);
    }

    public void tick()
    {
        int tx = target.getX();
        int ty = target.getY();

        int dx = tx - this.x;
        int dy = ty - this.y;

        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        if (this.x != tx) {
            this.x += (int)(speed/d * dx);
        } 

        if (this.y != ty) {
            this.y += (int)(speed/d * dy);
        } 
    }

    public PESquare getTarget()
    {
        return this.target;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
    public int getSpeed()
    {
        return this.speed;
    }
}