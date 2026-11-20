import java.awt.Color;
import java.util.Random;
//- Decorator replacing an edit's color with a random one.
public class RandomColor extends BitmapEditDecorator{
//- Variable
    private Color rc;
//- Wrap given edit, picks one random color 
    public RandomColor(BitmapEdit e){
        super(e);
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        this.rc = new Color(r,g,b);
    }
//- Returns random color 
    public Color getColor(){
        return rc;
    }
}
