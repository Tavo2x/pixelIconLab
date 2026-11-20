//- Decorator mirroring and edited column across the grid. 
public class HorizontalInvert extends BitmapEditDecorator{
//- Variable
    private int width;
//- Constructor,
    public HorizontalInvert(BitmapEdit e, int w){
        super(e);
        this.width = w;
    }
//- Return the inverted horizontal column.
    @Override 
    public int getCol(){
        return width - 1 - edit.getCol();
    }
}
