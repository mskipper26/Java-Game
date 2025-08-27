import edu.nd.cse.paradigms.*;

public class HardCollider extends Collider
{
    public void handleCollide(PEEngine engine, PEWorldObject obj)
    {
        Enemy enemy = (Enemy)obj;
        if (enemy.getColor() == 0xff2600) {
            engine.remove(enemy);
        } else if (enemy.getColor() == 0xccff00) {
            enemy.setColor(0xff2600);
        } else {
            enemy.setColor(0xccff00);
        }
    }
}