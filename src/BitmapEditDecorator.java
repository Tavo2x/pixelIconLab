import java.awt.Color;
//- Base decorator that forwards BitmapEdit getters, letting subclasses override only what they need.
public abstract class BitmapEditDecorator implements BitmapEdit{
//- Variable
    public BitmapEdit edit;
//- Wraps the given edit
    public BitmapEditDecorator(BitmapEdit e){
        this.edit = e;
    }
//- Returns the wrapped edit's col
    public int getCol(){
        return edit.getCol();
    }
//- Returns the wrapped edit's row
    public int getRow(){
        return edit.getRow();
    }
//- Returns the wrapped edit's color
    public Color getColor(){
        return edit.getColor();
    }
}
