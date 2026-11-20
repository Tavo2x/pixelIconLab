import java.awt.Color;
import java.util.ArrayList;
public class IconEdit implements BitmapEdit{
//- Variables
    private ArrayList<IconObserver> observers;
    private int row;
    private int col;
    private Color color;
    private IconObserver source;

//- Constructor, creating list of observers
    public IconEdit(){
        this.observers = new ArrayList<IconObserver>();
    }
//- Attach observer to the list, if not already
    public void attach(IconObserver o){
        if(!observers.contains(o)){
            observers.add(o);
        }
    }
//- Detach an observer
    public void detach(IconObserver o){
        observers.remove(o);
    }
//- Editis the edited pixel, notifying the observers of said edit
    public void setEdit(IconObserver source, int rowEddited, int colEddited, Color colorEddited){
        this.source = source;
        row = rowEddited;
        col = colEddited;
        color = colorEddited;
        for (IconObserver o : observers){
            o.editMade(this);
        }
    }
//- Returns the row
    public int getRow(){
        return row;
    }
//- Returns the column
    public int getCol(){
        return col;
    }
//- Returns the color
    public Color getColor(){
        return color;
    }
//-
    public IconObserver getSource(){
        return source;
    }
//- 
    public int getObserverCount(){
        return observers.size();
    }
}
