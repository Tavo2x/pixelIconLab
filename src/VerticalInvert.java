public class VerticalInvert extends BitmapEditDecorator{
//- Variable
    private int height;
//- Constructor
    public VerticalInvert(BitmapEdit e, int h){
        super(e);
        this.height = h;
    }
//- Return the inverted vertical row
    @Override 
    public int getRow(){
        return height - 1 - edit.getRow();
    }
}
