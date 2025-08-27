import edu.nd.cse.paradigms.*;

public class EasyCollider extends Collider
{
    public void handleCollide(PEEngine engine, PEWorldObject obj)
    {
        engine.remove(obj);
    }
}
