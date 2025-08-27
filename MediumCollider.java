import edu.nd.cse.paradigms.*;

public class MediumCollider extends Collider
{
    public void handleCollide(PEEngine engine, PEWorldObject obj)
    {
        Enemy enemy = (Enemy)obj;
        if (enemy.getColor() == 0xff2600) {
            engine.remove(enemy);
        } else {
            enemy.setColor(0xff2600);
        }
    }
}