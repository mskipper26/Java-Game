import edu.nd.cse.paradigms.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MyGame extends PEGame
{
    protected PEEngine engine;

    private PECircle circle;
    private PESquare target;
    private Enemy enemy1, enemy2;
    private Collider c;
    private boolean dead = false;
    private int projSpeed = 3;
    private List<Projectile> projectiles;
    private int chance = 1000;
    private Random random;
    private PowerupFactory pfact;
    boolean started = false;

    public MyGame()
    {
        
    }

    public void start()
    {
        engine = new PEEngine(this);

        circle = new PECircle();
        circle.setRadius(25);
        circle.setCenter(100, 400);
        circle.setColor(0xFFFFFF);

        engine.add(circle);

        target = new PESquare();
        target.setSize(20);
        target.setCenter(100, 200);
        target.setColor(0x3295a8);

        engine.add(target);
        
        enemy1 = new Enemy(250, 250, 2, 1, 80, 0x736e80, 100, 600, 200, 400);
        engine.add(enemy1);

        enemy2 = new Enemy(250, 100, 3, 0, 100, 0x736e80, 100, 600, 0, 200);
        engine.add(enemy2);
        
       c = new HardCollider();

       projectiles = new ArrayList<>();
       random = new Random();
       pfact = new PowerupFactory();
       started = true;
    }

    
    // on every tick, there is a 1 in [chance] chance that a powerup will appear
    // set to 1000
    public void tick()
    {
        if (started)
        {
            int rand = random.nextInt(chance);
            if (rand == 0)
            {
                pfact.createShape(this.engine);
            }
        }
    }
    public void keyPressed(int keycode)
    {
        switch(keycode)
        {
            case PEKeyEvent.VK_DOWN:
                circle.setCenter(circle.getX(), circle.getY() + 5);
                break;
            case PEKeyEvent.VK_UP:
                circle.setCenter(circle.getX(), circle.getY() - 5);
                break;
            case PEKeyEvent.VK_RIGHT:
                circle.setCenter(circle.getX() + 5, circle.getY());
                break;
            case PEKeyEvent.VK_LEFT:
                circle.setCenter(circle.getX() - 5, circle.getY());
                break;
            case PEKeyEvent.VK_W:
                target.setCenter(target.getX(), target.getY() - 5);
                break;
            case PEKeyEvent.VK_A:
                target.setCenter(target.getX() - 5, target.getY());
                break;
            case PEKeyEvent.VK_S:
                target.setCenter(target.getX(), target.getY() + 5);
                break;
            case PEKeyEvent.VK_D:
                target.setCenter(target.getX() + 5, target.getY());
                break;
            case PEKeyEvent.VK_SPACE:
                //create projectile
                if (!dead) {
                    Projectile p = new Projectile(circle, target, this.projSpeed);
                    engine.add(p);
                    projectiles.add(p);
                }
        }
    }
    public void collisionDetected(List<PEWorldObject> worldObjects)
    {
        PEWorldObject a = worldObjects.get(0);
        PEWorldObject b = worldObjects.get(1);
        
        if (a == this.circle && b instanceof Enemy) {
            dead = true;
            engine.remove(a);
        } else if (b == this.circle && a instanceof Enemy) {
            dead = true;
            engine.remove(b);
        } else if (a instanceof Projectile && b == target) {
            engine.remove(a);
        } else if (b instanceof Projectile && a == target) {
            engine.remove(b);
        } else if (a instanceof Enemy && b instanceof Projectile) {
            engine.remove(b);
            c.handleCollide(engine, a);
        } else if (b instanceof Enemy && a instanceof Projectile) {
            engine.remove(a);
            c.handleCollide(engine, b);
        } else if (b == this.circle && a instanceof Powerup) {
            projSpeed += 1;
            engine.remove(a);
        } else if (a == this.circle && b instanceof Powerup) {
            projSpeed += 1;
            engine.remove(b);

            for (Projectile p: projectiles)
            {
                p.setSpeed(projSpeed);
            }
        }
    }

}
