public class Pixel {

    private int red;
    private int green;
    private int blue;


    public String toString(){
        return "rgb(" + getRed() + ", " + getGreen() + ", " + getBlue() + ")";
    }

    public Pixel(){
        setRed(0);
        setGreen(0);
        setBlue(0);
    }

    public Pixel(int r, int g, int b){
        setRed(r);
        setGreen(g);
        setBlue(b);
    }

    public void setRed(int r){
        if (r > 0 && r < 255){
            red = r;
        }
    }
    public int getRed(){
        return red;
    }

    public void setGreen(int g){
        if (g > 0 && g < 255){
            green = g;
        }    
    }
    public int getGreen(){
        return green;
    }

    public void setBlue(int b){
        if (b > 0 && b < 255){
            blue = b;
        }
    }
    public int getBlue(){
        return blue;
    }
}
