import edu.nd.cse.paradigms.*;
import java.util.Random;

public class PowerupFactory extends PEShapeFactory
{
    private final int size = 50;
    private final Random random;


    public PowerupFactory()
    {
        random = new Random();
    }
    
    public PEWorldObject createShape(PEEngine engine)
    {
        Powerup pu = new Powerup();
        pu.setSize(size);
        
        int height = engine.getHeight();
        int width  = engine.getWidth();

        int randx = random.nextInt(width - size*2);
        randx += size;

        int randy = random.nextInt(height - size*2);
        randy += size;

        pu.setCenter(randx, randy);
        pu.setColor(0x19a600);

        engine.add(pu);

        return pu;
    }
}
